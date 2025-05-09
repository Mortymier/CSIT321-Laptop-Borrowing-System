import '../assets/css/Header.css';
import citu_logo from '../assets/images/citu_logo.png';
import { useLocation } from 'react-router-dom';

export default function Header()
{
	let location = useLocation();

    return (
        <header>
            <img src={citu_logo} alt="CIT - U Logo"/>

            <div id="title">
				<div id="lbs"> LAPTOP BORROWING SYSTEM </div>
				<div id="ccs"> COLLEGE OF COMPUTER STUDIES </div>
		    </div>	

            <nav>
				<ul>
					<li> <a id={location.pathname === '/' ? 'current' : ''} href="/">Home</a> </li>
					<li> <a id={location.pathname === '/about' ? 'current' : ''} href="about">About</a> </li>
					<li> <a id={location.pathname === '/contact' ? 'current' : ''} href="contact">Contact</a> </li>
					<li> <a id={location.pathname === '/login' ? 'current' : ''} href="login">Login</a> </li>
					<li> <a id={(location.pathname === '/register' || location.pathname === '/registerstudent') ? 'current' : ''} href="register">Register</a> </li>
				</ul>
			</nav>
        </header>
    );
}