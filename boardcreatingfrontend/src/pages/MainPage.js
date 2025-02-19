import React, {useState} from 'react'
import NavigationBar from "../components/NavigationBar";
import CreateBoard from "../components/CreateBoard";
import Boards from "../components/Boards";


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