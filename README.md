# Casino Slot Machine - Multi-Armed Bandit Simulation

A Java-based project implementing multi-armed bandit algorithms and slot machine simulations for educational purposes.

## Project Overview

This project explores the **Multi-Armed Bandit Problem**, a fundamental concept in machine learning and artificial intelligence. It includes both command-line and graphical user interface implementations to simulate gambling scenarios and solve optimization problems.

## Project Structure

```
Casino/
├── src/ai_bandit/
│   ├── models/                    # Basic bandit model implementations
│   │   ├── Bandit.java           # Single-armed bandit machine
│   │   └── MultiBandit.java      # Multi-armed bandit machine (basic)
│   │
│   ├── core/                      # Core algorithms, utilities, and enhanced models
│   │   ├── Gambling.java         # Base class for gambling machines
│   │   ├── Bandit.java           # Enhanced bandit (for GUI)
│   │   ├── MultiBandit.java      # Enhanced multi-bandit (for GUI)
│   │   ├── MultiBanditSolver.java        # Enhanced solver for GUI with reset()
│   │   ├── MultiBanditSolver_CLI.java    # Basic solver for CLI apps
│   │   └── WheelOfFortune.java   # Wheel of fortune game implementation
│   │
│   ├── apps/                      # Application entry points
│   │   ├── BanditApp.java        # CLI for single bandit
│   │   ├── MultiBanditApp.java   # CLI for multi-bandit
│   │   ├── WheelApp.java         # CLI for wheel of fortune
│   │   └── App.java              # Main GUI application entry point
│   │
│   ├── ui/                        # User interface components
│   │   └── gui/                  # Graphical user interface
│   │       ├── GraphicalUI.java  # Main Swing GUI frame
│   │       ├── Model.java        # Data model for GUI
│   │       ├── ControlPanel.java # Control panel components
│   │       ├── PlayThread.java   # Multi-threaded game execution
│   │       ├── PlotPanel.java    # Line chart visualization
│   │       └── PlotBarPanel.java # Bar chart visualization
│   │
│   └── tests/                     # Unit tests
│       ├── TestBandit.java       # Tests for bandit implementations
│       └── TestMultiBandit.java  # Tests for multi-bandit implementations
```

## Features

### Models

- **Bandit**: Core single-armed bandit machine implementation
- **MultiBandit**: Container for multiple bandits with strategy selection

### Core Algorithms & Utilities

- **Gambling**: Base class with common gambling functionality
- **MultiBanditSolver**: Implements optimization algorithms for bandit selection
- **WheelOfFortune**: Interactive wheel-based game implementation
- **GUI Enhancements**: Extended versions optimized for graphical display

### Applications

- **CLI Applications**: Command-line interfaces for single bandit, multi-bandit, and wheel games
- **GUI Application**: Interactive graphical interface with visualization and real-time gameplay

### User Interface

- **Graphical UI**: Built with Java Swing for interactive gameplay
- **Real-time Visualization**: Plot panels for tracking performance metrics
- **Multi-threaded Gameplay**: Smooth concurrent game execution
- **Control Panel**: Intuitive controls for game configuration

## Getting Started

### Prerequisites

- Java 8 or higher
- IntelliJ IDEA (or any Java IDE/compiler)

### Running the Project

#### CLI Applications

**Single-Armed Bandit:**

```bash
javac src/ai_bandit/apps/BanditApp.java
java -cp src ai_bandit.apps.BanditApp
```

**Multi-Armed Bandit:**

```bash
javac src/ai_bandit/apps/MultiBanditApp.java
java -cp src ai_bandit.apps.MultiBanditApp
```

**Wheel of Fortune:**

```bash
javac src/ai_bandit/apps/WheelApp.java
java -cp src ai_bandit.apps.WheelApp
```

#### Graphical User Interface

```bash
javac -cp src src/ai_bandit/apps/App.java
java -cp src ai_bandit.apps.App
```

### Running Tests

```bash
javac -cp src src/ai_bandit/tests/TestBandit.java
java -cp src ai_bandit.tests.TestBandit

javac -cp src src/ai_bandit/tests/TestMultiBandit.java
java -cp src ai_bandit.tests.TestMultiBandit
```

## Multi-Armed Bandit Problem

The Multi-Armed Bandit Problem is a classic exploration-exploitation dilemma:

- **Exploration**: Try new machines to find better ones
- **Exploitation**: Play machines you know are profitable

This project implements various strategies to solve this optimization problem, useful for applications like:

- A/B testing in web applications
- Recommendation systems
- Resource allocation
- Clinical trials

## Implementation Details

### Core Classes

- **Bandit**: Represents a single slot machine with win probabilities
- **MultiBandit**: Container for multiple bandits with strategy selection
- **MultiBanditSolver**: Implements algorithms to determine optimal machine selection
- **GraphicalUI**: Swing-based interface for interactive gameplay
- **PlotPanel**: Visualizes results over time

## Usage Example (CLI)

```
Gambling : One-armed bandit
Price    : 1.00 EUR
How many rounds would you like to play? 10

Round | Win [EUR] | Net [EUR]
======+===========+==========
 1    |   0.90   |  -0.10
 2    |   2.00   |   0.90
...
```

## Technologies Used

- **Java**: Core language
- **Swing**: GUI framework (Lab 4)
- **Collections Framework**: Data structure management
- **Threading**: Concurrent gameplay support

## Project Status

This project is an educational implementation of machine learning concepts related to the multi-armed bandit problem. It demonstrates both theoretical understanding and practical implementation through CLI and GUI applications.

## License

This project is for educational purposes.

## Authors

Arian Makiabadi
[Sogol Saleki](https://github.com/sogolcodeshere)
