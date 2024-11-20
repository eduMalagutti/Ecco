import { Header2 } from '../Componentes/Header2/Header2.tsx'
import { EccoCircle } from '../Componentes/EccoCircle/EccoCircle.tsx';
import { Botao } from '../Componentes/Botao/Botao.tsx';
import styles from './Home.module.css'

import Limpeza from '../assets/limpeza.png';


import { AiOutlineLike } from 'react-icons/ai';
import { BsCalendar4, BsSun } from 'react-icons/bs';
import { NavLink } from 'react-router-dom';

export function Home() {

    return (
        <div className={styles.wrapper}>
            <header>
                <Header2 />
            </header>

            <div className={styles.main}>
                <EccoCircle></EccoCircle>

                {/* <div className={styles.carouselDiv}>
                    <Carousel>
                        <img src={Limpeza} style={{width: '100px'}} />
                        <img src={Limpeza} style={{width: '100px'}} />
                    </Carousel>
                </div> */}

                <div className={styles.carouselDiv}>
                    <img src={Limpeza} alt="Limpeza" className={styles.carouselImg}/>
                    <span>Encontre e ofereça serviços em um só lugar</span>
                </div>

                <div className={styles.infos}>


                    <div className={styles.info}>
                        <div className={styles.iconWrapper}>
                            <AiOutlineLike className={styles.icon} />
                        </div>
                        Serviços avaliados visando à qualidade
                    </div>
                    <div className={styles.info}>
                        <div className={styles.iconWrapper}>
                            <BsCalendar4 className={styles.icon} />
                        </div>
                        Trabalhadores sempre disponíveis
                    </div>
                    <div className={styles.info}>
                        <div className={styles.iconWrapper}>
                            <BsSun className={styles.icon} />
                        </div>
                        Serviços disponíveis de manhã e/ou de tarde
                    </div>
                </div>

                <div className={styles.CTA}>
                    <div className={styles.CTABox}>
                        <h3 className={styles.h3}>Comece a trabalhar agora mesmo</h3>
                        <nav>
                            <div className={styles.botaoDiv}>
                            <NavLink to="/login" className={styles.navlink}><Botao>Prestar serviço</Botao></NavLink>
                            </div>
                        </nav>
                    </div>
                    <div className={styles.CTABox}>
                        <h3 className={styles.h3}>Procure um serviço agora mesmo</h3>
                        <nav>
                            <div className={styles.botaoDiv}>
                                <NavLink to="/login" className={styles.navlink}><Botao>Procurar serviço</Botao></NavLink>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

    )
}