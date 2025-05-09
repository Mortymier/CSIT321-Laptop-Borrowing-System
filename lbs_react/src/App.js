import './assets/css/Global.css'
import Home from './pages/Home';
import About from './pages/About';
import Contact from './pages/Contact';
import Login from './pages/Login';
import Register from './pages/Register';
import RegisterStudent from './pages/RegisterStudent';
import RegisterStaff from './pages/RegisterStaff';
import StudentDashboard from './pages/StudentDashboard';
import BrowseLaptops from './pages/BrowseLaptops';
import AddLaptop from './pages/AddLaptop';
import NoPage from './pages/NoPage';
import {BrowserRouter, Routes, Route} from 'react-router-dom';

export default function App() 
{
  return (
    <>
      <BrowserRouter> 
        <Routes>
          <Route index element={<Home/>}/>
          <Route path="about" element={<About/>}/>
          <Route path="contact" element={<Contact/>}/>
          <Route path="login" element={<Login/>}/>
          <Route path="register" element={<Register/>}/>
          <Route path="registerstudent" element={<RegisterStudent/>}/>
          <Route path="registerstaff" element={<RegisterStaff/>}/>
          <Route path="studentdashboard" element={<StudentDashboard/>}/>
          <Route path="browselaptops" element={<BrowseLaptops/>}/>
          <Route path="addlaptop" element={<AddLaptop/>}/>
          <Route path="*" element={<NoPage/>}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}
