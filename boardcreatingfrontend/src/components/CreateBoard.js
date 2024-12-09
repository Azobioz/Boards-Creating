import React, {useState} from 'react'
import Button from "./Button";

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
            } else {
                console.error('Ошибка при создании доски');
            }
        } catch (error) {
            console.error('Ошибка при создании доски: ' + error);
        }
    }

    return (
        <div>
            <Button onClick={sendData}></Button>
        </div>
    )
}

export default CreateBoard