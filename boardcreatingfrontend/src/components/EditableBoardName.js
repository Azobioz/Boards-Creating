import React, { useState } from 'react';

function EditableBoardName({board, setBoard, initialText }) {
    const [isEditing, setIsEditing] = useState(false); // Состояние для отслеживания режима редактирования
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
        <div>
            {isEditing ? (
                <input
                    type="text"
                    value={text}
                    onChange={ e => setText(e.target.value)}
                    onKeyDown={handleKeyDown}
                    onBlur={() => setIsEditing(false)} // Выходим из режима редактирования, если поле теряет фокус
                    autoFocus // Автоматически фокусируемся на поле ввода
                />
            ) : (
                <span onClick={handleTextClick}>{text}</span>
            )}
        </div>
    )
}

export default EditableBoardName;