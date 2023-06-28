import React, { useState } from 'react';
import './App.css';
import Planner from "./Planner.js";
import Footer from "./footer";
import { Link } from 'react-router-dom';

function StudentInfo() {
  const [degree, setDegree] = useState('');
  const [major, setMajor] = useState('');
  const [cCompleted, setCompleted] = useState([]);
  const [selectedCourse, setSelectedCourse] = useState('');
  const [semester, setSemester] = useState('');
  const [year, setYear] = useState('');
  const [campus, setCampus] = useState('');
  const [formError, setFormError] = useState(false);
  const [duplicateError, setDuplicateError] = useState(false);

  const degreeOptions = [
    { name: 'Bachelor in Information Technology', majors: ['ICT Developer', 'ICT Professional'] },
    { name: 'Bachelor in Computer Science', majors: ['Cyber Security'] },
  ];

  const handleDegreeChange = (e) => {
    const selectedDegree = e.target.value;
    setDegree(selectedDegree);

    // Find the matching degree object from the options
    const selectedDegreeObject = degreeOptions.find((option) => option.name === selectedDegree);

    // Set the major options based on the selected degree
    if (selectedDegreeObject) {
      setMajor('');
      setMajorOptions(selectedDegreeObject.majors);
    } else {
      setMajor('');
      setMajorOptions([]);
    }
  };

  const setMajorOptions = (majors) => {
    const majorOptions = majors.map((major) => (
      <option key={major} value={major}>
        {major}
      </option>
    ));
    setMajor(majorOptions);
  };

  const handleAddCourse = () => {
    if (selectedCourse && !cCompleted.includes(selectedCourse)) {
      setCompleted([...cCompleted, selectedCourse]);
      setSelectedCourse('');
      setDuplicateError(false);
    } else {
      setDuplicateError(true);
    }
  };

  const handleRemoveCourse = (course) => {
    setCompleted(cCompleted.filter((c) => c !== course));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (degree && major && cCompleted.length > 0 && semester && year && campus) {
      // Do something with the form data
      console.log(`Submitted: ${degree}, ${major}, ${cCompleted}, ${semester}, ${year}, ${campus}`);
      // Reset the form state
      setDegree('');
      setMajor('');
      setCompleted([]);
      setSemester('');
      setYear('');
      setCampus('');
      setFormError(true);
      // Navigate to the next page
      // You can use React Router or some other method here
    } else {
      // Set an error state if any field is missing
      setFormError(false);
    }
  };

  const sortedCompletedCourses = [...cCompleted].sort();

  return (
    <div>
      <h1>STUDENT INFORMATION</h1>
      <p>Please complete the following in its entirety:</p>
      <form onSubmit={handleSubmit}>
        <label htmlFor="degree">Degree:</label>
        <input
          type="text"
          id="degree"
          value={degree}
          onChange={handleDegreeChange}
          placeholder="Search for a Degree"
          list="degreeOptions"
          autoComplete="off"
        />
        <datalist id="degreeOptions">
          {degreeOptions.map((option) => (
            <option key={option.name} value={option.name} />
          ))}
        </datalist>
        <br />

        <label htmlFor="major">Major:</label>
        <select id="major" value={major} onChange={(e) => setMajor(e.target.value)}>
          <option value="" disabled hidden>
            Select a major
          </option>
          {major}
        </select>
        <br />

        <label htmlFor="cCompleted">Select courses that you have completed:</label>
        <select
          id="cCompleted"
          value={selectedCourse}
          onChange={(e) => setSelectedCourse(e.target.value)}
        >
          <option value="" disabled hidden>
            Select completed courses
          </option>
          <option value="COMP1010 Computing Fundamentals">COMP1010 Computing Fundamentals</option>
          <option value="COMP1140 Database and Information Management">COMP1140 Database and Information Management</option>
          <option value="INFT1060 Cybersecurity Fundamentals">INFT1060 Cybersecurity Fundamentals</option>
          <option value="SENG1050 Web Technologies">SENG1050 Web Technologies</option>
          <option value="SENG1110 Object Oriented Programming">INFT3800 Project Management</option>
          <option value="INFT2031 Systems and Network Administration">INFT2031 Systems and Network Administration</option>
          <option value="COMP3851A COMPINFOSCI WIL A">COMP3851A COMPINFOSCI WIL A</option>
          <option value="COMP3350 Advanced Database">COMP3350 Advanced Database</option>
          <option value="INFT3800 Project Management">INFT3800 Project Management</option>
          
          
          
          
          
        </select>
        <button className="add-course-button" onClick={handleAddCourse}>
          Add course
        </button>
        {duplicateError && <p>⚠️ Course is already added!</p>}
        <br />
        <div className="selected-courses">
          {sortedCompletedCourses.map((course, index) => (
            <div className="selected-course" key={index}>
              {course}
              <button className="remove-button" onClick={() => handleRemoveCourse(course)}>
                x
              </button>
            </div>
          ))}
        </div>
        <br />

        <label htmlFor="semester">Which Semester do you want to start in:</label>
        <select id="semester" value={semester} onChange={(e) => setSemester(e.target.value)}>
          <option value="" disabled hidden>
            Select a semester
          </option>
          <option value="One">Semester 1</option>
          <option value="Two">Semester 2</option>
        </select>
        <br />

        <label htmlFor="year">How long do you want to complete your degree:</label>
        <select id="year" value={year} onChange={(e) => setYear(e.target.value)}>
          <option value="" disabled hidden>
            Select a year
          </option>
          <option value="Three">Three</option>
          <option value="Four">Four</option>
          <option value="Five">Five</option>
          <option value="Six">Six</option>
          <option value="Seven">Seven</option>
          <option value="Eight">Eight</option>
        </select>
        <br />

        <label htmlFor="campus">Campus:</label>
        <select id="campus" value={campus} onChange={(e) => setCampus(e.target.value)}>
          <option value="" disabled hidden>
            Select a campus
          </option>
          <option value="Callaghan">Callaghan Campus</option>
        </select>
        <br />

        {formError && <p>⚠️ Please fill in all fields before submitting!</p>}

        <Link to="/Planner">
          <button type="submit" disabled={formError}>
            Next
          </button>
        </Link>
      </form>
      <Footer />
    </div>
  );
}

export default StudentInfo;
