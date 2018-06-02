# **User Manual for Word Count (WC)**

## Document Revisions

Author: 6300Spring16Team85   
Date: 2/9/2016   
Document Version: 1.0    
Document Changes: Final

## Table of Contents

1. Introduction   
1.1 Scope and Purpose   
1.2 Process Overview   
2. Using Word Count   
2.1 Saving Essay File   
2.2 Running Word Count with Default Settings   
2.3 Running Word Count with User Defined Settings   
2.4 Word Count Display   
3 Quick Reference   

## 1 Introduction

### 1.1 Scope and Purpose

The Word Count program is a program that calculates the words per sentence in a provided essay file and displays the words per sentence to the screen. This User Manual will show the User how to run the program with the default settings, how to run the program with User defined settings, and also how to troubleshoot any error messages.

### 1.2 Process Overview

Word Count must run on a computer using the Mac, Linux, or Window Operating System. The computer must have JRE version 1.7 or higher installed. The program should already be compiled and the .class files should be in the source folder (./bin\edu\gatech\seclass\project1\). The program is run on the command line of the operating system's shell. If you are having trouble with the setup please contact your class instructor.

The program can be run with the default settings or User defined settings for the following items:
* Minimum Word Length: The minimum letters in a word to be considered a valid word for counting. (Default = 4)
* Sentence Delimiters: The characters that denote the end of a sentence. (Default = ; . ! ? :)

## 2 Using Word Count

### 2.1 Saving Essay File

The Word Count program must be run on a valid text file saved on the computer's local drive. Save your essay file on your local system and make sure to record the file's full path name (this will be used as the [FILE PATH] in sections 2.2 and 2.3). 


### 2.2 Running Word Count with Default Settings

To run Word Count with the default settings follow the directions below:

1. Open the shell and navigate to the root directory where the Word Count program is.
2. Type the command below and press the Enter key.

		java -cp ./bin edu.gatech.seclass.project1.WC [FILE PATH]
		
Where:
* [FILE PATH] is the full path name of your essay file   

Note: If you manually compiled your program in another directory use the path for that directory instead of "./bin"


### 2.3 Running Word Count with User Defined Settings

To run Word Count with User defined settings follow the directions below:

1. Open the shell and navigate to the root directory where the Word Count program is.
2. Type the command below and press the Enter key.
		
		java -cp ./bin edu.gatech.seclass.project1.WC [FILE PATH] -l [MINWORDLENGTH] -d [DELIMITERS]
Where:
* [FILE PATH] is the full path name of your essay file
* [MINWORDLENGTH] is entered as an integer. It is used as the Minimum Word Length.
* [DELIMITERS] are entered as characters without spaces between them. They are used as the Sentence Delimiters.   

Note: You do not have to use both -l and -d as settings not defined by the User will be set to their default values. If you do use -l or -d you must provide an argument or you will get an error.

### 2.4 Word Count Display

When Word Count is run successfully the program will output a number which is the average number of words per sentence.

## 3 Quick Reference

NAME   
&nbsp; &nbsp; &nbsp;wc - word count for essay file

USAGE   
&nbsp; &nbsp; &nbsp;java -cp ./bin edu.gatech.seclass.project1.WC [FILE PATH] [OPTIONS]

OPTIONS   
&nbsp; &nbsp; &nbsp;-d 	[DELIMITERS]	User defined Sentence Delimiters   
&nbsp; &nbsp; &nbsp;-l 	[MINWORDLENGTH]	User defined Minimum Word Length

