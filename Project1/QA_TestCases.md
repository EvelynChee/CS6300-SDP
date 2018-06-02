# Test Cases For Application "wc"

##### Case 1   
Goal: Test normal Case  
Application name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\general.txt  
Minimum Word Length: -l 4  
Delimiters: -d ?;!:.  
Expected Result: 3.60 (Words: 18; Sentences: 5)  
Actual Result: Pass  

##### Case 2   
Goal: Test Empty File  
Application name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\tb0.txt  
Minimum Word Length: -l 4  
Delimiters: -d ?;!:.
Expected Result: 0.00 (Words: 0; Sentences: 0)
Actual Result: Pass
   
##### Case 3  
Goal: Test no filename provided  
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\  
Minimum Word Length:  
Delimiters:  
Expected Result: File name not been provided for analysis!  
Actual Result: Pass  

##### Case 4
Goal: Test User doesn't enter delimiter in terminal line   
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\general.txt  
Minimum Word Length: -l 4  
Delimiters: -d   
Expected Result: ERROR: argument missing for parameter -d   
Actual Result: Pass  

##### Case 5
Goal: Test User doesn't enter minimum word length in the terminal line   
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\general.txt   
Minimum Word Length: -l   
Delimiters: -d ?;!:.  
Expected Result: ERROR: argument missing for parameter -l  
Actual Result: Pass  

##### Case 6
Goal: Test User provides minimum word length not as number  
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\general.txt   
Minimum Word Length: -l four   
Delimiters: -d ?;!:.  
Expected Result: Parameter -l should be used with integer number only   
Actual Result: Pass  

##### Case 7
Goal: Test User provides negative word length  
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\general.txt   
 Minimum Word Length: -l -4  
Delimiters: -d ?;!:.  
Expected Result: ERROR: Unrecognized key -4  
Actual Result: Pass  

##### Case 8
Goal: Test User provides unrecognized key  
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\general.txt   
 Minimum Word Length: -l -5  
Delimiters: -x ?;!:.  
Expected Result: ERROR: Unrecognized key -x  
Actual Result: Pass  

##### Case 9
Goal: Test User enters minimum word length as zero  
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\general.txt 
Minimum Word Length: -l 0  
Delimiters: -d ?;!:.  
Expected Result: ERROR: Word length limit cannot be 0  
Actual Result: Pass  

##### Case 10  
Goal: Test User enters other characters rather than ?;!:. as delimiters   
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\other_delimiter.txt 
Minimum Word Length: -l 4  
Delimiters: -d a  
Expected Result: 2.00 (Words: 4; Sentences: 2)  
Actual Result: Pass  

##### Case 11  
Goal: Test program runs on docx file 
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\docx.docx  
Minimum Word Length: -l 4  
Delimiters: -d ?;!:.  
Expected Result: 1.00 (Words: 2; Sentences: 2)  
Actual Result: Pass  

##### Case 12  
Goal: Test program runs on pdf file   
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\pdf.pdf 
Minimum Word Length: -l 4  
Delimiters: -d ?;!:.  
Expected Result: 1.00 (Words: 1; Sentences: 1)  
Actual Result: Pass  

##### Case 13
Goal: Test program runs on gif file   
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\gif.gif 
Minimum Word Length: -l 4  
Delimiters: -d ?;!:.  
Expected Result: 1.00 (Words: 1; Sentences: 1)  
Actual Result: Pass

##### Case 14
Goal: Test sentence with 0 word count  
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\tb2.txt   
Minimum Word Length: -l 4  
Delimiters: -d ?;!:.  
Expected Result: 0.00 (Words: 0; Sentences: 0)  
Actual Result: Pass  

##### Case 15
Goal: Test tab, space, carriage return, line-feed as word delimiter  
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\delimit_word.txt   
Minimum Word Length: -l 4  
Delimiters: -d ?;!:.  
Expected Result: 3.00 (Words: 3; Sentences: 1)  
Actual Result: Pass

##### Case 16
Goal: Test sequence other than characters counted as word  
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC  
File Path: ~\digit word.txt 
Minimum Word Length: -l 4  
Delimiters: -d $  
Expected Result: 1.00 (Words: 1; Sentences: 1)  
Actual Result: Pass  

##### Case 17  
Goal: Test All ASCII characters   
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC    
File Path: ~\ASCII.txt  
Minimum Word Length: -l 1    
Delimiters: -d .    
Expected Result: 110.00 (Words: 220; Sentences: 2)    
Actual Result: Pass    

##### Case 18
Goal: Test last sentence end with no delimiter     
Application Name: java -cp ./bin edu.gatech.seclass.project1.WC    
File Path: ~\last sentence no end delimiter.txt    
Minimum Word Length: -l 4    
Delimiters: -d ?!;.:    
Expected Result: 2.00 (Words: 4; Sentences: 2)    
Actual Result: Pass    
