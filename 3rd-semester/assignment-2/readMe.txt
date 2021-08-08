Sorting application

This is a data sorting program. With this program, 
you can sort shape data in different sorting method. 
You must input sorting type, compare type and file 
name to execute this file.

Date: 10/26/2020
Author: HSIAO YU CHEN (Sean Chen)

How to run the program:
1. For Windows 10, open "Command prompt".
2. Change to the folder where this Sort.jar is located.
3. Type "java -jar Sort.jar" with parameters. 
   Example: "java -jar Sort.jar -ta -sq -fpolyfor1.txt"

Parameters:
-t   compare type
     a: compare area of shape
     h: compare height of shape
     v: compare volume of shape

-s   sorting type
     b: bubble sort
     s: selection sort
     i: insertion sort
     m: merge sort
     q: quick sort
     z: cocktail sort

-f   The file name. 
     The file must locate under "res" folder.