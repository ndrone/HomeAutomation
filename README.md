Home Automation [![Build Status](https://travis-ci.org/ndrone/HomeAutomation.svg?branch=develop)](https://travis-ci.org/ndrone/HomeAutomation) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/8a331e855a0d40e88f7631cdfdb59920)](https://www.codacy.com/app/ndrone/HomeAutomation?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ndrone/HomeAutomation&amp;utm_campaign=Badge_Grade)
======

Home Automation is a multi module project using several cloud projects, and other technologies, AngularJS, Spring Boot and more to come. Most of these projects are hosted on my Raspberry Pi's.

Modules
------

Home Automation has the following modules:

  - ```config-server``` Configuration Server to have a single repository to abstract secret configurations.
  - ```dashboard``` Module for a centralized knowledge of the entire ecosystem.
  - ```eureka-server``` Registry Service for all modules. 
  - ```GarageDoorOpener-client``` AngularJs project that is built with webpack into a jar and used with the server project
  - ```GarageDoorOpener-server``` Spring boot project that uses Pi4J to leverage the GPIO's to a relay to trigger the garage door opener.
  
Building from Source
----

You need Java (1.8 or higher) and Maven 3.3.3

```maven clean install```