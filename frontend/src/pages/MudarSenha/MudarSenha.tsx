import { useState } from "react";
import { Header } from "../../Componentes/Header/Header.tsx";
import { Input } from "../../Componentes/Input/Input.tsx";
import styles from "./MudarSenha.module.css"
import { Botao } from "../../Componentes/Botao/Botao.tsx";
import { Footer } from "../../Componentes/Footer/Footer.tsx";
import { useNavigate } from "react-router-dom";
import { passwordRegex } from "../../lib/utils";
import api from "../../lib/api";


export function MudarSenha (){
    const navigate = useNavigate();

    function redefinirSenha()
    {
        if(passwordRegex.test(novaSenha) == false)
        {
            alert("Sua senha não condiz com as condições necessárias");
            return;
        }
        
        api.patch('/v1/users/change-password', {newPassword: novaSenha}).then(res => {
            alert(res.data.message);
            navigate('/');
        }).catch(err => alert(err.response.data));

    }


    const [novaSenha,setNovaSenha] = useState('')
    return (
        <body className={styles.body}>
            <header>
                <Header />
            </header>
            <main className={styles.main}>
                <div className={styles.box}>
                    <p className={styles.text1}>Redefinir sua senha</p>
                    <span className={styles.text2}>Crie uma nova senha<br/></span>
                    <div className={styles.text3Div}>
                        <p className={styles.text3}>
                            &bull;&nbsp;  Escolha senhas com pelo menos 8 caracteres<br/>
                            &bull;&nbsp;  Utilize no minímo uma letra minúscula,<br/>&emsp;maiúscula,um número e um caractere especial<br/>   
                            &bull;&nbsp;  Não comece ou termine com um espaço em<br/>&emsp;branco
                        </p>
                    </div>
                    <div className={styles.input}>
                        <Input type="password" placeholder="Nova senha" value={novaSenha} setValue={setNovaSenha}>Nova senha</Input>
                    </div>
                    <div className={styles.botao}>
                        <Botao onClick={redefinirSenha}>
                            <span className={styles.textbotao}>
                                Avançar
                            </span>
                        </Botao>
                    </div>
                </div>
            </main>
            <footer>
                <Footer />
            </footer>
        </body>

)}
