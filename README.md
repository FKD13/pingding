# PINGDING

# PingDing

A thing that Pings things


Structure taken from https://github.com/openjfx/samples/tree/master/CommandLine/Modular/Maven

## Requirements

- Java 11
- Maven

## Description

This Program is a tool to monitor the ping of multiple sites at once.

Menu: `Edit > Add` or Key: `Insert` to add a new empty line. <br>
Menu: `Edit > Delete` or Key: `Delete` to remove selected row. <br>
Double click to edit the **URL**.

The program will update it's values every 10 seconds.



## Running on linux

    mvn clean javafx:run

## Creating and running JRE

    mvn clean javafx:jlink
    target/pingding/bin/launcher 
