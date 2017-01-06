GarageDoorOpener
======

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8a331e855a0d40e88f7631cdfdb59920)](https://www.codacy.com/app/ndrone/HomeAutomation?utm_source=github.com&utm_medium=referral&utm_content=ndrone/HomeAutomation&utm_campaign=badger)

Purpose
-----------------
The purpose of the is project was to allow me to have a playground to learn Angular JS, and cloud technologies. I needed
a garage door opener and found a use for my Raspberry Pi at the same time.

Technologies Used
------------------
Angular JS client interface
Spring Boot because I have small kids, and spinng up this entire application took less then an hour minus the front end.
Pi4j to interface between spring boot and the Raspberry Pi.
Mockito used to mock out the Pi4j portions unless your programming on the raspberry pi itself.

Environment/Build
-----------------

This application contains a [server](GarageDoorOpener-server/README.md) and a [client](GarageDoorOpener-client/README.md)

You must install [node](https://nodejs.org/)