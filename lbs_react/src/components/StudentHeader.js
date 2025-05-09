import '../assets/css/Header.css';
import citu_logo from '../assets/images/citu_logo.png';
import { useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

export default function StudentHeader()
{
    let location = useLocation();
    const navigate = useNavigate();

    const handleLogout = () =>
    {
        localStorage.removeItem('loggedInStudent');
        console.log('Student has logged out');
        navigate('/login');
    };

    return (
        <header>
            <img src={citu_logo} alt="CIT - U Logo"/>

            <div id="title">
			    <div id="lbs"> LAPTOP BORROWING SYSTEM </div>
				<div id="ccs"> COLLEGE OF COMPUTER STUDIES </div>
		    </div>

            <nav>
				<ul>
					<li> <a id={location.pathname === '/studentdashboard' ? 'current' : ''} href="/studentdashboard">Dashboard</a> </li>
                    <li> <a href="/studentdashboard"> Browse </a> </li>
                    <button onClick={handleLogout}> Logout </button>
				</ul>
			</nav>
        </header>
    );
}