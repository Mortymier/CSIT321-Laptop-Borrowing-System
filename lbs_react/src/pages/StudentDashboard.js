import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function StudentDashboard()
{
    const [studentName, setStudentName] = useState('');
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
        }
        // If none, redirect to login page
        else
        {
            navigate('/login');
        }
    }, [navigate]);

    const handleLogout = () =>
    {
        localStorage.removeItem('loggedInStudent');
        console.log(studentName + ' has logged out');
        navigate('/login');
    };

    return (
        <>
            <h1> Welcome student {studentName} </h1>
            <button onClick={handleLogout}> Logout </button>
        </>
    )
}