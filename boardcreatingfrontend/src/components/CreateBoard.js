import React, {useState} from 'react'
import Button from "./Button";
import { TiPlus } from "react-icons/ti";

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
            });

            const newBoardList = await fetch('http://localhost:8080/boards')
            const newData = await newBoardList.json()
            setBoards(newData)

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
            <Button className='button-delete' onClick={sendData} icon={<TiPlus/>}></Button>
        </div>
    )
}

export default CreateBoard