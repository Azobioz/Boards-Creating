import React, {useState} from 'react'
import {request, setAuthHeader, setAuthToken} from "./axios_helper";
import {useNavigate} from "react-router";

export default function RegisterPage () {

    const[username, setUsername] = useState('')
    const[email, setEmail] = useState('')
    const[password, setPassword] = useState('')
    const[confirmPassword, setConfirmPassword] = useState('')
    const navigate = useNavigate()

    const handleSubmit = async (e) =>  {
        e.preventDefault()
        if (password !== confirmPassword) {
            console.log('Пароли не совпадают ')
            return
        }
        try {
            const response = await request('POST', '/register', { username, password, email });
            const { token, refreshToken } = response.data;
            console.log('Server response:', response.data);
            setAuthHeader(token, refreshToken);
            navigate('/boards');
        }
        catch (error) {
            console.log('Ошибка: ' + error)
        }
    }

    return (
        <div>
            <h1>BoardCreating</h1>
            <form onSubmit={handleSubmit}>
                <label>Username</label>
                <input type='text' value={username} onChange={e => setUsername(e.target.value)} required />
                <label>Email</label>
                <input type='email' value={email} onChange={e => setEmail(e.target.value)} required />
                <label>Password</label>
                <input type='password' value={password} onChange={e => setPassword(e.target.value)} required />
                <label>Confirm password</label>
                <input type='password' value={confirmPassword} onChange={e => setConfirmPassword(e.target.value)} required />
                <button type='submit'>Sign up </button>
            </form>
        </div>
    )


}