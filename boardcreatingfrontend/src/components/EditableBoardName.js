import React, { useState } from 'react';

function EditableBoardName({board, setBoard, initialText }) {
    const [isEditing, setIsEditing] = useState(false);
    const [text, setText] = useState(initialText); // Текущий текст


    const handleTextClick = () => {
        setIsEditing(true);
    }


    const handleTextChange = async () => {
        await fetch('http://localhost:8080/boards/' + board.id + '/edit', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({name: text})
        })

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