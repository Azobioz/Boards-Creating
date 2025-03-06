import React, {useState} from 'react'
import axios from 'axios'
import axios_helper, {request, setAuthHeader, setAuthToken} from "./axios_helper";
import {useNavigate} from "react-router";

export default function LoginPage() {

    const[username, setUsername] = useState('')
    const[password, setPassword] = useState('')
    const navigate = useNavigate()

    const handleLogin = async () => {
        try {
            const response = await request('POST', '/authenticate', { username, password });
            const { token, refreshToken } = response.data;
            console.log('Server response:', response.data);
            setAuthHeader(token, refreshToken);
            navigate('/boards');
        }
        catch (error) {
            console.error('Ошибка входа:', error)
            setAuthHeader(null, null)
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