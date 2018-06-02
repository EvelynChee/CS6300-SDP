# **Requirements Document -- Team 85**

## 1 User Requirements

Users want to analyze their essays and determine the average words per sentence for their essays.  

### 1.1 Software Interfaces

1.1.1. The application shall run on the Mac, Linux, and Windows Operating Systems.  
1.1.2. The application shall run in the command shell (Console).  
1.1.3. The application shall utilize the JVM and standard Java libraries.  
1.1.4. The application shall run with JVM 1.7 and above.  
1.1.5. The application shall use a raw text file on the hard drive as the input.  

### 1.2 User Interfaces  

1.2.1. The User shall store the essay text file on their local hard drive.  
1.2.2. The User shall execute the application and provide parameters to run in using the command line in the shell.  
1.2.3. The minimum input from the User shall be to provide the essay's file name with the full path.  
1.2.4. The User has the option to specify a parameter of what delimiters will be used to split the sentences.  
1.2.5. The User has the option to specify a parameter of the minimum word length.  
1.2.6. If the User does not specify any parameters the default delimiters and minimum word length are used.  
1.2.6. The application shall output to the command shell the average number of words for sentences in the essay.  

### 1.3 User Characteristics  

1.3.1. The Users are academic staff and students.  
1.3.2. The Users can read and write in English.  
1.3.3. The Users shall be able to open their OS's command shall and input commands on the command line.  
1.3.4. The Users shall be able to install JVM on their computer if Java is not available.  
1.3.5. The Users technical expertise varies from a non-technical to technical experts.  

## 2 System Requirements

### 2.1 Functional Requirements

2.1.1.  The application shall be able to analyze essay raw text files, calculate, and output the average number of words per sentence to the command shell.  
2.1.2.  If the User provides incorrect parameters the application will provide friendly error messages.  
2.1.3.  The User can use -d parameter option to specify non-default sentence delimiters.  
2.1.4.  The User can present parameters to application in any order.  
2.1.5.  The User can use -l parameter option to specify a non-default minimum word length. It is inclusive.   
2.1.6.  The application will use ".:?!;" characters as the default sentence delimiters.   
2.1.7.  The application will use 4 letters as the default minimum word length when evaluating the words per sentence in the essay.  
2.1.8.  The application shall provide the result as single number with 2 decimals. example; 9.656 rounded to 9.67   
2.1.9.  The application shall ignore 0 length sentences.    
2.1.10. Essay File shall be provided with any file extension.   
2.1.11. All Errors shall be displayed "ERROR: {ERROR MESSAGE}" format.   

### 2.2 Non-Functional Requirements

2.2.1. The User shall be able to run the program in JVM 1.7 or higher.  
2.2.2. The User shall be able to run the application on Mac, Linux, and Windows Operating Systems.  
2.2.3. The application shall process minimum 5000 characters per second.   
2.2.4. Application will be called "wc"   
2.2.5. Application shall process the essay file with ascii encoding.   
2.2.6. Errors messages shall be sent to System Standard Error console. 