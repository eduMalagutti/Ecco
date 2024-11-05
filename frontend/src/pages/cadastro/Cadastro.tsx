import { Header } from '../../Componentes/Header'
import { Footer } from '../../Componentes/Footer'
import { Botao } from '../../Componentes/Botao'
import styles from '../cadastro/Cadastro.module.css'
import { useState } from 'react'
import { Input } from '../../Componentes/Input'
import api from '../../lib/api';
import { useNavigate } from "react-router-dom";


import { passwordRegex } from '../../lib/utils'

export function Cadastro () {
    const navigate = useNavigate();


    const[NomeCompleto, setNomeCompleto] = useState('')
    const[Email, setEmail] = useState('')
    // const[RG,setRG] = useState('')
    const[Celular,setCelular] = useState('')
    const[Senha,setSenha] = useState('')
    
    async function onCadastro()
    {
        
        if(passwordRegex.test(Senha) == false)
        {
            // Peço perdão por esse código crasso
            alert("Senha não condiz com os requerimentos");
        }

        api.post('/v1/users/signup', {
            "name": NomeCompleto,
            "email": Email,
            "password": Senha,
            "phone": Celular
        }).then(() => {
            

            // login
            api.post('/v1/users/login', {
                "email": Email,
                "password": Senha
            }).then(res => {
                // logged in worked

                // store jwt token
                localStorage.setItem("jvToken", res.data);

                navigate("/verificacao-cadastro");
            }).catch(err => alert(err.response.data));

        }
        ).catch(err => {
            // Algum erro ocorreu
            alert(err.response.data);
        });
    }



    return (
        <div>
            <header>
                <Header/> 
            </header>

            <main>
                <div className={styles.box}>
                    <p className={styles.title}>Cadastre-se</p>

                    <div className={styles.textbox}>
                        <div className={styles.inputs}>
                            <Input className={styles.input} type ="text" placeholder='Nome Completo' value={NomeCompleto} setValue={(value) => setNomeCompleto(value.toString().replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();}))}>Nome Completo</Input>
                            <Input className={styles.input} type ="text" placeholder='E-mail' value={Email} setValue={setEmail}>Email</Input>
                            {/* <Input className={styles.input} type ="text" placeholder='RG' value={RG} setValue={setRG}>RG</Input> */}
                            <Input className={styles.input} type ="text" placeholder='Celular'value={Celular}setValue={setCelular}>Celular</Input>
                            <Input className={styles.input} type="password" placeholder='Senha'value={Senha}setValue={setSenha}>Senha</Input> 
                        </div>

                        <div className={styles.orientacoes}>
                            •Escolha senhas com pelo menos 8 caracteres
                            <br></br>
                            •Utilize no minímo uma letra minúscula, maiúscula, um número e um caractere especial
                            <br></br>
                            •Não comece ou termine com um espaço em branco
                        </div>

                    </div>

                    <Botao className={styles.botao} onClick={onCadastro}>
                            Criar
                    </Botao>
                </div>

                <div>
                    <Footer />
                </div>
            </main>

            
                
           



        </div>


    )



}