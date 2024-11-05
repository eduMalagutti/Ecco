import styles from './DocumentSelector.module.css';


interface PropDocumentSelector{
  // i want to fix this to 
  // but idk cause i need to learn how to customize
  // useState()<specifiedType> maybe!?
  // selected: 'rg' | 'cnh' | 'passport' | '';
  selected: string;
  setSelected: React.Dispatch<React.SetStateAction<string>>;
}


export function DocumentSelector(props: PropDocumentSelector) {

  return (

    <div className={styles.wrapper}>
      <button onClick={() => props.setSelected('rg')} className={props.selected == 'rg' ? styles.selected : styles.notSelected}>RG</button>
      <button onClick={() => props.setSelected('cnh')} className={props.selected == 'cnh' ? styles.selected : styles.notSelected}>CNH</button>
      <button onClick={() => props.setSelected('passport')} className={props.selected == 'passport' ? styles.selected : styles.notSelected}>Passaporte</button>
    </div>

  )

}