import React, {useState} from 'react'
import NavigationBar from "../components/NavigationBar";
import CreateBoard from "../components/CreateBoard";
import Boards from "../components/Boards";
import {Route, Routes} from "react-router";
import Board from "../components/Board";


function MainPage () {

    const [boards, setBoards] = useState([])

    return (
        <div>
            <NavigationBar>
                <CreateBoard setBoards={setBoards}/>
            </NavigationBar>
            <Boards boards={boards} setBoards={setBoards}/>
        </div>
    )

}

export default MainPage