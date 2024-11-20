import styles from './Header.module.css'
import logoEcco from '../../assets/Logo-Ecco.svg'
import '../../global.css'

export function Header(){

    return(
        <header className={styles.header}>
            <img src={logoEcco} alt="Logotipo da Ecco" />
        </header>
    );
}