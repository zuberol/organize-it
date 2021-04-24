import { NavLink } from 'react-router-dom';
import '../static/css/commons.css';

//todo dorzucic linki, naprawic buga ze sie '/' swieci jako aktywny caly czas
export default function Header() {
    const activeStyle = {
        fontWeight: "bold",
        color: "gold"
    }
    const navStyle = {
        color: 'inherit',
        fontFamily: 'inherit',
        fontSize: '2rem',
        fontWeight: 'bolder'
    }

    return (
        <header>
            <NavLink
                to="/"
                activeStyle={activeStyle}
                style={navStyle}
            >organize-it</NavLink>
            {/* <a className="" href="/decks/modify">
                <h5 className="logo-title">modify Sets</h5>
            </a>
            <a className="" href="/meal/add">
                <h5 className="logo-title">add meal</h5>
            </a> */}
            <NavLink
                to="/dev"
                activeStyle={activeStyle}
                style={navStyle}
            >dev</NavLink>
            <NavLink
                to="/decks"
                activeStyle={activeStyle}
                style={navStyle}
            >Decks</NavLink>
            <NavLink
                to="/projects"
                activeStyle={activeStyle}
                style={navStyle}
            >Projects</NavLink>
        </header>
    )
}