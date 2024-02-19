'use client'
import React, { useState } from "react";
import styles from './ProductModal.module.scss';
import "react-quill/dist/quill.snow.css";
import ReactQuill from "react-quill";

const ProductModal = ({ onSUbmit }) => {
    const [title, setTitle] = useState('');
    const [quillValue, setQuillValue] = useState("");

    const handleTitleChange = (e) => {
        setTitle(e.target.value);
    };
    
    const handleQuillChange = (content, delta, source, editor) => {
        setQuillValue(editor.getContents());
    };

    const handleSubmit = () => {
        console.log('제목', title);
        console.log('내용', quillValue);
        onsubmit({ title, quillValue });
        setTitle('');
        setQuillValue('');
    };

    const modules = {
        toolbar: [
            [{ header: [1, 2, false] }],
            ["bold", "italic", "underline", "strike", "blockquote"],
            [
                { list: "ordered" },
                { list: "bullet" },
                { indent: "-1" },
                { indent: "+1" },
            ],
            ["link", "image"],
            [{ align: [] }, { color: [] }, { background: [] }], // dropdown with defaults from theme
            ["clean"],
        ],
    };
    
    const formats = [
        "header",
        "bold",
        "italic",
        "underline",
        "strike",
        "blockquote",
        "list",
        "bullet",
        "indent",
        "link",
        "image",
        "align",
        "color",
        "background",
    ];
    
    return (
        <div className={styles.writeReviewContainer}>
            <h3>판매상품 등록</h3>

            <div className={styles.formGroup}>
                <input 
                    type="text"
                    id="title"
                    value={title}
                    onChange={handleTitleChange}
                    placeholder="상품명을 입력하세요." 
                />

            </div>


            
            <ReactQuill
                className={styles.write}
                style={{ height: "200px" }}
                theme="snow"
                modules={modules}
                formats={formats}
                value={quillValue || ""}
                onChange={handleQuillChange}
            />
            <button className={styles.button} onClick={handleSubmit}>제출</button>
        </div>
    );
};

export default ProductModal