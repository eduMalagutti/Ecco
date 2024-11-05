import styles from '../login/Login.module.css'
import { Header } from '../../Componentes/Header'
import { Input } from '../../Componentes/Input'
import { Botao } from '../../Componentes/Botao'
import { Footer } from '../../Componentes/Footer'
import { useState } from 'react'
import api from '../../lib/api'
import { useNavigate, NavLink } from 'react-router-dom'

export function Login() {
    const navigate = useNavigate();

    const [Email, setEmail] = useState('')
    const [Senha, setSenha] = useState('')


    function login() {
        if (Senha === '') return;
        
        api.post('/v1/users/login', {
            "email": Email,
            "password": Senha
        }).then(res => {
            const token = typeof res.data === 'object' ? res.data.token : res.data;
            
            localStorage.setItem("jvToken", token);
            
            navigate("/");
        }).catch(err => {
            console.error('Login error:', err);
            alert(err.response?.data || 'Error during login');
        });
    }


    return (
        <body>
            <header>
                <Header />
            </header>


            <main className={styles.main}>
                <div className={styles.box}>
                    <p className={styles.title}>Login</p>

                    <Input className={styles.input} type="text" placeholder='E-mail' value={Email} setValue={setEmail}>Email</Input>
                    <Input className={styles.input} type="password" placeholder='Senha' value={Senha} setValue={setSenha}>Senha</Input>

                    <div className={styles.botoes}>
                        <Botao className={styles.botao} onClick={login}>
                            Avançar
                        </Botao>

                        <NavLink to='/esqueci-senha'>
                            <Botao secundario className={styles.botao}>
                                Esqueci a senha
                            </Botao>
                        </NavLink>
                    </div>

                    <nav>
                        <p className={styles.semconta}>
                            Não tem uma conta?
                            <NavLink to="/cadastro" className={styles.navlink}><a className={styles.a} href="">Inscreva-se</a></NavLink>
                        </p>
                    </nav>
                </div>

                <div className={styles.footer}>
                    <Footer />
                </div>




            </main>
        </body>
    )
}































