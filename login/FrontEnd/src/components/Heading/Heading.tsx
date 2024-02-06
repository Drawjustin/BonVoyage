import React from 'react';
import styles from './Heading.module.scss';

interface HeadingProps {
  title: string;
  subtitle?: string;
  center?: boolean;
}

const Heading: React.FC<HeadingProps> = ({
  title,
  subtitle,
  center
}: HeadingProps) => {
  return (
    <div className={`${styles.headingContainer} ${center ? styles.textCenter : styles.textStart}`}>
      <div className={`${styles.title}`}>
        {title}
      </div>

      {subtitle && (
        <div className={`${styles.subtitle}`}>
          {subtitle}
        </div>
      )}
    </div>
  );
};

export default Heading;
