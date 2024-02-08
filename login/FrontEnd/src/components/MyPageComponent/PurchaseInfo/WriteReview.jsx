'use client'
import React, { useState } from "react";
import styles from './WriteReview.module.scss';
import "react-quill/dist/quill.snow.css";
import ReactQuill from "react-quill";

Quill.register('modules/imageActions', ImageActions);
Quill.register('modules/imageFormats', ImageFormats);


const WriteReview = ({ onSUbmit }) => {
    const [title, setTitle] = useState('');
    const [quillValue, setQuillValue] = useState("");
    const [imageFile, setImageFile] = useState(null);

    const handleTitleChange = (e) => {
        setTitle(e.target.value);
    };
    
    const handleQuillChange = (content, delta, source, editor) => {
        setQuillValue(editor.getContents());
    };

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        setImageFile(file);
    };

    const handleSubmit = () => {
        console.log('제목', title);
        console.log('내용', quillValue);
        console.log('이미지 파일', imageFile);

        // 제출을 처리하는 함수
        onsubmit({ title, quillValue, imaggeFile });

        setTitle('');
        setQuillValue('');
        setImageFile(null);
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
            <h3>리뷰 작성하기</h3>

            <div className={styles.formGroup}>
                <input 
                    type="text"
                    id="title"
                    value={title}
                    onChange={handleTitleChange}
                    placeholder="제목을 입력하세요." 
                />

            </div>
            <input 
                type="file"
                onChange={handleImageChange}
                accept="image/*" 
            />

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

export default WriteReview