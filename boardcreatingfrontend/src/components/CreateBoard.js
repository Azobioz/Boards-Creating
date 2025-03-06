import React, {useEffect, useState} from 'react'
import Button from "./Button";
import { TiPlus } from "react-icons/ti";
import axios from 'axios'
import axios_helper, {request} from "./axios_helper";

function CreateBoard({ setBoards, userId }) {
    const [data, setData] = useState({
        name: 'Board'
    });

    const sendData = async () => {
        if (!userId) {
            console.error('User ID not available');
            return;
        }
        try {
            const boardDto = {
                name: data.name,
                userId: userId // Передаем ID текущего пользователя
            };
            await request('POST', '/boards/create', boardDto);

            const response = await request('GET', '/boards');
            setBoards(response.data);

            console.log('Доска успешно создана');
        } catch (error) {
            console.error('Ошибка при создании доски: ' + error);
        }
    };

    return (
        <div>
            <Button className='button-create-block' onClick={sendData} icon={<TiPlus />}></Button>
        </div>
    );
}

export default CreateBoard