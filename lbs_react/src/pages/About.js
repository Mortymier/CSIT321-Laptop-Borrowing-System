import '../assets/css/About.css';
import Header from '../components/Header';
import Footer from '../components/Footer';
import laptop_about from '../assets/images/laptop_about.jpg';

export default function About()
{
    return (
        <>
            <title> About - Laptop Borrowing System </title>

            <Header/>

            <main id="about">
                <div id="aboutgrid"> 
                    <img src={laptop_about} alt="Student using laptop"/>
                    <div>
                        <h2> About Us </h2>
                        <p>
                            Welcome to <strong>CCS Laptop Borrowing System!</strong> <br/> <br/>

                            This is an innovative platform designed to provide students with easy <br/>
                            and efficient access to laptops for academic use. <br/> <br/>

                            Developed by students, our system aims to streamline the borrowing <br/>
                            process, ensuring that fellow students have the necessary tools to <br/>
                            enhance their learning experience.
                        </p>
                        <button> See More </button>
                    </div>
                </div>
                
            </main>

            <Footer/>
        </>
    )
}