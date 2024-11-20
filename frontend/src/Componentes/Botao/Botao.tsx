import styles from './Botao.module.css';

interface PropBotao {
    children: React.ReactNode;
    onClick?: () => void;
    secundario?: boolean;
    className?: string;
}

export function Botao(props: PropBotao){
    return (
        <button
            className={styles.botao + ' ' + (props.secundario ? styles.secundario : styles.primario) + ' ' + props.className}
            onClick={props.onClick}
        >{props.children}</button>
    );
}