import React from 'react'

function Board ({board}) {
    return (
        <div className="block">
            {board.name + " " + board.id}
        </div>
    )

}

export default Board