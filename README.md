
# Sudoku Solver App

A simple android application that solves the sudoku puzzle using a backtracking algorithm.

---

## Features

- **Input Sudoku Puzzle**: Enter the numbers in a 9x9 grid.
- **Validate Input**: Ensures the puzzle is solvable and adheres to Sudoku rules.
- **Solve Puzzle**: Automatically computes the solution for a valid Sudoku puzzle.
- **Reset Grid**: Clears the grid for a new puzzle.
- **Navigation**: Includes "About" and "Feedback" sections in the menu.

---

## How It Works

1. **Input Validation**: 
   - Ensures no duplicate numbers exist in rows, columns, or sub-grids.
   - Displays an error message if the input is invalid.

2. **Puzzle Solver**:
   - Implements a backtracking algorithm to solve the Sudoku puzzle.
   - Updates the grid with the solution.

3. **Reset Functionality**:
   - Clears the input grid for a fresh start.

---

## Getting Started

### Prerequisites
- Android Studio installed on your computer.
- An Android device or emulator to run the app.

### Installation
1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/Benyormin/sudokusolver.git
