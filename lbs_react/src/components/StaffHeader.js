import '../assets/css/Header.css';
import citu_logo from '../assets/images/citu_logo.png';
import { useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

export default function StaffHeader()
{
    let location = useLocation();
    const navigate = useNavigate();

    const handleLogout = () =>
    {
        localStorage.removeItem('loggedInStaff');
        console.log('Staff has logged out');
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
                    <li> <a id={location.pathname === '/staffdashboard' ? 'current' : ''} href="/staffdashboard">Dashboard</a> </li>
					<li> <a id={location.pathname === '/addlaptop' ? 'current' : ''} href="/addlaptop">Add Laptop</a> </li>
                    <button onClick={handleLogout}> Logout </button>
				</ul>
			</nav>
        </header>
    );
}