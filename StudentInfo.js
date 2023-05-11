
import React, { useState } from 'react';
import './App.css';
import Planner from "./Planner.js"
import Footer from "./footer"
import { Link } from 'react-router-dom';


function StudentInfo() {
  const [degree, setDegree] = useState('');
  const [major, setMajor] = useState('');
  const [cCompleted, setCompleted] = useState('');
  const [semester, setSemester] = useState('');
  const [year, setYear] = useState('');
  const [campus, setCampus] = useState('');
  const [formError, setFormError] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (degree && major && cCompleted && semester && year && campus) {
      // Do something with the form data
      console.log(`Submitted: ${degree}, ${major}, ${cCompleted}, ${semester}, ${year}, ${campus}`);
      // Reset the form state
      setDegree('');
      setMajor('');
      setCompleted('');
      setSemester('');
      setYear('');
      setCampus('');
      setFormError(false);
      // Navigate to the next page
      // You can use React Router or some other method here
    } else {
      // Set an error state if any field is missing
      setFormError(true);
    }
  };

  return (
    <div>
      <h1>STUDENT INFORMATION</h1>
      <p>Please complete the following in its entirely:</p>
      <form onSubmit={handleSubmit}>
        <label htmlFor="degree">Degree:</label>
        <select id="degree" value={degree} onChange={(e) => setDegree(e.target.value)}>
          <option value="">Select a degree</option>
          <option value="Bachelor's">Bachelor in Information Technology</option>
          <option value="Bachelor's">Bachelor in Computer Science</option>

        </select><br/>

        <label htmlFor="major">Major:</label>
        <select id="major" value={major} onChange={(e) => setMajor(e.target.value)}>
          <option value="">Select a major</option>
          <option value="Developer">ICT Developer</option>
          <option value="Professional">ICT Professional</option>
          <option value="CyberSecurity">Cyber Security</option>
        </select><br/>

        <label htmlFor="cCompleted">Select courses that you have completed:</label>
        <select id="cCompleted" value={cCompleted} onChange={(e) => setCompleted(e.target.value)}>
          <option value="">Enter completed courses</option>
          <option value="a">a</option>
          <option value="b">b</option>
          <option value="c">c</option>
        </select><br/>

        <label htmlFor="semester">Which Semester do you want to start in:</label>
        <select id="semester" value={semester} onChange={(e) => setSemester(e.target.value)}>
          <option value="">Select a semester</option>
          <option value="One">Semester 1</option>
          <option value="Two">Semester 2</option>
        </select><br/>

        <label htmlFor="year">How long do you want to complete your degree:</label>
        <select id="year" value={year} onChange={(e) => setYear(e.target.value)}>
          <option value="">Select a year</option>
          <option value="Three">Three</option>
          <option value="Four">Four</option>
          <option value="Five">Five</option>
          <option value="Six">Six</option>
          <option value="Seven">Seven</option>
          <option value="Eight">Eight</option>
        </select><br/>

        <label htmlFor="campus">Campus:</label>
        <select id="campus" value={campus} onChange={(e) => setCampus(e.target.value)}>
          <option value="">Select a campus</option>
          <option value="Callaghan">Callaghan Campus</option>
          
        </select><br/>

        {formError && <p>⚠️ Please fill in all fields before submitting!</p>}

        <Link to="/Planner">
              <button type="submit">Next</button>
        </Link>
        
      </form>
      <Footer/>
    </div>
  );
}

export default StudentInfo;