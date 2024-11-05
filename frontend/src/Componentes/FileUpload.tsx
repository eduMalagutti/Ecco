import FolderIcon from "../assets/folder-icon.svg";
import { Botao } from './Botao';
import styles from './FileUpload.module.css';
import { createRef } from 'react';


export function FileUpload(){

    const inputRef = createRef<HTMLInputElement>();
    function handleDrop(event: React.DragEvent<HTMLDivElement>)
    {
        event.preventDefault();
        const files = event.dataTransfer?.files;

        // files are handed to another function
        // so that the user can send files by either
        // clicking on the button
        // or dragging
        onFiles(files);
    }


    function onFiles(files: FileList)
    {
        // do something with the files
        // this depends on how backend
        // will be developed
        alert(files[0].name);
    }


    return (
        <div
            className={styles.wrapper}
            onDrop={(e) => handleDrop(e)}
            onDragOver={(e) => e.preventDefault()}
        >
            <input
                type="file"
                ref={inputRef}
                hidden

                // idk if this syntax of ! is correct
                onChange={() => onFiles(inputRef.current!.files!)}
            />
            
            <Botao className={styles.botaoSelecionaDocumento} onClick={() => inputRef.current!.click()}>Selecione o documento para enviar</Botao>
            <img src={FolderIcon} alt="Arquivo" />
            <span>Ou arraste e solte</span>
        </div>
    );
}