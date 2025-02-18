import NavigationBar from "./components/NavigationBar";
import {BrowserRouter, Route, Router, Routes} from "react-router"
import MainPage from "./pages/MainPage";
import Board from "./components/Board";
import RegisterPage from "./components/RegisterPage";

function App() {
  return (

    <BrowserRouter>
        <Routes>
           <Route path='/boards' element={<MainPage/>}/>
           <Route path='/boards/:boardId' element={<Board />} />
           <Route path='/register' element={<RegisterPage/>} />
        </Routes>
    </BrowserRouter>
  )
}

export default App;
