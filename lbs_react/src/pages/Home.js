import '../assets/css/Home.css';
import Header from '../components/Header';
import Footer from '../components/Footer';
import laptop_hero from '../assets/images/laptop_hero.jpg';

export default function Home()
{
    return (
        <>
            <title> Laptop Borrowing System </title>

            <Header/>

            <main id="home">
                <img src={laptop_hero} alt="A woman handing someone a laptop"/>
                <div id="homeinfo">
                    <h2> WE GOT YOU COVERED </h2>
                    <p>
                        Need a laptop for your next assignment? <br/>
                        The CCS Office is open for borrowing laptops. <br/> <br/>
                        <a href="register">Register</a> now and start borrowing!
                    </p>
                </div>
            </main>

            <Footer/>
        </>
    );
}