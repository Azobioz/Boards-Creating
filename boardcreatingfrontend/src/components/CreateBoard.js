import React, {useState} from 'react'
import Button from "./Button";
import { TiPlus } from "react-icons/ti";
import axios from 'axios'
import requestToBackend from "./RequestToBackend";

function CreateBoard ({setBoards}) {

    const [data, setData] = useState({
        name: 'Board'
    })

    const sendData = async () => {
        try {
            const createBoard = await requestToBackend.post('/boards/create', {
                name
            })

            requestToBackend.get('/boards')
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