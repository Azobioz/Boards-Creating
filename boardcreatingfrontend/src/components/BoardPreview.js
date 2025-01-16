import React from 'react'
import { Link } from 'react-router-dom'
import Button from "./Button"
import { TiDelete } from "react-icons/ti"


function BoardPreview ({board, onDelete}) {

    const deleteBoard = async () => {
        const response = await fetch('http://localhost:8080/boards/ ' + board.id + '/delete', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        if (response.ok) {
            console.log('Удаление ' + board.name)
            onDelete(board.id)
        }
    }



    return (
        <div className='block'>
            <Link to={'/boards/' + board.id}>
                {board.name}
            </Link>
            <Button onClick={deleteBoard} icon={<TiDelete />} className='delete-button'/>
        </div>
    )

}

export default BoardPreview