import React from 'react';
import '../design/style.css'

function NavigationBar ({children}) {

    return (
        <header className="header">
            {children}
        </header>
    )

}

export default NavigationBar;