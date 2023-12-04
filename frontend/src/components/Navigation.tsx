import GhLogo from '../img/github-mark.svg';
import './Navigation.sass';
import { Link } from 'react-router-dom';

export function Navigation() {
    return (
        <nav className="nav">
            <Link to="/">Catalog</Link>
            {/* <Link to="/map">Map</Link> */}
            <Link to="/add">New vehicle</Link>
            <Link to="/stat">Stats</Link>
            <Link to="/second">Second Service</Link>
        </nav>
    )
}