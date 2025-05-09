import '../assets/css/BrowseLaptops.css';
import StudentHeader from '../components/StudentHeader';
import Footer from '../components/Footer';
import React, { useState, useEffect } from 'react';

const BrowseLaptops = () => {
    const [laptops, setLaptops] = useState([]);
    const [loading, setLoading] = useState(true);
    const [formLaptop, setFormLaptop] = useState({})
    const [show, setShow] = useState(false);
    const [model, setModel] = useState('');

    const [formData, setFormData] = useState({
        borrowDate: '',
        borrowStatus: '',
        reason: '',
    });
    
    useEffect(() => {
        fetch('http://localhost:8080/api/laptops')
            .then(response => response.json())
            .then(data => {
                setLaptops(data);
                setLoading(false);
            })
            .catch(error => {
                console.error('Error fetching laptops:', error);
                setLoading(false);
            });
    }, []);

    const handleClick = (laptop) =>
    {
        if(laptop.laptopStatus === 'AVAILABLE')
        {   
            console.log(laptop);
            setFormLaptop(laptop);
            setModel(laptop.model);
            setShow(true);
        }
        else
        {
            alert('Laptop unavailable for borrowing');
        }
    }

    const handleSubmit = async (e) =>
    {
        e.preventDefault();
        const storedStudent = localStorage.getItem('loggedInStudent');
        const studentData = JSON.parse(storedStudent);
        const studentEmail = studentData.email;
        console.log(formData);

        try
        {
            const response = await fetch(`http://localhost:8080/api/borrows/createborrowrecord/${studentEmail}/${model}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData),
            });

            if(response.ok)
            {
                console.log('Borrow request added!');
                alert('Borrow request added!');
            }
            else
            {
                throw new Error('Borrowing failed');
            }
        }
        catch(error)
        {
            console.error(error);
        }
    }

    return (
        <>
            <title> Browse Laptops - Laptop Borrowing System </title>

            <StudentHeader/>

            <main className="browse-laptops">
                <h1>Browse Laptops</h1>

                {loading ? (<p>Loading laptops...</p>) : 
                (
                    <div className="laptop-grid">
                        {laptops.length === 0 ? (<p>No laptops available.</p>) : 
                        (
                            laptops.map(laptop => 
                            ( 
                                <div key={laptop.id} className="laptop-card" onClick={() => handleClick(laptop)}>
                                    <h2>{laptop.brand} - {laptop.model}</h2>
                                    <p>Memory: {laptop.memory}</p>
                                    <p>CPU: {laptop.cpu}</p> 
                                    <p>Status: <span className={laptop.laptopStatus === 'AVAILABLE' ? 'status1' : 'status2'}>{laptop.laptopStatus}</span></p>
                                </div>  
                            ))
                        )}
                    </div>
                )}

                { show ? 
                    <form className="borrowform" onSubmit={handleSubmit}>
                        <h2> Borrow this laptop? </h2>
                        <p> 
                            {formLaptop.brand} - {formLaptop.model} <br/>
                            {formLaptop.memory}, {formLaptop.cpu} 
                        </p>
                        <label htmlFor="reason"> Reason </label>
                        <textarea id="reason" name="reason" rows="5" value={formData.reason} onChange={(e) => setFormData({reason: e.target.value, borrowDate: new Date().toISOString().slice(0, 10), borrowStatus: 'REVIEW'})} required/>
                        <div>
                            <button id="submit" type="submit"> Submit </button>
                            <button id="close" type="button" onClick={() => setShow(false)}> Close </button>
                        </div>
                    </form> : ''
                }
                
            </main>

            <Footer/>
        </>
    );
};

export default BrowseLaptops;
