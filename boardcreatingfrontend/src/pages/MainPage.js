import React, {useEffect, useState} from 'react'
import NavigationBar from "../components/NavigationBar";
import CreateBoard from "../components/CreateBoard";
import Boards from "../components/Boards";
import {request} from "../components/axios_helper";


function MainPage () {

    const [boards, setBoards] = useState([])
    const [userId, setUserId] = useState(null)

    useEffect(() => {
        fetchUser();
    }, [])


    const fetchUser = async () =>  {
        try {
            const userResponse = await request('GET', '/current-user');
            setUserId(userResponse.data.id)
            console.log('Current user: ' + userResponse.data.username)
        }
        catch (error) {
            console.log('Ошибка в MainPage: ' + error)
        }
    }

    return (
        <div>
            <NavigationBar>
                <CreateBoard setBoards={setBoards} userId={userId}/>
            </NavigationBar>
            <Boards boards={boards} setBoards={setBoards}/>
        </div>
    )

}

export default MainPage