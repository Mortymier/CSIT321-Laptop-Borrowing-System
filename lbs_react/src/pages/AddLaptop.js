import '../assets/css/AddLaptop.css'; 
import StaffHeader from '../components/StaffHeader';
import Footer from '../components/Footer';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function AddLaptop() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        brand: '',
        model: '',
        memory: '',
        cpu: '',
        laptopStatus: 'AVAILABLE'
    });

    useEffect(() => {
        const role = localStorage.getItem('userRole');
        if (role !== 'staff') {
            alert('Access denied. Staff only.');
            navigate('/');
        }
    }, [navigate]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/laptops', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                alert('Laptop added successfully!');
                setFormData({
                    brand: '',
                    model: '',
                    memory: '',
                    cpu: '',
                    laptopStatus: 'AVAILABLE'
                });
            } else {
                throw new Error('Failed to add laptop');
            }
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    };

    return (
        <>
            <StaffHeader/>

            <main id="home">
                <section id="homeinfo">
                    <h2>Add Laptop</h2>
                    <p>Staff members can register new laptops to the system below.</p>
                </section>

                <section id="addlaptopform">
                    <form onSubmit={handleSubmit}>
                        <label>Brand</label>
                        <input type="text" name="brand" value={formData.brand} onChange={handleChange} required />

                        <label>Model</label>
                        <input type="text" name="model" value={formData.model} onChange={handleChange} required />

                        <label>Memory</label>
                        <input type="text" name="memory" value={formData.memory} onChange={handleChange} required />

                        <label>CPU</label>
                        <input type="text" name="cpu" value={formData.cpu} onChange={handleChange} required />

                        <button type="submit">Submit</button>
                    </form>
                </section>
            </main>

            <Footer />
        </>
    );
}
