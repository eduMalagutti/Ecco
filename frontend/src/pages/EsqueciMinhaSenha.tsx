import { useNavigate } from "react-router-dom";

import { Header } from "../Componentes/Header/Header.tsx";
import { Footer } from "../Componentes/Footer/Footer.tsx";
import { Input } from "../Componentes/Input/Input.tsx";
import { Botao } from "../Componentes/Botao/Botao.tsx";

import { useState } from 'react';


import styles from './EsqueciMinhaSenha.module.css';
import api from "../lib/api";

export function EsqueciMinhaSenha()
{
    const navigate = useNavigate();
    const [email, setEmail] = useState('');

    function avancar()
    {
        api.patch(`/v1/users/forgot-password?email=${email}`).then(() => {

            // worked
            localStorage.setItem('email', email);
            navigate('/verificacao-esqueci-senha');

        }).catch(err => alert(err.response.data));
    }

    return (
        <div>
            <Header/>

            <div className={styles.box}>
                <div className={styles.titleSubtitle}>
                    <h3 className={styles.h3}>Encontre sua conta ECCO</h3>
                    <h6 className={styles.h6}>Informe o e-mail associado à sua conta para alterar sua senha.</h6>
                </div>

                <Input className={styles.input} type="text" value={email} setValue={setEmail} placeholder="E-mail"></Input>

                <Botao className={styles.botao} onClick={avancar}>Avançar</Botao>

            </div>

            <Footer/>
        </div>
    );
}