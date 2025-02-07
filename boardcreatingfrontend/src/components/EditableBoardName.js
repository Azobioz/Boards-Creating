import React, { useState } from 'react';
import axios from "axios";

function EditableBoardName({board, setBoard, initialText }) {
    const [isEditing, setIsEditing] = useState(false);
    const [text, setText] = useState(initialText); // Текущее название доски


    const handleTextClick = () => {
        setIsEditing(true);
    }


    const handleTextChange = async () => {
        axios.put('http://localhost:8080/boards/' + board.id + '/edit', {name: text})
    }

    // Обработчик нажатия Enter
    const handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            handleTextChange()
            setIsEditing(false);
        }
    }

    return (
        <div className='board-header-board-name'>
            {isEditing ? (
                <input
                    type="text"
                    value={text}
                    onChange={ e => setText(e.target.value)}
                    onKeyDown={handleKeyDown}
                    onBlur={() => setIsEditing(false)}
                    autoFocus
                />
            ) : (
                <span onClick={handleTextClick}>{text}</span>
            )}
        </div>
    )
}

export default EditableBoardName;