import { NavLink } from "react-router-dom";
import { Botao } from "../../Componentes/Botao";
import { Header } from "../../Componentes/Header";
import styles from './VerificacaoEmail.module.css'

export function VerificacaoEmail () {
    return (
        <body className={styles.body}>
            <header>
                <Header />
            </header>
            <main className={styles.main}>
                <div className={styles.box}>
                    <p className={styles.text}>Seu registro está sendo verificado..Nossa equipe enviará um e-mail quando estiver tudo pronto.</p>
                        <nav className={styles.botao}>
                            <Botao>
                                <span className={styles.textbutton}>
                                    <NavLink to="/" className={styles.link}>
                                        <h5>Voltar para Home</h5>
                                    </NavLink>
                                </span>
                            </Botao>
                        </nav>
                </div>  
            </main>
        </body>
        
    )
}