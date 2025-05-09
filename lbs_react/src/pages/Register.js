import '../assets/css/Register.css';
import Header from '../components/Header.js';
import Footer from '../components/Footer.js';

export default function Register()
{
    return (
        <>
            <title> Register - Laptop Borrowing System </title>

            <Header/>

            <main className="register">
                <div>
                    <h1> Register User </h1>
                    <p> Please select your role </p>
                    <hr/>
                    <a href="registerstudent"> <button> Student </button> </a>
                    <p> or </p>
                    <a href="registerstaff"> <button> Staff </button> </a>
                </div>
            </main>

            <Footer/>
        </>
    );
}