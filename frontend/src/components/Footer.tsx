import GhLogo from '../img/github-mark-white.svg';
import './Header.sass';
import { Navigation } from './Navigation';

export function Footer() {
    return (
        <footer className="header footer">
            <div className="container">
                <div className="header__wrap">
                    <a href="" className="header__logo">
                        SOA Lab2
                    </a>
                    <Navigation />
                    <div className="header__links">
                        <a href="https://github.com/maximax444/soa2-front" target='_blank' className="header__git"><img src={GhLogo} alt="" /> Frontend</a>
                        <a href="https://github.com/LinaMalinaMeow/soa" target='_blank' className="header__git"><img src={GhLogo} alt="" /> Backend</a>
                    </div>
                </div>
            </div>
        </footer>
    )
}