import React from 'react';
import '../design/style.css'

function Header ({children}) {

    return (
        <header className="header">
            {children}
        </header>
    )

}

export default Header;