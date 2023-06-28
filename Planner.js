import React, { useState } from 'react';
import './App.css';
import Header from './header';
import Footer from './footer';
import Draggable from 'react-draggable';
import { DragDropContext } from 'react-beautiful-dnd';
import { render } from '@testing-library/react';

const tableData = [
  ['', '', '', ''],
  ['', '', '', ''],
  ['', '', '', ''],
  ['', '', '', ''],
  ['', '', '', ''],
  ['', '', '', ''],
];

function Planner() {
  const initialPositions = Array.from({ length: tableData.length }, () =>
    Array.from({ length: 4 }, () => ({ x: 0, y: 0 }))
  );
  const [positions, setPositions] = useState(initialPositions);

  const handleDrag = (sourceRowIndex, sourceCellIndex) => (e, ui) => {
    const { x, y } = ui;
    const newPositions = [...positions];
    newPositions[sourceRowIndex][sourceCellIndex] = { x, y };
    setPositions(newPositions);
  };

  const handleDragStop = (sourceRowIndex, sourceCellIndex) => () => {
    const newPositions = [...positions];
    newPositions[sourceRowIndex][sourceCellIndex] = { x: 0, y: 0 };
    setPositions(newPositions);
  };

  const handleDragEnd = (result) => {
    const { source, destination } = result;

    if (!destination) {
      return;
    }

    const sourceRowIndex = parseInt(source.droppableId);
    const sourceCellIndex = parseInt(source.index);
    const destRowIndex = parseInt(destination.droppableId);
    const destCellIndex = parseInt(destination.index);

    if (sourceRowIndex === destRowIndex && sourceCellIndex === destCellIndex) {
      return;
    }

    const draggedCell = tableData[sourceRowIndex][sourceCellIndex];
    const updatedTableData = [...tableData];

    // Swap the dragged cell with the destination cell
    updatedTableData[sourceRowIndex][sourceCellIndex] = tableData[destRowIndex][destCellIndex];
    updatedTableData[destRowIndex][destCellIndex] = draggedCell;

    // Update the state
    const newPositions = [...positions];
    newPositions[sourceRowIndex][sourceCellIndex] = { x: 0, y: 0 };
    newPositions[destRowIndex][destCellIndex] = { x: 0, y: 0 };
    setPositions(newPositions);

    render(<Planner />); // Force re-render to update the table with new positions and data
  };

  return (
    <div>
      <Header />
      <div style={{ textAlign: 'left', marginLeft: '30px' }}>
        This program plan is for students commencing in the xxx major in Semester xx 2023.
        <br />
      </div>
      <DragDropContext onDragEnd={handleDragEnd}>
        <div className='row'>
          <div className='column'>
            {tableData.slice(0, 3).map((row, rowIndex) => (
              <table key={rowIndex}>
                <tbody>
                  <tr>
                    <th>
                      <span>Year {rowIndex + 1}</span>
                    </th>
                    <th>
                      <span>Semester 1</span>
                    </th>
                    {row.map((cellData, cellIndex) => (
                      <td key={cellIndex}>
                        <Draggable
                          handle='.handle'
                          defaultPosition={{ x: 0, y: 0 }}
                          position={positions[rowIndex][cellIndex]}
                          onDrag={handleDrag(rowIndex, cellIndex)}
                          onStop={handleDragStop(rowIndex, cellIndex)}
                        >
                          <div className='cell-container'>
                            <div className='handle'>Drag me</div>
                            <div className='cell-content'>{cellData}</div>
                          </div>
                        </Draggable>
                      </td>
                    ))}
                  </tr>
                </tbody>
              </table>
            ))}
          </div>
          <div className='column'>
            {tableData.slice(3).map((row, rowIndex) => (
              <table key={rowIndex}>
                <tbody>
                  <tr>
                    <th>
                      <span>Semester 2</span>
                    </th>
                    {row.map((cellData, cellIndex) => (
                      <td key={cellIndex}>
                        <Draggable
                          handle='.handle'
                          defaultPosition={{ x: 0, y: 0 }}
                          position={positions[rowIndex + 3][cellIndex]}
                          onDrag={handleDrag(rowIndex + 3, cellIndex)}
                          onStop={handleDragStop(rowIndex + 3, cellIndex)}
                        >
                          <div className='cell-container'>
                            <div className='handle'>Drag me</div>
                            <div className='cell-content'>{cellData}</div>
                          </div>
                        </Draggable>
                      </td>
                    ))}
                  </tr>
                </tbody>
              </table>
            ))}
          </div>
        </div>
      </DragDropContext>
      <Footer />
    </div>
  );
}

export default Planner;
