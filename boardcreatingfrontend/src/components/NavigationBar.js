import React from 'react';
import '../style.css'

function NavigationBar ({children}) {

    return (
        <header className="header">
            {children}
        </header>
    )

}

export default NavigationBar