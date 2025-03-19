# filesprocessingengine
**File Processing Engine for Search System** is designed to process large files, apply rules-based indexing, and generate search analysis results. 

This is project is designed to process large files, apply rules-based indexing, and generate search analysis results. 
- Provides command line Menu for guiding user.
- Process files provided on command line argument OR Asks source directory to process files. 
- Works for business rules in the requirement.
	A. Output the number of words that start with upper case letter in each file 
	B. List all the words that are longer than 5 characters long in each file 
- You can create new business rules easily without modifying the core logic.
- It could be added with rich features in future releases.
- One can use this application - Just build this Maven project and use as a command line utillity.

Here is sample information from development box.
There are 2 ways to use this CLI Utility.

**Approach 1 -** Run Application from Command line and provide file to the same.
**java -jar filesprocessingengine-1.0.jar C:\Coding\test\test.txt C:\Coding\test\info.txt**

**Enter Empty Directory for Processing C:\Coding\test\test.txt Source File :** C:\Coding\temp

----------------------------------------------------------------------------------------------------------------------------
                                                                 File Name: test.txt
----------------------------------------------------------------------------------------------------------------------------
RULE_UPPERCASE_START_WORDS_COUNT 	          :: No of words starting with a capital letter: 3
RULE_MIN_LENGTH_WORDS                   		:: No of words with length more than 5 are : [operations, created, analysis, sample]

**Enter Empty Directory for Processing C:\Coding\test\info.txt Source File :** C:\Coding\temp

----------------------------------------------------------------------------------------------------------------------------
                                                                 File Name: info.txt
----------------------------------------------------------------------------------------------------------------------------
RULE_UPPERCASE_START_WORDS_COUNT   	        :: No of words starting with a capital letter: 1
RULE_MIN_LENGTH_WORDS                   		:: No of words with length more than 5 are : [project, information]

Choose an option:
1. Perform an File Processing Operation.
2. Exit.
Your option:** 2**
Spring Boot application stopped successfully.


**Approach 2** - Just run application from Command line.
**java -jar filesprocessingengine-1.0.jar**

**Enter Empty Directory for Processing C:\Coding\test\ Source File :** C:\Coding\temp

----------------------------------------------------------------------------------------------------------------------------
                                                                 File Name: test.txt
----------------------------------------------------------------------------------------------------------------------------
RULE_UPPERCASE_START_WORDS_COUNT 	          :: No of words starting with a capital letter: 3
RULE_MIN_LENGTH_WORDS                   		:: No of words with length more than 5 are : [operations, created, analysis, sample]

----------------------------------------------------------------------------------------------------------------------------
                                                                 File Name: info.txt
----------------------------------------------------------------------------------------------------------------------------
RULE_UPPERCASE_START_WORDS_COUNT   	:: No of words starting with a capital letter: 1
RULE_MIN_LENGTH_WORDS                   		:: No of words with length more than 5 are : [project, information]

Choose an option:
1. Perform an File Processing Operation.
2. Exit.
Your option: **2**
Spring Boot application stopped successfully.

Note: We need to provide temporary empty directory as well, because using Apache camel file component for efficiency, which requires directory to process file. Hence, you see above, command line ask "Enter Empty Directory for Processing <Source File Name> Source File :"

