import { Header } from "./Header";
import { Footer } from "./Footer";
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