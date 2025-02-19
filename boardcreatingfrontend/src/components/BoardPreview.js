import React from 'react'
import { Link } from 'react-router-dom'
import Button from "./Button"
import { TiDelete } from "react-icons/ti"
import axios from "axios";
import requestToBackend from "./RequestToBackend";


function BoardPreview ({board, onDelete}) {

    const deleteBoard = async () => {

        await requestToBackend.delete('/boards ' + board.id + '/delete')

        console.log("Удаление: " + board.name)
        onDelete(board.id)

    }

    return (
        <div className='block'>
            <Link to={'/boards' + '/' + board.id}>
                {board.name}
            </Link>
            <Button onClick={deleteBoard} icon={<TiDelete />} className='delete-button'/>
        </div>
    )

}

export default BoardPreview