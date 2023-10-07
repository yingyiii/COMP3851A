import React, {useState, useRef, useEffect} from 'react';
import './App.css';
import Planner from "./Planner.js";
import Footer from "./footer";
import { Link, useNavigate } from 'react-router-dom';

function StudentInfo() {
  const [degree, setDegree] = useState('');
  const [major, setMajor] = useState('');
  const [majorOptions, setMajorOptions] = useState('');
  const [cCompleted, setCompleted] = useState([]);
  const [selectedCourse, setSelectedCourse] = useState('');
  const [semester, setSemester] = useState('');
  const [units, setUnits] = useState('');
  const [formError, setFormError] = useState(false);
  const [duplicateError, setDuplicateError] = useState(false);
  const navigate = useNavigate();



  //Pulling degree info from the backend. 'useRef()' makes this immune to re-renders on change, and 'useEffect()' will stop the fetch request running on every re-render.
  const degreeOptions = useRef([]);
  useEffect(() => {
    fetch('http://localhost:8080/ProgramPlanner/getDegrees')
    .then(response => {
      if(!response.ok) {
        throw new Error("Fetch Request Failed! Network Error - Code: " + response.status);
      }
      return response.json();
    })
    .then(data => degreeOptions.current = data)
    .catch(error => console.log("Fetch Request Failed! Error:", error));
  }, []);

  //Same thing as above, but pulling courses instead.
  const courseOptions = useRef([]);
  useEffect(() => {
    fetch('http://localhost:8080/ProgramPlanner/getCourses')
    .then(response => {
      if(!response.ok) {
        throw new Error("Fetch Request Failed! Network Error - Code: " + response.status);
      }
      return response.json();
    })
    .then(data => courseOptions.current = data)
    .catch(error => console.log("Fetch Request Failed! Error:", error));
  }, []);



  //Handles degree changes, this also effects the options for majors.
  const handleDegreeChange = (e) => {
    
    const selectedDegree = e.target.value;
    if(degreeOptions.current.includes(selectedDegree)) { //If the degree is a legitimate degree...
      
      //Send chosen degree to back end then pull list of majors for that degree from back end.
      //These requrests are chained so that data isn't pulled from the back end before it has the chance to update it.
      fetch('http://localhost:8080/ProgramPlanner/postDegree', {
        method: 'POST',
        headers: {'Content-Type': 'text/plain'},
        body: selectedDegree
      })
      .then(response => {
        if(!response.ok) {
          throw new Error("Post request failed")
        }
        return response.text();
      })
      .then(data => { 
        return fetch('http://localhost:8080/ProgramPlanner/getMajors');
      })
      .then(response => {
        if(!response.ok) {
          throw new Error("Fetch Request Failed! Network Error - Code: " + response.status);
        }
        return response.json();
      })
      .then(data => {
        updateMajorOptions(data) //Calls the function that updates the maps the majors from the back end to the options on the form.
      }) 
      .catch(error => console.log("Fetch Request Failed!", error));
    
      //Reseting major chosen major field.
      setMajor('');

    }
    else { //Reset major and major options. Don't bother with fetch as degree doesn't exist.
      setMajor('');
      setMajorOptions([]);
    }

    setDegree(selectedDegree); //Sets the degree to be the input regardless, should maybe change this.
  };




  const updateMajorOptions = (majors) => {
    const options = majors.map((major) => (
      <option key={major} value={major}>
        {major}
      </option>
    ));
    setMajorOptions(options);
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
    if (degree && major && semester && units) {
      
      console.log(`Submitted: ${degree}, ${major}, ${cCompleted}, ${semester}, ${units}`);

      //Converts the array of strings into one long string, with a space between.
      let outputCCompleted = cCompleted.join(" ");
      console.log(outputCCompleted);
      
      fetch('http://localhost:8080/ProgramPlanner/sendInfo', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
          degree: degree,
          major: major,
          completedCourses: outputCCompleted,
          units: units,
          startingSemester: semester 
        })
      })
      .then(data => {
        console.log(data);
      })
      .catch(error => {
        console.error('Error:', error);
      });

      console.log((typeof semester == typeof units));

      // Reset the form state
      setDegree('');
      setMajor('');
      setCompleted([]);
      setSemester('');
      setUnits('');
      setFormError(false);
      // Navigate to the next page
      // You can use React Router or some other method here
    } else {
      // Set an error state if any field is missing
      setFormError(true);
    }
  };

  const sortedCompletedCourses = [...cCompleted].sort();

  return (
    <div>
      <h1>STUDENT INFORMATION</h1>
      <p>Please complete the following in its entirety:</p>
      <form onSubmit={handleSubmit}>
        <label htmlFor="degree">Degree:</label>
        <input type="text" id="degree" value={degree} onChange={handleDegreeChange} placeholder="Search for a degree..." list="degreeOptions" autoComplete="off"/>
        <datalist id="degreeOptions">
          {degreeOptions.current.map((option) => (
            <option value={option} />
          ))}
        </datalist>
        <br />

        <label htmlFor="major">Major:</label>
        <select id="major" value={major} onChange={(e) => setMajor(e.target.value)}>
          <option value="" disabled hidden>
            Select a major...
          </option>
          {majorOptions}
        </select>
        <br />

        <label htmlFor="cCompleted">Select courses that you have completed:</label>
        <select id="cCompleted" value={selectedCourse} onChange={(e) => setSelectedCourse(e.target.value)}>
          <option value="" disabled hidden>
            Select completed courses
          </option>
          {courseOptions.current.map((option) => (<option value={option}>{option}</option>))}

        </select>
        <button className="add-course-button" type="button" onClick={handleAddCourse}>
          Add course
        </button>
        {duplicateError && <p>⚠️ Course is already added!</p>}
        <br />
        <div className="selected-courses">
          {sortedCompletedCourses.map((course, index) => (
            <div className="selected-course" key={index}>
              {course}
              <button className="remove-button" type="button" onClick={() => handleRemoveCourse(course)}>
                x
              </button>
            </div>
          ))}
        </div>
        <br />

        <p>Which Semester do you want to start in:</p>
        <input type="radio" id="sem1" name="semester" value="1" checked={semester === 1} onChange={(e) => {setSemester(parseInt(e.target.value))}}/>
        <label htmlFor="sem1">Semester 1</label>
        <input type="radio" id="sem2" name="semester" value="2" checked={semester === 2} onChange={(e) => {setSemester(parseInt(e.target.value))}}/>
        <label htmlFor="sem2">Semester 2</label>

        <label htmlFor="units">What is your prefered course load per semester?</label>
        <select id="units" value={units} onChange={(e) => setUnits(parseInt(e.target.value))}>
          <option value="" disabled hidden>
            Select load
          </option>
          <option value="40">40 Units</option>
          <option value="30">30 Units</option>
          <option value="20">20 Units</option>
        </select>
        <br />

        

        {formError && <p>⚠️ Please fill in all fields before submitting!</p>}

        <button type="submit">
          Next
        </button>
      </form>
      <Footer />
    </div>
  );
}

export default StudentInfo;




//Send data to the backend, JSON format.

