import styles from './EccoCircle.module.css';
import LogoEcco from '../../assets/Logo-EccoComFolha.svg';
import Circles from '../../assets/Circles.svg';


export function EccoCircle(){
    return (
        <div className={styles.circlesWrapper}>
            <img className={styles.circles} src={Circles} alt="" />
            <img className={styles.logo} src={LogoEcco} alt="Logo da ECCO" />
        </div>
    )

}
