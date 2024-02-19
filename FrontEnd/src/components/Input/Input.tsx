import React from 'react';
import styles from './Input.module.scss';
import { UseFormRegister, FieldErrors, FieldValues } from 'react-hook-form';

interface InputProps {
  id: string;
  label: string;
  type?: string;
  disabled?: boolean;
  formatPrice?: boolean;
  formatDate?: Date;
  required?: boolean;
  register: UseFormRegister<FieldValues>;
  errors: FieldErrors;
}

const Input: React.FC<InputProps> = ({
  id,
  label,
  type = 'text',
  disabled,
  formatPrice,
  register,
  required,
  errors
}) => {
  return (
    <div className={styles.inputContainer}>
      {formatPrice && (
        <span></span>
      )}
      <input
        id={id}
        disabled={disabled}
        {...register(id, { required })}
        placeholder=''
        type={type}
        className={`
          ${styles.inputField}
          ${formatPrice ? styles.priceFormat : ''}
          ${errors[id] ? styles.error : ''}
        `}
      />
      <label className={`${styles.inputLabel} ${errors[id] ? 'text-rose-500' : 'text-zinc-400'}`}>
        {label}
      </label>
    </div>
  );
};

export default Input;