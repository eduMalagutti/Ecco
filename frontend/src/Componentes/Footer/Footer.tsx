import { NavLink } from 'react-router-dom';
import styles from "./Footer.module.css";

export function Footer() {
    return (
        <footer className={styles.footer}>
            <div className={styles.row}>
                <nav>
                    <NavLink to="/sobre" className={styles.sobre} >
                        <div>
                            Sobre
                        </div>
                    </NavLink>
                </nav>

                <nav>
                    <NavLink to="/termos-de-servico" className={styles.termos} >
                        <div>
                            Termos de Serviço
                        </div>
                    </NavLink>

                </nav>

            </div>

            <div className={styles.column}>
                <div>
                    Contato: (xx) - xxxx-xxxx
                </div>
                <div>
                    ©ECCO - 2024
                </div>
            </div>


        </footer >




    )

}
