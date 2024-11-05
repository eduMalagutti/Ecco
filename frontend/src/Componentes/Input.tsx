import styles from './Input.module.css';
import { Eye, EyeSlash } from "phosphor-react";
import { useState } from 'react';
import { AiFillEdit } from 'react-icons/ai';

interface PropInput {
    type: 'text' | 'password';
    children?: React.ReactNode;
    value: string;
    placeholder?: string;
    setValue: React.Dispatch<React.SetStateAction<string>>;
    inputRef?: any | React.MutableRefObject<any | undefined> | undefined;
    className?: string;
    edit?: boolean;
    readOnly?: boolean;
}

export function Input(props: PropInput) {
    const [visible, setVisible] = useState(false);
    const [possibletowrite, setPossibleToWrite] = useState(false);


    return (
        <div className={styles.wrapperDiv}>
            <span className={styles.inputBox}>
                <input ref={props.inputRef} placeholder={props.placeholder} type={
                    props.type == 'text' ? 'text' : (visible ? 'text' : 'password')
                } className={styles.input + ' ' + props.className} value={props.value} onChange={
                    event => {
                        props.setValue(event.target.value);
                    }
                } />
                {
                    props.type == 'password' &&
                    <div onClick={
                        () => {
                            setVisible(!visible);
                        }
                    } className={styles.eyeIcon}>
                        {
                            visible ? <Eye size={16}/> : <EyeSlash size={16}/>
                        }
                    </div>
                }
                {
                    props.edit == true &&
                        <i onClick={
                            () => {
                                setPossibleToWrite(!possibletowrite)
                            }
                        } className={styles.penIcon} >
                            {
                                possibletowrite ? <AiFillEdit size={24} color='gray'/> : <AiFillEdit size={24}/>
                            }
                        </i>
                }
            </span>
        </div>
    );
}