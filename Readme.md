# PingDing

A thing that Pings things

## Requirements

- Java 11
- JavaFX 11

## Description

This Program is a tool to monitor the ping of multiple sites at once.

Menu: `Edit > Add` or Key: `Insert` to add a new empty line. <br>
Menu: `Edit > Delete` or Key: `Delete` to remove selected row. <br>
Double click to edit the **URL**.

The program will update it's values every 10 seconds.

## Compile

use **IntelliJ** or manually using the command line.

- Make Sure your default java is java 11.

Execute in project root: <br>
`javac --module-path <path to javafx>/lib -d out/ $(find src/ -name "*.java")` <br>
Copy some necessary files: <br>
`cp src/sample/config.txt src/sample/Main.fxml src/sample/Style.css out/sample/` <br>
Run program: <br>
`cd out` <br>
`java --module-path <path to javafx>/lib --add-modules=javafx.fxml,javafx.graphics,javafx.controls sample.Main`

