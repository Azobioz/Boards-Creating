import React from 'react'
import { Link } from 'react-router-dom'
import Button from "./Button"
import { TiDelete } from "react-icons/ti"
import {request} from "./axios_helper";




function BoardPreview ({board, onDelete}) {

    const deleteBoard = async () => {

        await request('DELETE', '/boards ' + board.id + '/delete')

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