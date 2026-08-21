# CS 102 Project - Until the Last Color Stands

**Until the Last Color Stands** is a turn-based 4X strategy and civilization-building game developed using Java and the [libGDX](https://libgdx.com/) framework. In this game, players choose from various distinct empires (represented by colors such as Red, Blue, Cyan, etc.) and compete for dominance on a tile-based map.

## Core Features

- **Empire Management:** Choose your civilization and manage essential resources including Food, Gold, Books, and Movement Points.
- **Tile-Based Strategy:** Explore and conquer different terrain types. Utilize your movement points efficiently to expand your territory.
- **City Building:** Construct specialized buildings like Farms, Gold Mines, Libraries, and Ports to boost your empire's economy and resource generation.
- **Diplomacy & Warfare:** Interact with other empires through the Trade system or declare War using the comprehensive war mechanics. The ultimate goal is to conquer your enemies until you are the last color standing!
- **Save & Load System:** Pick up right where you left off with JSON-based game state serialization.
- **Custom UI:** Features a fully custom Graphical User Interface including Game HUDs, Empire Selection Screens, Trade Dialogs, and Interactive Maps.

## Technical Details
This project serves as a comprehensive application of Object-Oriented Programming (OOP) concepts. It makes heavy use of:
- **Inheritance & Polymorphism:** For managing different types of buildings, resources, and civilizations.
- **UI & State Management:** Implementing structured UI screens and input processors using libGDX's Scene2D.
- **File I/O:** Serializing and deserializing complex game states using JSON.

## How to Run
This project uses the Gradle build system. To run the game locally, you can use the provided Gradle wrapper:

```bash
# Windows
gradlew.bat lwjgl3:run

# Linux/macOS
./gradlew lwjgl3:run
```
