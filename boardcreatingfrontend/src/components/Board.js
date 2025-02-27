import React, {useEffect, useState} from 'react'
import {useParams} from "react-router"
import BoardHeader from "./BoardHeader"
import SideBar from "./SideBar"

import {request, setAuthToken} from './axios_helper'

function Board () {
    const {boardId}  = useParams();
    const [board, setBoard] = useState([]);

    useEffect(() => {
        getBoard();
    }, [])

    const getBoard = async () => {
        try {
           request('GET', '/boards' + boardId)
                .then(res => {setBoard(res.data)})
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