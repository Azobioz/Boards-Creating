import React, {useEffect, useState} from 'react'
import BoardPreview from "./BoardPreview";
import CreateBoard from "./CreateBoard";

function Boards({boards, setBoards}) {

    useEffect(() => {
        fetchBoards()
    }, [])

    const fetchBoards = async () => {
        try {
            const response = await fetch('http://localhost:8080/boards')
            const data = await response.json()
            console.log(data)
            setBoards(data)
        }
        catch (error) {
            console.error('Ошибка при получении boards: ' + error)
        }
    }

    const handleDeleteBoard = (boardId) => {
        setBoards(prevBoards => prevBoards.filter(board => board.id !== boardId));
    }



    return (
        <div>
            <div className="boards">
                {boards.map(board => (
                    <BoardPreview key={board.id} board={board} onDelete={handleDeleteBoard} />
                ))}
            </div>
        </div>
    )
}

export default Boards