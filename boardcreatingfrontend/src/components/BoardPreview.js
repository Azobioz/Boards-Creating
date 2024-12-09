import React from 'react'
import {Link} from 'react-router-dom'
function BoardPreview ({board}) {
    return (
            <Link to={'/boards/' + board.id} className="block">
                {board.name + " " + board.id}
            </Link>
    )

}

export default BoardPreview