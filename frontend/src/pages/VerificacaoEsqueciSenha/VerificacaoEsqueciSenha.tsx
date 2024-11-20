// import { Input } from '../Componentes/Input.tsx';
import { Botao } from '../../Componentes/Botao/Botao.tsx';
import { VistaPageComponent } from '../../Componentes/VistaPageComponent/VistaPageComponent.tsx';
import { Input } from '../../Componentes/Input/Input.tsx';
import styles from './VerificacaoEsqueciSenha.module.css'

import { useRef, useState } from 'react';

import api from '../../lib/api.ts';
import { useNavigate } from 'react-router-dom';

// TODO: go back when pressing backspace
// TODO: fix button shadow?!?

export function VerificacaoEsqueciSenha() {
    const navigate = useNavigate();

    // metendo o louco
    const email = localStorage.getItem('email');

    function verificarSenha()
    {
        api.patch('/v1/users/forgot-password/verify', {
            code: code,
            email
        }).then(res => {

            // save token
            localStorage.setItem("jvToken", res.data);

            navigate('/mudar-senha');
        }).catch(err => alert(err.response.data));
    }

    const inputsRef: React.MutableRefObject<HTMLInputElement | null>[] = [];
    const inputs: JSX.Element[] = [];

    const [code, setCode] = useState('');

    for (let i = 0; i < 6; i++) {
        const inputRef = useRef<HTMLInputElement | null>(null);
        inputsRef.push(inputRef);
        
        const [value, setValue] = useState('');
        
        inputs.push(
            <Input
                className={styles.input}
                type='text'
                value={value}
                setValue={
                    (newValue) => {
                        newValue = newValue.toString().slice(newValue.length-1, newValue.length);
                        setValue(newValue);
                        setCode((code.slice(0, i) + newValue.toString() + code.slice(i, 6)).slice(0, 6));
                        
                        
                        const nextIndex = newValue.length == 0 ? i-1 : i+1;
                        // not last or first input element
                        if(nextIndex >= 0 && nextIndex < inputsRef.length)
                        {
                            // focus next input
                            console.log(inputsRef);
                            inputsRef[nextIndex].current?.focus();
                        }
                    }
                }
                inputRef={inputRef}
            />
        )
    }

    return (
        <VistaPageComponent>

            <main className={styles.main}>
                <div className={styles.header}>
                    <h3 className={styles.h3}>Encontre sua conta da ECCO</h3>
                    <p className={styles.p}>Um código de confirmação foi enviado para</p>
                    <a className={styles.email + ' ' + styles.a}>{email}</a>
                </div>

                <div className={styles.codeAllInputs}>
                    <div className={styles.code3Inputs}>
                        {inputs.slice(0, 3)}
                    </div>
                    <div className={styles.code3Inputs}>
                        {inputs.slice(3, 6)}
                    </div>
                </div>
                <Botao className={styles.button} onClick={verificarSenha}>Avançar</Botao>
                <a className={styles.a} href="">Enviar outro código</a>
            </main>
        </VistaPageComponent>
    );

}