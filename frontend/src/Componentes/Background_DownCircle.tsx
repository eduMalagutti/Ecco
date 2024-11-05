import styles from './Background_DownCircle.module.css'
import { Header2 } from './Header2';
import Circles from '../assets/Image-Circles.svg'

interface PropsBackground_DownCircle {
    children?: React.ReactNode
}

export function Background_DownCircle (props: PropsBackground_DownCircle) {
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
        </div>
    )
}