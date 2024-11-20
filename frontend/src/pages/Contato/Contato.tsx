import { BsEnvelope, BsFacebook, BsInstagram, BsTelephone } from 'react-icons/bs'
import styles from './Contato.module.css'
import { AiOutlineMail } from 'react-icons/ai'
import { Botao } from '../../Componentes/Botao/Botao.tsx'
import { Background_MiddleCircle } from '../../Componentes/BackgroundMiddleCircle/Background_MiddleCircle.tsx'
import { NavLink } from 'react-router-dom'

export function Contato(){
    return (
        <Background_MiddleCircle>
            <div className={styles.container}>
                <div className={styles.title}>
                    <h1>Contato</h1>
                    <BsEnvelope />
                </div>
                <p className={styles.p}> Nos contate através de nossos canais de comunicação oficial</p>
                <ul className={styles.containerList}>
                    <li>
                        <BsFacebook color='#5F8666' size={30}></BsFacebook>
                        <span>Facebook</span>
                    </li>
                    <li>
                        <BsInstagram color='#5F8666' size={30} />
                        <span>Instagram</span>
                    </li>
                    <li>    
                        <AiOutlineMail color='#5F8666'size={30} />
                        <span>E-mail</span>
                    </li>
                    <li>
                        <BsTelephone color='#5F8666' size={30} />
                        <span>0800</span>
                    </li>
                </ul>
                <nav>
                    <div className={styles.button}>
                        <NavLink to="/" className={styles.navlink}><Botao secundario>
                            <p className={styles.textbutton}> Voltar para a Home </p>
                        </Botao></NavLink>
                    </div>
                </nav>
            </div>
        </Background_MiddleCircle>
    )
}