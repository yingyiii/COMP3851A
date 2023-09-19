import React from 'react';
import ReactDOM from 'react-dom/client';
import {Router, Routes, Route } from 'react-router-dom';
import Home from './Home.js';
import StudentInfo from './StudentInfo.js';
import './App.css';
import Planner from './Planner.js';


class App extends React.Component {
    render() {
        return (
            <div>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/StudentInfo" element={<StudentInfo />} />
                    <Route path='/Planner' element={<Planner />} />
                </Routes>
               
            </div>
            
        );
    }
}

export default App;