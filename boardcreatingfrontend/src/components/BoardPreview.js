import React from 'react'
import {Link} from "react-router";

function BoardPreview ({board}) {
    return (
        <div className="block">
            <Link to={'/boards/' +board.id}/>
            {board.name + " " + board.id}
        </div>
    )

}

export default BoardPreview