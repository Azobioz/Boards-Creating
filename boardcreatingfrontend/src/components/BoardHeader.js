import React from 'react'

function BoardHeader ({board}) {

    return (
        <div className='board-header'>
            <header>
                {board.name}
            </header>
        </div>
    )

}

export default BoardHeader