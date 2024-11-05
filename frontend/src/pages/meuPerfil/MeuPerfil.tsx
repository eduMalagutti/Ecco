import { Header2 } from '../../Componentes/Header2'
import styles from './MeuPerfil.module.css'
import Circles from '../../assets/Image-Circles.svg'
import LogoEccoFolha from '../../assets/Logo-EccoComFolha.svg'
import { Footer } from '../../Componentes/Footer'
import { BsPersonFill, BsCardText, BsPencil} from "react-icons/bs";
import { AiOutlineLogout, AiOutlineWarning} from "react-icons/ai";
import { Botao } from '../../Componentes/Botao'
import { NavLink } from 'react-router-dom'

export function MeuPerfil () {

return (
    <body className={styles.body}>
        <header>
            <Header2 loggedIn/>
        </header>
        <main className={styles.main}>
            <div className={styles.imagens}>
                <img className={styles.circles} src={Circles}/>
                <img className={styles.eccofolha} src={LogoEccoFolha}/>
            </div>
            <div className={styles.text}>
                <div className={styles.boxmeuperfil}>
                    <span className={styles.textmeuperfil}>Meu Perfil</span>
                    <i className={styles.iconperson}><BsPersonFill size={61}/></i>
                </div>
                <div className={styles.boxinputs}>
                    <div className={styles.circlebox}> 
                        <i><BsPersonFill size={58}/></i>
                        <span>Editar Foto</span>
                    </div>
                    <nav>
                        <div className={styles.botoes}>
                            
                            <NavLink to="/caracteristica" className={styles.navlink}><Botao>Adicionar características sobre mim <BsCardText size={24}/></Botao></NavLink>
                            
                            <div className={styles.botoesmenores}>
                                <NavLink to="/meusdados" className={styles.navlink}><Botao>Editar meus dados <BsPersonFill size={24}/></Botao></NavLink>
                            
                            
                                <NavLink to="/mudar-senha" className={styles.navlink}><Botao>Mudar minha senha <BsPencil size={24}/></Botao></NavLink>
                            
                            
                                <NavLink to="/" className={styles.navlink}><Botao>Log Out <AiOutlineLogout size={24}/></Botao></NavLink>
                            
                            
                                <NavLink to="/" className={styles.navlink}><Botao>Excluir minha conta <AiOutlineWarning size={24}/></Botao></NavLink>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </main>
        <footer>
            <Footer />
        </footer>
    </body>
)
}