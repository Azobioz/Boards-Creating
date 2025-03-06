import {BrowserRouter, Navigate, Route, Router, Routes} from "react-router"
import MainPage from "./pages/MainPage";
import Board from "./components/Board";
import RegisterPage from "./components/RegisterPage";
import LoginPage from "./components/LoginPage";
import {getAuthToken} from "./components/axios_helper";

function PrivateRoute({children}) {
    const token = getAuthToken()
    return token && token !== null ? children : <Navigate to="/authenticate" /> //PrivateRoute проверяет наличие токена в localStorage. Если токена нет, пользователь перенаправляется на страницу входа.
}

function App() {
  return (

    <BrowserRouter>
        <Routes>
           <Route path='/boards' element={<PrivateRoute><MainPage/></PrivateRoute>}/>
           <Route path='/boards/:boardId' element={<PrivateRoute><Board /></PrivateRoute>} />
           <Route path='/register' element={<RegisterPage/>} />
           <Route path='/authenticate' element={<LoginPage/>} />
        </Routes>
    </BrowserRouter>
  )
}

export default App;
