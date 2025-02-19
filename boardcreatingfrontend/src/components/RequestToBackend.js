import axios from 'axios'

const requestToBackend = axios.create({
    baseURL: 'http://localhost:8080' // при вызове у requestToBackend методов get, post и .. будет использоваться это url
})

requestToBackend.interceptors.request.use(config => {
    const token = localStorage.getItem("token")
    if (token) {
        config.headers.Authorization = `Bearer ${token}`
    }
    return config
})

export default requestToBackend

