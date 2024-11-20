import styles from './Background_MiddleCircle.module.css'
import { Header2 } from '../Header2/Header2.tsx';
import Circles from '../../assets/Circles.svg'
import { Footer } from '../Footer/Footer.tsx';

interface PropsBackground_MiddleCircle {
    children?: React.ReactNode
}

export function Background_MiddleCircle (props: PropsBackground_MiddleCircle) {
    return (
        <div className={styles.body}>
            <header>
                <Header2/>
            </header>
            <main className={styles.main}>
                <div className={styles.containerCircles}>
                        <img className={styles.circles} src={Circles}/>
                    </div>
                <div>
                    {props.children}
                </div>
            </main>
            <Footer/>
        </div>
    )
}