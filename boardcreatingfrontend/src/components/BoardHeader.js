import React from 'react'
import EditableBoardName from "./EditableBoardName";

function BoardHeader ({board, setBoard}) {

    return (
        <div className='board-header'>
            <header>
                <div className='board-header-logo-text'>Board <br/>Creating</div>
                <div className='block-separate1'></div>
                <EditableBoardName board={board} setBoard={setBoard} initialText={board.name} className='board-header-board-name'/>
                <div className='block-separate2'></div>
            </header>
        </div>
    )

}

export default BoardHeader