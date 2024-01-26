import React from 'react'
import { IconType } from 'react-icons';
import styles from './Button.module.scss';

interface ButtonProps {
    label: string;
    onClick?: (e: React.MouseEvent<HTMLButtonElement>) => void;
    disabled?: boolean;
    outline?: boolean;
    small?: boolean;
    icon?: IconType
}

const Button: React.FC<ButtonProps> = ({
    label,
    onClick,
    disabled,
    outline,
    small,
    icon : Icon
}) => {
    if (outline) {
        if (small) {
            return (
                <button
                    type='submit'
                    disabled={disabled}
                    onClick={onClick}
                    className={styles.small_outline}    
                >
                        {Icon && (
                            <Icon
                                size={24}
                                className={styles.Icon}
                            />
                            )}
                        {label}
                </button>
            )
        } else {
            return (
                <button
                    type='submit'
                    disabled={disabled}
                    onClick={onClick}
                    className={styles.not_small_outline}    
                >
                        {Icon && (
                            <Icon
                                size={24}
                                className={styles.Icon}
                            />
                            )}
                        {label}
                </button>
            )
        }
    } else {
        if (small) {
            return (
                <button
                    type='submit'
                    disabled={disabled}
                    onClick={onClick}
                    className={styles.small_not_outline}    
                >
                        {Icon && (
                            <Icon
                                size={24}
                                className={styles.Icon}
                            />
                            )}
                        {label}
                </button>
            )
        } else {
            return (
                <button
                    type='submit'
                    disabled={disabled}
                    onClick={onClick}
                    className={styles.not_small_not_outline}    
                >
                        {Icon && (
                            <Icon
                                size={24}
                                className={styles.Icon}
                            />
                            )}
                        {label}
                </button>
            )
        }
    }
}

export default Button