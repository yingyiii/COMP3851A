import React, { useState } from 'react';
import Header from './header'; 
import Footer from './footer';

const Box = ({ box, onDragStart, onDragOver, onDrop }) => {
  return (
    <div
      className="box"
      style={{ backgroundColor: box.color }}
      draggable="true"
      onDragStart={onDragStart({ id: box.id })}
      onDragOver={onDragOver({ id: box.id })}
      onDrop={onDrop({ id: box.id })}
    >
      <div className="content">{box.name}</div>
    </div>
  );
};

const BoxesGroup = () => {
  const [boxes, setBoxes] = useState([
    { id: 1, name: 'COMP3851A', color: 'lightgrey' },
    { id: 2, name: 'COMP1010', color: 'lightgrey' },
    { id: 3, name: 'SENG1050', color: 'lightgrey' },
    { id: 4, name: 'COMP3500', color: 'lightgrey' },
    { id: 5, name: 'SENG2260', color: 'lightgrey' },
    { id: 6, name: 'SENG2130', color: 'lightgrey' },
    { id: 7, name: 'COMP3851B', color: 'lightgrey' },
    { id: 8, name: 'INFT1005', color: 'lightgrey' },
    { id: 9, name: 'SENG1110', color: 'lightgrey' },
    { id: 10, name: 'INFT1004', color: 'lightgrey' },
    { id: 11, name: 'INFT1060', color: 'lightgrey' },
    { id: 12, name: 'INFT3100', color: 'lightgrey' },
    { id: 13, name: 'INFT3100', color: 'lightgrey' },
    { id: 14, name: 'INFT3100', color: 'lightgrey' },
    { id: 15, name: 'INFT3100', color: 'lightgrey' },
    { id: 16, name: 'INFT3100', color: 'lightgrey' },
    { id: 17, name: 'INFT3100', color: 'lightgrey' },
    { id: 18, name: 'INFT3100', color: 'lightgrey' },
    { id: 19, name: 'INFT3100', color: 'lightgrey' },
    { id: 20, name: 'INFT3100', color: 'lightgrey' },
    { id: 21, name: 'INFT3100', color: 'lightgrey' },
    { id: 22, name: 'INFT3100', color: 'lightgrey' },
    { id: 23, name: 'INFT3100', color: 'lightgrey' },
    { id: 24, name: 'INFT3100', color: 'lightgrey' },
  ]);

  const leftBoxes = boxes.slice(0, 4);
  const rightBoxes = boxes.slice(4, 8);

  const swapBoxes = (fromBox, toBox) => {
    const updatedBoxes = [...boxes];
    const fromIndex = boxes.findIndex((box) => box.id === fromBox.id);
    const toIndex = boxes.findIndex((box) => box.id === toBox.id);

    if (fromIndex !== -1 && toIndex !== -1) {
      [updatedBoxes[fromIndex], updatedBoxes[toIndex]] = [
        updatedBoxes[toIndex],
        updatedBoxes[fromIndex],
      ];
      setBoxes(updatedBoxes);
    }
  };

  const handleDragStart = (data) => (event) => {
    const fromBox = JSON.stringify({ id: data.id });
    event.dataTransfer.setData('dragContent', fromBox);
  };

  const handleDragOver = (data) => (event) => {
    event.preventDefault();
  };

  const handleDrop = (data) => (event) => {
    event.preventDefault();

    const fromBox = JSON.parse(event.dataTransfer.getData('dragContent'));
    const toBox = { id: data.id };

    swapBoxes(fromBox, toBox);
  };

  const makeBoxes = () => {
    return boxes.map((box) => (
      <Box
        key={box.id}
        box={box}
        onDragStart={handleDragStart}
        onDragOver={handleDragOver}
        onDrop={handleDrop}
      />
    ));
  };

  return (
    <div>
      <Header />
      <div style={{ textAlign: 'left', marginLeft: '30px' }}>
        This program plan is for students commencing in the xxx major in Semester xx 2023.
        <br />
        <br />
      </div>
      <tbody>
        <tr>
          <th>
            <span>YEAR 1</span>
            <span>YEAR 2</span>
            <span>YEAR 3</span><br/>
          </th>
          <td>
            <div className="semester-container">
              <div className="semester-labels">
                <div>
                  <span className="left-semester">SEMESTER 1</span>
                  <span className="right-semester">SEMESTER 2</span>
                </div>
              </div>
            </div>
            <div className="boxesGroup">{makeBoxes()} </div>
          </td>
          <th></th>
          <td className="blank-space">
            {/* Content for the new cell */}
          </td>
        </tr>
        
      </tbody>
      
      <Footer />
    </div>
  );
};

export default BoxesGroup;