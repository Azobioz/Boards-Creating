import React, {useEffect, useState} from 'react'
import Board from "./Board";

function Boards() {
    const [boards, setBoards] = useState([]);

    useEffect(() => {
        fetchBoards()
    }, [])

    const fetchBoards = async () => {
        try {
            const response = await fetch('http://localhost:8080/boards')
            const data = await response.json()
            setBoards(data)
        }
        catch (error) {
            console.error('Ошибка при получении boards: ' + error)
        }
    }

    return (
        <div className="boards">
            {boards.map(board => (
                <Board key={board.id} board={board}/>
            ))}
        </div>
    )
}

export default Boards