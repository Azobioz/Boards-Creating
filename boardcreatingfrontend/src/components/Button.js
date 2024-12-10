import React from 'react'

function Button ({onClick, icon}) {



    return (
        <button onClick={onClick} className="button">
            {icon}
        </button>
    )

}

export default Button