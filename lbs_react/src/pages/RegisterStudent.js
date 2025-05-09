import '../assets/css/RegisterStudent.css';
import Header from '../components/Header.js';
import Footer from '../components/Footer.js';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function RegisterStudent()
{
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        course: '',
        year: '',
        number: '',
        email: '',
        password: '',
    });

    const navigate = useNavigate();

    const handleChange = (e) => 
    {
        const {name, value} = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };
    
    const handleSubmit = async (e) =>
    {
        e.preventDefault();
        console.log('Submitting form data:', formData);
        window.confirm('Proceed with registration?');

        try
        {
            const response = await fetch('http://localhost:8080/api/students/register', {
                method: 'POST',
                headers:
                {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if(response.ok) 
            {
                const data = await response.json();
                console.log('Registered successfully:', data);
                alert('Registration successful!');
                navigate('/login');
            }
            else if(response.status === 500)
            {   
                throw new Error('Email is already used');
            }
            else
            {
                throw new Error('Registration failed');
            }
        }
        catch(error)
        {
            console.error(error);
            alert(error.message);
        }
    };

    return (
        <>
            <title> Register Student - Laptop Borrowing System </title>

            <Header/>

            <main className="registerstudent">
                <form onSubmit={handleSubmit}>
                    <h1> Register Student </h1>
                    <h2> Please fill out the form </h2>
                    <hr/>

                    <div className="formfield">
                        <label htmlFor="firstName"> First Name </label>
                        <input id="firstName" name="firstName" type="text" value={formData.firstName} onChange={handleChange} required/>
                    </div>
                    
                    <div className="formfield">
                        <label htmlFor="lastName"> Last Name </label>
                        <input id="lastName" name="lastName" type="text" value={formData.lastName} onChange={handleChange} required/>
                    </div>
                    
                    <div className="formfield">
                        <label htmlFor="course"> Course </label>
                        <input id="course" name="course" type="text" value={formData.course} onChange={handleChange} required/>
                    </div>
                    
                    <div className="formfield">
                        <label htmlFor="year"> Year </label>
                        <input id="year" name="year" type="number" min="1" max="6" value={formData.year} onChange={handleChange} required/>
                    </div>

                    <div className="formfield">
                        <label htmlFor="number"> Number </label>
                        <input id="number" name="number" type="text" value={formData.number} onChange={handleChange} required/>
                    </div>
                    
                    <div className="formfield">
                        <label htmlFor="email"> Email </label>
                        <input id="email" name="email" type="email" value={formData.email} onChange={handleChange} required/>
                    </div>
                    
                    <div className="formfield">
                        <label htmlFor="password"> Password </label>
                        <input id="password" name="password" type="password" value={formData.password} onChange={handleChange} required/>
                    </div>
                    
                    <div className="buttons">
                        <a href="register"> <button type="button" id="goBack"> Back </button> </a>
                        <button type="submit" id="submitForm"> Register </button>
                    </div>
                    
                </form>
            </main>

            <Footer/>
        </>
    )
}