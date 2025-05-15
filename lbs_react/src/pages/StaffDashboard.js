import '../assets/css/StaffDashboard.css';
import StaffHeader from '../components/StaffHeader';
import Footer from '../components/Footer';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function StaffDashboard()
{
    const [staffName, setStaffName] = useState('');
    const [pendingBorrows, setPendingBorrows] = useState([]);
    const [student, setStudent] = useState({});
    const [laptop, setLaptop] = useState({});
    const [borrowStatus, setBorrowStatus] = useState('');
    const navigate = useNavigate();
    let newStatus = '';

    useEffect(() => {
        // Get logged in staff's data
        const storedStaff = localStorage.getItem('loggedInStaff');

        // Check if staff data exists
        if(storedStaff)
        {
            const staffData = JSON.parse(storedStaff);
            console.log('Staff Data:', staffData);
            setStaffName(staffData.firstName + ' ' + staffData.lastName);

            const fetchPendingBorrows = async () =>
            {
                try
                {
                    const response = await fetch(`http://localhost:8080/api/borrows/getpendingborrows`);

                    if(response.ok)
                    {
                        const fetchedPendingBorrows = await response.json();
                        console.log('Pending borrows:', fetchedPendingBorrows);
                        setPendingBorrows(fetchedPendingBorrows);
                    }
                    else
                    {
                        throw new Error('Failed to fetch pending borrows');
                    }
                }
                catch(error)
                {
                    console.error(error);
                }
            };

            fetchPendingBorrows();
        }
        else
        {
            navigate('/');
        }
    }, [navigate]);

    const handleApprove = () =>
    {
        newStatus = 'APPROVED';
    }
    
    const handleReject = () =>
    {
        newStatus = 'REJECTED';
    }

    const handleAction = async (borrow) =>
    {
        console.log(borrow);
        const approveBorrow = borrow;
        console.log(approveBorrow);
        console.log(newStatus);

        try
        {
            const response = await fetch(`http://localhost:8080/api/borrows/updateborrowstatus/${newStatus}`, {
                method: 'PUT',
                headers: 
                {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(approveBorrow),
            });

            if(response.ok && newStatus === 'APPROVED')
            {
                alert('APPROVED: ' + borrow.laptop.brand + ' - ' + borrow.laptop.model + ' - ' + borrow.student.firstName + ' ' + borrow.student.lastName);
                window.location.reload();
            }
            else if(response.ok && newStatus === 'REJECTED')
            {
                alert('REJECTED: ' + borrow.laptop.brand + ' - ' + borrow.laptop.model + ' - ' + borrow.student.firstName + ' ' + borrow.student.lastName);
                window.location.reload();
            }
            else
            {
                throw new Error('Failed to approve request');
            }
        }
        catch(error)
        {
            console.error(error);
        }
    };

    return (
        <>
            <title> Staff Dashboard - Laptop Borrowing System </title>

            <StaffHeader/>

            <main className="staffdashboard">
                <h1> Welcome staff, {staffName} </h1>

                <h2> Pending Borrow Requests </h2>

                <table className="pendingborrows2"> 
                    <thead>
                        <tr>
                            <th> Borrow Date </th>
                            <th> Student </th>
                            <th> Laptop </th>
                            <th> Memory </th>
                            <th> CPU </th>
                            <th> Reason </th>
                            <th> Actions </th>
                        </tr>
                    </thead>

                    <tbody>
                        {pendingBorrows.map(borrow =>
                        (
                            <tr>
                                <td> {borrow.borrowDate} </td>
                                <td> {borrow.student.firstName} {borrow.student.lastName} </td>
                                <td> {borrow.laptop.brand} - {borrow.laptop.model} </td>
                                <td> {borrow.laptop.memory} </td>
                                <td> {borrow.laptop.cpu} </td>
                                <td> {borrow.reason} </td>
                                <td> <button onClick={() => { handleApprove(); handleAction(borrow) }}> APPROVE </button> <button onClick={() => { handleReject(); handleAction(borrow) }}> REJECT </button> </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </main>

            <Footer/>
        </>
    );
}