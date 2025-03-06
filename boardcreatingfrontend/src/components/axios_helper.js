import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080' //при вызове у axios_helper методов get, post и .. будет использоваться этот url
axios.defaults.headers.post['Content-Type'] = 'application/json'

export const getAuthToken = () => {
    return window.localStorage.getItem('auth_token')
}

export const getRefreshToken = () => {
    return window.localStorage.getItem('refresh_token')
}

export const setAuthHeader = (token, refreshToken) => {
    window.localStorage.setItem('refresh_token', refreshToken)
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

//  Interceptor для обработки 401 и обновления токена
axios.interceptors.response.use(
    response => response,
    async error => {
        const originalRequest = error.config;
        if (error.response && error.response.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true;
            let retryCount = 0;
            const maxRetries = 1;

            while (retryCount < maxRetries) {
                try {
                    const refreshToken = getRefreshToken();
                    if (!refreshToken) {
                        console.error('Refresh token missing in localStorage');
                        setAuthHeader(null, null);
                        window.location.href = '/authenticate';
                        return Promise.reject(new Error('Refresh token missing'));
                    }
                    console.log('Attempting to refresh token with:', refreshToken);
                    const response = await axios.post('/refresh', refreshToken, {
                        headers: { 'Content-Type': 'text/plain' }
                    });
                    const { token, refreshToken: newRefreshToken } = response.data;
                    setAuthHeader(token, newRefreshToken);
                    originalRequest.headers['Authorization'] = `Bearer ${token}`;
                    return axios(originalRequest);
                } catch (refreshError) {
                    console.error('Refresh token error:', refreshError.response ? refreshError.response.data : refreshError.message);
                    retryCount++;
                    if (retryCount === maxRetries) {
                        setAuthHeader(null, null);
                        console.log('Redirecting to /authenticate due to refresh failure');
                        window.location.href = '/authenticate';
                        return Promise.reject(refreshError);
                    }
                }
            }
        }
        return Promise.reject(error);
    }
);



