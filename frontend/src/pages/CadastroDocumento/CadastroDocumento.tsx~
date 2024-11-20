import { DocumentSelector } from "../Componentes/DocumentSelector/DocumentSelector.tsx";
import { FileUpload } from "../Componentes/FileUpload/FileUpload.tsx";
import { Botao } from "../Componentes/Botao/Botao.tsx";
import { useState } from "react";
import styles from './CadastroDocumento.module.css';

import { Header } from "../Componentes/Header/Header.tsx";
import { Footer } from "../Componentes/Footer/Footer.tsx";


export function CadastroDocumento(){
    const [documentoSelected, setDocumentoSelected] = useState("rg");


    return (
        <div>
            <Header/>
                <main className={styles.main}>
                    <h2 className={styles.h2}>Verificar perfil</h2>
                    <DocumentSelector
                        selected={documentoSelected}
                        setSelected={setDocumentoSelected}
                    />
                    <span>Envie uma foto frente e verso do seu documento</span>

                    { documentoSelected == "passport" ? 
                        <div>
                            <label style={{color: 'transparent'}}>Passaporte</label>

                            <FileUpload></FileUpload>
                        </div> :
                        
                        <div className={styles.fileUploads}>
                            <div>
                                <label>Frente</label>
                                <FileUpload></FileUpload>
                            </div>

                            <div>
                                <label>Verso</label>
                                <FileUpload></FileUpload>
                            </div>
                        </div>
                    }

                    <span>Tipos de arquivo suportados: pdf, txt, doc, ppt, xls, docx e outros</span>
                    <Botao className={styles.botaoAvancar}>Avançar</Botao>
                </main>
            <Footer/>
        </div>
    );
}