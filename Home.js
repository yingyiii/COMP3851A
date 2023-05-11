import React from 'react';
import './App.css';
import { Link } from 'react-router-dom';
import Footer from './footer';


class Home extends React.Component {
    render() {
        return (
            
        <div className="homePage">
            <header>
                <h1 className="title" style={{paddingTop: '50px', paddingBottom: '50px', fontSize: '50px'}}>UON Degree Program Planner</h1>
                <div>
                    <p className='instructPara'> 
                    Welcome to our Program Planner! <br/>
                        We are thrilled to have you join us and we look forward to an engaging and productive program.<br/>
                        To get started, please make sure to read through the instructions that we have provided.<br/>
                        1. You need to enter your information at the next page.<br/>
                        2. Select the degree and major you want to study.<br/>
                        3. Next, select which semester you want to start your study and how many year do you want to complete your degree.<br/>
                        4. Select the campus.<br/>
                        5. The program planner will generate a program plan for you and feel free to edit it!<br/>
                        If you have any questions or concerns about the program, please don't hesitate to reach out to us. We are here to support you and ensure that you have a positive and productive experience.
                    </p>
                </div>    
            </header>

                <div style={{padding: '20px'}}>
                    <Link to="/StudentInfo">
                        <button className="homePageNavButton" type="button">Get Started</button>
                    </Link>
                </div>
            <Footer/>
        </div>
        );
    }
}


export default Home;
