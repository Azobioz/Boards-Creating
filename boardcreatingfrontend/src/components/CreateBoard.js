import React, {useState} from 'react'
import Button from "./Button";
import { TiPlus } from "react-icons/ti";

function CreateBoard () {

    const [data, setData] = useState({
        name: 'Board'
    })

    const sendData = async () => {
        try {
            const response = await fetch('http://localhost:8080/boards/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
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