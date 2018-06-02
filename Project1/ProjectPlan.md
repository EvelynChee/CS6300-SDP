# **Project Plan -- Team 85**

## 1 Introduction

The project's goal is develop an application that will help the users to understand the quality of their essay writing by providing feedback on the average words per sentence. We are going to call the application Word Counter (wc) from now on.

## 2 Process Description

**Activity: Project Planning**
  - Description:  
    In this activity the team analyzes the general goals of the project. The team will define and allocate resources (define roles, activities, expectations) and provide an estimate of the effort.  
  - Entrance criteria: Project Idea.  
  - Exit criteria: Project Plan Document.  

**Activity: Requirement Gathering and Analysis**  
  - Description:  
    In this activity the team analyzes the details of the project and defines the requirements.  
  - Entrance criteria: Project Plan Document, Stakeholder/User Interviews.  
  - Exit criteria: Mature Requirements Document.  

**Activity: Application Design and Coding**
  - Description:  
    In this activity the team designs the application and implements the design based on the requirements and determined testing (including creation of Unit tests).  
  - Entrance criteria: Mature Requirements Document.  
  - Exit criteria: Technical Design Document, Working Application that Passes all Unit Tests.  

**Activity: User Manual Preparation**
  - Description:  
  In this activity the team prepares the user manual that provides application instructions and information to the end user. The user manual will help the end user setup/run the application on their Operating System and provide detailed information on the applications commands and parameter options.
  - Entrance criteria: Technical Design Document.
  - Exit criteria: User Manual.  

**Activity: QA**
  - Description:  
  In this activity the team verifies that the application quality is acceptable for production use. The team will also verify User Manual is correct and provides sufficient content for end user to use the application. Also, the QA team will prepare test scenarios and execute tests on the application. The QA team will keep a log book of executed tests and their status. The QA team will work with the Development Team until all bugs are fixed.
  - Entrance criteria: User Manual, Working Application.
  - Exit criteria: Test Scenarios and Test Execution Log Book, Bug Free Application.  

## 3 Team  

**Project Team Members:**

- Bulent Coskun  
- Brandon Chong  
- Chang Liu  
- Zhi Zhang  

The project has the following roles:

- **Project Manager:**  
Leader for the project. Keeps the project on track. Follows-up with team members on status and roadblocks. Organizes team activities and meetings.

- **Document Lead:**  
Prepares the User Manual. Checks and revises any documentation as necessary.

- **Development Lead:**  
Evaluates the requirements and writes code with the other developers. Responsible for design of the application. Leads the developers and makes sure the quality of the application is acceptable.

- **QA Manager:**  
Leads the testers. Develops test cases for the application.

- **Tester:**  
Simulates the end user and captures the issues found while testing the application.

- **Developer:**  
Implements the design with guidance from the Development Lead.  

**Team Members' Roles:**


| Resource     | Main Role         |  Helper Role 1  |   
|:------------ |:----------------- |:--------------- |  
| Bulent       | Project Manager   | Tester          |  
| Chang        | Development Lead  | Developer       |
| Brandon      | Document Lead     | Developer       |
| Zhi          | QA Manager        | Developer       |  

## 4 Estimates

Assumptions:  
  - Expected application usage:  
    
	(Windows) **C:\\>** java wc essay.txt -l 3 -d ?;!:.   
	(Linux) **user@host:~$** java wc essay.txt -l 3 -d ?;!:.   
	(Mac) **User-computer:~ user$** java wc essay.txt -l 3 -d ?;!:.   
	
  - The pessimistic (worst case) estimate for hours is provided.    
  - The projects takes place in 3 parts with a week to finish each part.

**Expected hours of effort from each team member:**

- Bulent Coskun (32h)  
- Brandon Chong (32h)  
- Chang Liu (33h)  
- Zhi Zhang (33h)  


**Calculation Details:**


Team Effort: (9h)


	1st Week
		3h Project Planning
		2h Requirements
		1h QA Office Hours Analysis
	2nd Week
		2-3h


Project Management: (13h)


	1st Week
		-min 6h, max 10h
	2nd Week
		-min 2h, max 3h


Documentation Lead: (13h)


	1st Week
		-min 1h, max 3h
	2nd Week
		-min 6h, max 10h (Prepare user Manual document)


Development Lead: (14h)


	1st Week
		-min 2h, max 4h (Study basic requirements and basic coding implementation such as reading the input file)
	2nd Week
		-min 6h, max 10h


QA Manager: (14h)


	1st Week
		-min 2h, max 4h (Study basic requirements)
	2nd Week
		-min 6h, max 10h (Execution of the Test)
	

Developer: (10h)


	1st Week  
		-min 2h, max 4h (Study Java and implement basic classes and project ideas)
	2nd Week
		-min 4h, max 6h (Coding and bug fixes)


Testing: (10h)


	1st Week
		- min 2h, max 4h (Study basic requirements and come up with basic test cases)    
	2nd Week
		- min 3h - max 6h (Define and Execution of test cases, report bugs and follow up with developers)
	


**Lines of code:** 920


Calculation Details:


	-Main Application Class - 20 LOC
	-Command Line Argument Interpretation Class - 100 to 200 LOC
	-Domain Class - 150 to 300 LOC
	-Unit Test Classes - 300 to 400 LOC (10 LOC per test case x 30 to 40 test cases)
