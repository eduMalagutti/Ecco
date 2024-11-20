import { Header } from "../Header/Header.tsx";
import { Footer } from "../Footer/Footer.tsx";
import styles from './VistaPageComponent.module.css';


interface PropVistaPageComponent{
    children?: React.ReactNode;
}


export function VistaPageComponent(props: PropVistaPageComponent){
    return (
        <div className={styles.background}>
            <Header/>

            <div className={styles.contentDiv}>
                <div className={styles.content}>
                    {props.children}
                </div>
            </div>
            <Footer/>
        </div>
    )
}