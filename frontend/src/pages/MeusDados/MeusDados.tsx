import { useState } from "react";
import { Header } from "../../Componentes/Header";
import { Input } from "../../Componentes/Input";
import styles from './MeusDados.module.css'
import { BsPerson } from 'react-icons/bs'
import { Botao } from "../../Componentes/Botao";
import { NavLink } from "react-router-dom";
import { Footer } from "../../Componentes/Footer";


export function MeusDados () {
    const [nomeCompleto, setNomeCompleto] = useState('');
    const [telefone, setTelefone] = useState('');
    const [email, setEmail] = useState('');

    return (
        <body className={styles.body}>
            <header>
                <Header />
            </header>
            <main className={styles.main}>
                <div className={styles.box}>
                    <p className={styles.text}>Meus Dados</p>
                    <div className={styles.circlebox}>
                                <i className={styles.personIcon}>
                                    <BsPerson  />
                                </i>
                            <p className={styles.editphoto}>Editar foto</p>
                    </div>
                    <div className={styles.textbox}>
                        <div className={styles.namearea}>
                            <p className={styles.name}>Nome Completo</p>
                            <div className={styles.inputName}>
                                <Input className={styles.input} type='text' value={nomeCompleto} setValue={setNomeCompleto} edit>Nome Completo</Input>                              
                            </div>
                        </div>
                        <div className={styles.phonearea}>
                            <p className={styles.phone}>Telefone</p>
                            <div className={styles.inputPhone}>
                                <Input className={styles.input} type='text' value={telefone} setValue={setTelefone} edit>Telefone</Input>
                            </div>
                        </div>
                        <div className={styles.emailarea}>
                            <p className={styles.email}>E-mail</p>
                            <div className={styles.inputEmail}>
                                <Input className={styles.input} type='text' value={email} setValue={setEmail} edit>E-mail</Input>
                            </div>
                        </div>
                        </div>
                        <nav className={styles.botao}>
                            <Botao>
                                <span className={styles.textbutton}>
                                    <NavLink to="/cadastrodocumento" className={styles.link}>
                                        <h5>Verificar minha conta</h5>
                                    </NavLink>
                                </span>
                            </Botao>
                        </nav>
                    </div>
            </main>
            <footer>
                <Footer />
            </footer>
        </body>
    )
}
