Garage Door Server
=================

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

Environment Setup
-----------------

When you are working with this code locally you
will need to add the below to the program argument list.


`--CLIENT_HOME=${project.basedir}/test-client`

If you are running locally without the files required by Pi4j and WiringPi on the filesystem make sure to use the `nopi` profile.

*note that location needs to be the fully qualified location of the client project.

This application contains a [client](GarageDoorOpener-client/README.md) as well.well

You must install [node](https://nodejs.org/)