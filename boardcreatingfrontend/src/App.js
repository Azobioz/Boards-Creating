import NavigationBar from "./components/NavigationBar";
import {BrowserRouter, Route, Router, Routes} from "react-router"
import MainPage from "./pages/MainPage";
import Board from "./components/Board";

function App() {
  return (

    <BrowserRouter>
        <Routes>
           <Route path='/boards' element={<MainPage/>}/>
           <Route path="/boards/:boardId" element={<Board />} />
        </Routes>

    </BrowserRouter>
  )
}

export default App;
