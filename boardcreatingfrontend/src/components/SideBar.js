import React from 'react'
import Button from "./Button";
import { BiCustomize } from "react-icons/bi";

function SideBar () {

    return (
        <div className='side-bar-block'>
            <Button icon={<BiCustomize/>}/>
        </div>
    )

}

export default SideBar