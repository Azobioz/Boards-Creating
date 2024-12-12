import React from 'react'
import EditableBoardName from "./EditableBoardName";
import {Link} from "react-router-dom";

function BoardHeader ({board, setBoard}) {

    return (
        <div className='board-header'>
            <header>
                <Link to={'/boards'} className='board-header-logo-text'>
                    Board<br/> {'Creating'}
                </Link>
                <div className='block-separate1'></div>
                <EditableBoardName board={board} setBoard={setBoard} initialText={board.name} />
                <div className='block-separate2'></div>
            </header>
        </div>
    )

}

export default BoardHeader