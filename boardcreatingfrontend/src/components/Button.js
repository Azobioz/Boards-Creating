import React from 'react'

function Button ({onClick}) {

    const buttonStyle = {
        backgroundColor: '#007bff',
        color: 'white',
        border: 'none',
        padding: '10px 20px',
        margin: '5px',
        textDecoration: 'none',
        borderRadius: '5px',
        cursor: 'pointer',
        transition: 'background-color 0.3s ease',
        width: '50px',
        height: '50px'
    };

    return (
        <button onClick={onClick} style={buttonStyle}></button>
    )

}

export default Button