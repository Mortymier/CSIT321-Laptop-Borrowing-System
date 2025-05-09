import '../assets/css/RegisterStaff.css';
import Header from '../components/Header.js';
import Footer from '../components/Footer.js';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function RegisterStaff() 
{
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
    });

    const navigate = useNavigate();

    const handleChange = (e) => 
    {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (e) => 
    {
        e.preventDefault();
        console.log('Submitting staff data:', formData);
        window.confirm('Proceed with staff registration?');

        try {
            const response = await fetch('http://localhost:8080/api/staffs/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                const data = await response.json();
                console.log('Staff registered successfully:', data);
                alert('Staff registration successful!');
                navigate('/login');
            } else if (response.status === 500) {
                throw new Error('Email is already used');
            } else {
                throw new Error('Registration failed');
            }
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    };

    return (
        <>
            <title>Register Staff - Laptop Borrowing System</title>

            <Header />

            <main className="registerstaff">
                <form onSubmit={handleSubmit}>
                    <h1> Register Staff </h1>
                    <h2> Please fill out the form </h2>
                    <hr/>

                    <div className="formfield">
                        <label htmlFor="firstName"> First Name </label>
                        <input id="firstName" name="firstName" type="text" value={formData.firstName} onChange={handleChange} required />
                    </div>

                    <div className="formfield">
                        <label htmlFor="lastName"> Last Name </label>
                        <input id="lastName" name="lastName" type="text" value={formData.lastName} onChange={handleChange} required />
                    </div>

                    <div className="formfield">
                        <label htmlFor="email"> Email </label>
                        <input id="email" name="email" type="email" value={formData.email} onChange={handleChange} required />
                    </div>

                    <div className="formfield">
                        <label htmlFor="password"> Password </label>
                        <input id="password" name="password" type="password" value={formData.password} onChange={handleChange} required />
                    </div>

                    <div className="buttons">
                        <a href="register"> <button type="button" id="goBack"> Back </button> </a>
                        <button type="submit" id="submitForm"> Register </button>
                    </div>
                </form>
            </main>

            <Footer />
        </>
    );
}
