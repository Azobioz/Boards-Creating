import React, {useState} from 'react'
import Button from "./Button";
import { TiPlus } from "react-icons/ti";
import axios from 'axios'

function CreateBoard ({setBoards}) {

    const [data, setData] = useState({
        name: 'Board'
    })

    const sendData = async () => {
        try {
            const createBoard = await fetch('http://localhost:8080/boards/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })

            axios.get('http://localhost:8080/boards')
                .then(res => {
                    setBoards(res.data)
                })

            if (createBoard.ok) {
                console.log('Доска успешно создана');
            }
            else {
                console.error('Ошибка при создании доски');
            }
        }
        catch (error) {
            console.error('Ошибка при создании доски: ' + error);
        }
    }

    return (
        <div>
            <Button className='button-create-block' onClick={sendData} icon={<TiPlus/>}></Button>
        </div>
    )
}

export default CreateBoard