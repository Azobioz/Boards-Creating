import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080' //при вызове у axios_helper методов get, post и .. будет использоваться этот url
axios.defaults.headers.post['Content-Type'] = 'application/json'

export const getAuthToken = () => {
    return window.localStorage.getItem('auth_token')
}

export const setAuthHeader = (token) => {
    window.localStorage.setItem('auth_token', token)
}

export const request = (method, url, data) => {
    let headers = {}
    if (getAuthToken() !== null && getAuthToken() !== 'null') {
        headers = {'Authorization': `Bearer ${getAuthToken()}`}
    }


    return axios({
        method: method,
        url: url,
        headers: headers,
        data: data
    })
}



