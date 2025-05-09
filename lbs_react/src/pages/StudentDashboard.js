import '../assets/css/StudentDashboard.css';
import StudentHeader from '../components/StudentHeader';
import Footer from '../components/Footer';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function StudentDashboard()
{
    const [studentName, setStudentName] = useState('');
    const [approvedBorrows, setApprovedBorrows] = useState([]);
    const [pendingBorrows, setPendingBorrows] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        // Get logged in student's data
        const storedStudent = localStorage.getItem('loggedInStudent');

        // Check if student data exists
        if(storedStudent)
        {
            const studentData = JSON.parse(storedStudent);
            console.log('Student Data:', studentData);
            setStudentName(studentData.firstName + ' ' + studentData.lastName);
            const studentEmail = studentData.email;

            const fetchApprovedBorrows = async () =>
            {
                try
                {
                    const response = await fetch(`http://localhost:8080/api/borrows/getapprovedborrows/${studentEmail}`);

                    if(response.ok)
                    {
                        const fetchedApprovedBorrows = await response.json();
                        console.log('Approved borrows:', fetchedApprovedBorrows);
                        setApprovedBorrows(fetchedApprovedBorrows);
                    }
                    else
                    {
                        throw new Error('Failed to fetch approved borrows');
                    }
                }
                catch(error)
                {
                    console.error(error);
                }
            };

            const fetchPendingBorrows = async () =>
            {
                try
                {
                    const response = await fetch(`http://localhost:8080/api/borrows/getpendingborrows/${studentEmail}`);

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

            fetchApprovedBorrows();
            fetchPendingBorrows();
        }
        // If none, redirect to login page
        else
        {
            navigate('/login');
        } 
    }, [navigate]);

    return (
        <>
            <title> Student Dashboard - Laptop Borrowing System </title>

            <StudentHeader/>

            <main className="studentdashboard">
                <h1> Welcome student, {studentName}! </h1>

                <h2> Approved Borrows </h2>

                <table className="approvedborrows">
                    <thead>
                        <tr>
                            <th> Borrow Date </th>
                            <th> Return Date </th>
                            <th> Laptop </th>
                            <th> Memory </th>
                            <th> CPU </th>
                            <th> Reason </th>
                            <th> Borrow Status </th>
                        </tr>
                    </thead>

                    <tbody>
                        {approvedBorrows.map(borrow =>
                        (
                            <tr>
                                <td> {borrow.borrowDate} </td>
                                <td> {borrow.returnDate} </td>
                                <td> {borrow.laptop.brand} - {borrow.laptop.model} </td>
                                <td> {borrow.laptop.memory} </td>
                                <td> {borrow.laptop.cpu} </td>
                                <td> {borrow.reason} </td>
                                <td> {borrow.borrowStatus} </td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <h2> Pending Borrows </h2>

                <table className="pendingborrows">
                    <thead>
                        <tr>
                            <th> Borrow Date </th>
                            <th> Laptop </th>
                            <th> Memory </th>
                            <th> CPU </th>
                            <th> Reason </th>
                            <th> Borrow Status </th>
                        </tr>
                    </thead>

                    <tbody>
                        {pendingBorrows.map(borrow =>
                        (
                            <tr>
                                <td> {borrow.borrowDate} </td>
                                <td> {borrow.laptop.brand} - {borrow.laptop.model} </td>
                                <td> {borrow.laptop.memory} </td>
                                <td> {borrow.laptop.cpu} </td>
                                <td> {borrow.reason} </td>
                                <td> {borrow.borrowStatus} </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </main>

            <Footer/>
        </>
    );
}