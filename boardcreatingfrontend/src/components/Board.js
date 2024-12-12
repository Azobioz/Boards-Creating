import React, {useEffect, useState} from 'react'
import {useParams} from "react-router";
import BoardHeader from "./BoardHeader";
import SideBar from "./SideBar";

function Board () {
    const {boardId}  = useParams();
    const [board, setBoard] = useState([]);

    useEffect(() => {
        fetchBoard();
    }, [])

    const fetchBoard = async () => {
        try {
            const response = await fetch('http://localhost:8080/boards/' + boardId);
            const data = await response.json();
            setBoard(data);
        }
        catch (error) {
            console.error('Ошибка при получении доски:', error);
        }
    }

    if (board.length === 0) {
        return <div>Загрузка...</div>;
    }

    return (
        <div>
            <BoardHeader board={board} setBoard={setBoard}/>
            <SideBar/>
        </div>
    );
}

export default Board