import React, {useState} from 'react'
import axios from 'axios'
import requestToBackend from "./RequestToBackend";

export default function LoginPage() {

    const[username, setUsername] = useState('')
    const[password, setPassword] = useState('')

    const handleLogin = async () => {
        try {
            const response = await requestToBackend.post('/authenticate', {
                username, //данные в теле запроса
                password
            })
            localStorage.setItem('token', response.data.token) // Сохранение токена в localStorage
            console.log('Успешный вход:');
        }
        catch (error) {
            console.error('Ошибка входа:', error)
        }
    }

    return (
        <div>
            <h1>Board Creating</h1>
            <label>Username</label>
            <input placeholder='Username' value={username} onChange={e => setUsername(e.target.value)}/>
            <label>Password</label>
            <input placeholder='Password' value={password} onChange={e => setPassword(e.target.value)}/>
            <button onClick={handleLogin}>Login</button>
        </div>
    )

}