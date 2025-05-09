import '../assets/css/Contact.css';
import Header from '../components/Header';
import Footer from '../components/Footer';

export default function Contact()
{
    return (
        <>
            <title> Contact - Laptop Borrowing System </title>

            <Header/>

            <main className="contact">
                <h1> Contact Us </h1>
                <p>Have any questions? Feel free to reach out to us.</p>

                <div className="contactinfo">
                    <p>
                        <strong>Email:</strong> citulaptopborrowingsystem@gmail.com <br/>
                        <strong>Phone:</strong> +63 912 345 6789 <br/>
                        <strong>Address:</strong> CIT-U, N. Bacalso Ave, Cebu City, Philippines
                    </p>
                </div>

                <form>
                    <label htmlFor="name">Name</label>
                    <input type="text" id="name" name="name" required=""/>
            
                    <label htmlFor="email">Email</label>
                    <input type="email" id="email" name="email" required=""/>
            
                    <label htmlFor="message">Message</label>
                    <textarea id="message" name="message" rows="5" required=""></textarea>
            
                    <button type="submit">Send Message</button>
                </form>
            </main>

            <Footer/>
        </>
    );
}