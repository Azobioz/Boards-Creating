import Header from "./components/Header";
import {BrowserRouter, Route, Router, Routes} from "react-router"
import MainPage from "./pages/MainPage";

function App() {
  return (

    <BrowserRouter>
        <Routes>
           <Route path='/boards' element={<MainPage/>}/>
        </Routes>
    </BrowserRouter>
  )
}

export default App;
