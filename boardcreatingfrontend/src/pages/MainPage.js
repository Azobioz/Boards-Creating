import React from 'react'
import Header from "../components/Header";
import CreateBoard from "../components/CreateBoard";
import Boards from "../components/Boards";


function MainPage () {

    return (
        <div>
            <Header>
                <CreateBoard/>
            </Header>
            <Boards/>
        </div>
    )

}

export default MainPage