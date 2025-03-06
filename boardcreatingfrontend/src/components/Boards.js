import React, {useEffect, useState} from 'react'
import BoardPreview from "./BoardPreview";

import { request, setAuthHeader } from "./axios_helper";
import CreateBoard from "./CreateBoard";

function Boards({ boards, setBoards }) {
    const [userId, setUserId] = useState(null);

    useEffect(() => {
        fetchBoards();
    }, []);

    const fetchBoards = async () => {
        try {
            const boardsResponse = await request('GET', '/boards', {});
            console.log('Boards response:', boardsResponse.data);
            setBoards(boardsResponse.data);
        } catch (error) {
            console.error('Ошибка при получении пользователя или досок: ' + error);
            setAuthHeader(null);
        }
    };

    const handleDeleteBoard = (boardId) => {
        setBoards(prevBoards => prevBoards.filter(board => board.id !== boardId));
    };

    if (!boards) {
        return <div>Загрузка...</div>;
    }

    return (
        <div>
            <div className="boards">
                {boards.map(board => (
                    <BoardPreview key={board.id} board={board} onDelete={handleDeleteBoard} />
                ))}
            </div>
        </div>
    );
}

export default Boards