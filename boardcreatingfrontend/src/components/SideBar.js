import React, {useState} from 'react'
import Button from "./Button";
import { BiCustomize } from "react-icons/bi";
import ShapePanel from "./ShapePanel";

function SideBar () {

    const [showShapePanel, setShapePanel] = useState(false)

    const openShapePanel = () => {
        setShapePanel(!showShapePanel)
    }


    return (

        <div className='side-bar-block'>
            <Button onClick={openShapePanel} icon={<BiCustomize/>}/>
            {showShapePanel && <ShapePanel />}
        </div>
    )

}

export default SideBar