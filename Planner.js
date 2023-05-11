import React,{useState} from 'react';
import './App.css'
import Header from "./header";
import Footer from "./footer";
import Draggable from 'react-draggable';
import { DragDropContext } from 'react-beautiful-dnd';
import { render } from '@testing-library/react';

const rowNames = [];

const tableData1 = [
  ['', '', '', ''],
];

const tableData2 = [
  ['', '', '', ''],
];

const tableData3 = [
  ['', '', '', ''],
];

const tableData4 =[
  ['', '', '', ''],
];

const tableData5 = [
  ['', '', '', ''],
];

const tableData6 = [
  ['', '', '', ''],
];

const handleDrag = (e, ui) => {
  // Handle drag event
};



function Planner() {
  return (
      <div>
          <Header />
          <div style={{textAlign: "left", marginLeft:"30px"}}>
            This program plan is for students comencing in the xxx major in Semester xx 2023.<n/>
          </div>
        <div class='row'>
          <div class="column">
        <table>
        <tbody>
        {tableData1.map((row, rowIndex) => (
          <tr key={rowIndex}>
            <th><span>SEMESTER 1 <br/> Year 1</span></th>
            {row.map((cellData, cellIndex) => (
              <td key={cellIndex}>
                <Draggable
                  handle=".handle"
                  defaultPosition={{ x: 0, y: 0 }}
                  onDrag={handleDrag}
                >
                  <div className="cell-container">
                    <div className="handle">Drag me</div>
                    <div className="cell-content">{cellData}</div>
                  </div>
                </Draggable>
              </td>
            ))}
          </tr>
          ))}
          </tbody>
        </table>

        <table>
        <tbody>
        {tableData2.map((row, rowIndex) => (
          <tr key={rowIndex}>
            <th><span>SEMESTER 1 <br/>Year 2</span></th>
            {row.map((cellData, cellIndex) => (
              <td key={cellIndex}>
                <Draggable
                  handle=".handle"
                  defaultPosition={{ x: 0, y: 0 }}
                  onDrag={handleDrag}
                >
                  <div className="cell-container">
                    <div className="handle">Drag me</div>
                    <div className="cell-content">{cellData}</div>
                  </div>
                </Draggable>
              </td>
            ))}
          </tr>
          ))}
          </tbody>
        </table>

        <table>
        <tbody>
        {tableData3.map((row, rowIndex) => (
          <tr key={rowIndex}>
            <th><span>SEMESTER 1<br/> Year 3 </span></th>
            {row.map((cellData, cellIndex) => (
              <td key={cellIndex}>
                <Draggable
                  handle=".handle"
                  defaultPosition={{ x: 0, y: 0 }}
                  onDrag={handleDrag}
                >
                  <div className="cell-container">
                    <div className="handle">Drag me</div>
                    <div className="cell-content">{cellData}</div>
                  </div>
                </Draggable>
              </td>
            ))}
          </tr>
          ))}
          </tbody>
        </table>
        </div>

        <div class="column">
        <table>
        <tbody>
   
        {tableData4.map((row, rowIndex) => (
          <tr key={rowIndex}>
            <th><span>SEMESTER 2</span></th>
            {row.map((cellData, cellIndex) => (
              <td key={cellIndex}>
                <Draggable
                  handle=".handle"
                  defaultPosition={{ x: 0, y: 0 }}
                  onDrag={handleDrag}
                >
                  <div className="cell-container">
                    <div className="handle">Drag me</div>
                    <div className="cell-content">{cellData}</div>
                  </div>
                </Draggable>
              </td>
            ))}
          </tr>
          ))}
          </tbody>
        </table>

        <table>
        <tbody>
        {tableData5.map((row, rowIndex) => (
          <tr key={rowIndex}>
            <th><span>SEMESTER 2</span></th>
            {row.map((cellData, cellIndex) => (
              <td key={cellIndex}>
                <Draggable
                  handle=".handle"
                  defaultPosition={{ x: 0, y: 0 }}
                  onDrag={handleDrag}
                >
                  <div className="cell-container">
                    <div className="handle">Drag me</div>
                    <div className="cell-content">{cellData}</div>
                  </div>
                </Draggable>
              </td>
            ))}
          </tr>
          ))}
          </tbody>
        </table>

        <table>
        <tbody>
        {tableData6.map((row, rowIndex) => (
          <tr key={rowIndex}>
            <th><span>SEMESTER 2</span></th>
            {row.map((cellData, cellIndex) => (
              <td key={cellIndex}>
                <Draggable
                  handle=".handle"
                  defaultPosition={{ x: 0, y: 0 }}
                  onDrag={handleDrag}
                >
                  <div className="cell-container">
                    <div className="handle">Drag me</div>
                    <div className="cell-content">{cellData}</div>
                  </div>
                </Draggable>
              </td>
            ))}
          </tr>
          ))}
          </tbody>
        </table>
        </div>
        </div>
         
          <Footer />
      </div>
  )
}

export default Planner;