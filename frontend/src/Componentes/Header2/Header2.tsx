import styles from './Header2.module.css'
import logoFolha from '../../assets/Logo-Folha.svg'
import LoginIcon from '../../assets/Login-Icon.svg';
import { BsList, BsSearch, BsBell, BsXCircle, BsPip, BsStarFill, BsCheck, BsPersonFill, BsChat, BsHammer } from 'react-icons/bs';
import { AiTwotoneTool, AiOutlineLogout } from "react-icons/ai";
import '../../global.css'
import { useNavigate } from 'react-router-dom';
import { NavLink } from 'react-router-dom';


import { validToken } from '../../lib/utils.ts';


export function Header2() {
    const navigate = useNavigate();

    function logOut() {
        localStorage.removeItem("jvToken");
        navigate('/');
    }

    return (
        <header className={styles.header}>
            {validToken() ?

                // Logado
                <>
                    <img src={logoFolha} alt="Logotipo da Ecco" />
                    <div className={styles.boxtext}>
                        <div className={styles.dropdown}>
                            <i className={styles.list}>
                                <BsList size={24} />
                            </i>
                            <span className={styles.text}>
                                Menu
                            </span>
                            <div className={styles.dropdown_child2}>
                                <nav>
                                    <NavLink to="/meu-perfil" className={styles.navlink}><button className={styles.button6}>
                                        <span className={styles.textbutton2}>Meu Perfil </span>
                                        <i className={styles.buttonicon}><BsPersonFill size={18} /></i>
                                    </button></NavLink>
                                    <hr color='E1E1E1' />
                                    <NavLink to="/servicoandamneto" className={styles.navlink}><button className={styles.button7}>
                                        <span className={styles.textbutton2}>Serviços em Andamento</span>
                                        <i className={styles.buttonicon}><AiTwotoneTool size={18.72} /></i>
                                    </button></NavLink>
                                    <hr color='E1E1E1' />
                                    <NavLink to="/chat" className={styles.navlink}><button className={styles.button8}>
                                        <span className={styles.textbutton2}>Chat</span>
                                        <i className={styles.buttonicon}><BsChat size={21} /></i>
                                    </button></NavLink>
                                    <hr color='E1E1E1' />
                                    <NavLink to="/servicofinalizado" className={styles.navlink}><button className={styles.button9}>
                                        <span className={styles.textbutton2}>Serviço finalizado</span>
                                        <i className={styles.buttonicon}><BsHammer size={24} /></i>
                                    </button></NavLink>
                                    <hr color='E1E1E1' />
                                    <button className={styles.button10} onClick={logOut}>
                                        <span className={styles.textbutton2}>Logout</span>
                                        <i className={styles.buttonicon}><AiOutlineLogout size={17} /></i>
                                    </button>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <div className={styles.boxicon}>
                        <i className={styles.search}>
                            <BsSearch size={17} />
                        </i>
                        <div className={styles.dropdown}>
                            <i className={styles.bell}>
                                <BsBell size={24} />
                            </i>
                            <div className={styles.dropdown_child}>
                                <nav>
                                    <NavLink to="/notificacoes" className={styles.navlink}><button className={styles.button1}>
                                        <span className={styles.textbutton}>Notificações </span>
                                        <i className={styles.buttonicon}><BsBell size={24} /></i>
                                    </button></NavLink>
                                    <hr color='E1E1E1' />
                                    <NavLink to="/ofertacancelada" className={styles.navlink}><button className={styles.button2}>
                                        <span className={styles.textbutton}>Oferta cancelada</span>
                                        <i className={styles.buttonicon}><BsXCircle size={24} /></i>
                                    </button></NavLink>
                                    <hr color='E1E1E1' />
                                    <NavLink to="/documentosverificados" className={styles.navlink}><button className={styles.button3}>
                                        <span className={styles.textbuttonDocumentos}>Documentos <br />verificados</span>
                                        <i className={styles.buttonicon}><BsPip size={24} /></i>
                                    </button></NavLink>
                                    <hr color='E1E1E1' />
                                    <NavLink to="/servicofinalizado" className={styles.navlink}><button className={styles.button4}>
                                        <span className={styles.textbutton}>Serviço finalizado</span>
                                        <i className={styles.buttonicon}><BsCheck size={26} /></i>
                                    </button></NavLink>
                                    <hr color='E1E1E1' />
                                    <NavLink to="/novaavaliacao" className={styles.navlink}><button className={styles.button5}>
                                        <span className={styles.textbutton}>Nova avaliação!</span>
                                        <i className={styles.buttonicon}><BsStarFill size={16} /></i>
                                    </button></NavLink>
                                </nav>
                            </div>
                        </div>
                    </div>
                </>
                :
                // Não logado
                <div className={styles.notLoggedIn}>

                    <nav>
                        <span className={styles.contato}>
                            <NavLink to="/Contato" className={styles.navlink}>Contato</NavLink>
                        </span>
                    </nav>


                    <div className={styles.loginBox}>
                        <nav>
                            <NavLink to="/login" className={`${styles.navlink} ${styles.login}`}>
                                Login
                            </NavLink>
                            <NavLink to="/login" className={styles.navlink}><img className={styles.loginIcon} src={LoginIcon} alt="Login"></img></NavLink>
                        </nav>
                    </div>
                </div>}

        </header>
    );
}