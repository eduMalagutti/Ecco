import { NavLink } from "react-router-dom";
import { Botao } from "../../Componentes/Botao";
import { Header } from "../../Componentes/Header";
import styles from "./VerificacaoCadastro.module.css"
import { Footer } from "../../Componentes/Footer";

export function VerificacaoCadastro () {
    return (
        <body className={styles.body}>
            <header>
                <Header />
            </header>
            <main className={styles.main}>
                <div className={styles.box}>
                    <div></div>
                    <p className={styles.text}>Seu registro está sendo verificado.
                        Nossa equipe enviará um e-mail quando estiver tudo pronto.</p>
                        <div className={styles.botao}>
                        <NavLink to="/" className={styles.link}>
                            <Botao>
                                <span className={styles.textbutton}>
                                        Voltar para Home
                                </span>
                            </Botao>
                         </NavLink>
                        </div>
                </div>  
            </main>
            <footer>
                <Footer />
            </footer>
        </body>
        
    )
}