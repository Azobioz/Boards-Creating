import NavigationBar from "./components/NavigationBar";
import {BrowserRouter, Route, Router, Routes} from "react-router"
import MainPage from "./pages/MainPage";
import Board from "./components/Board";
import RegisterPage from "./components/RegisterPage";
import LoginPage from "./components/LoginPage";

function App() {
  return (

    <BrowserRouter>
        <Routes>
           <Route path='/boards' element={<MainPage/>}/>
           <Route path='/boards/:boardId' element={<Board />} />
           <Route path='/register' element={<RegisterPage/>} />
           <Route path='/authenticate' element={<LoginPage/>} />
        </Routes>
    </BrowserRouter>
  )
}

export default App;
