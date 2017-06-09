# SMS-Dictionary

A program that displays all possible matches for a digit sequence, using a dictionary


## General information

The numeric keypad to compose text messages is given below

|           |           |           |
| :-------- | :-------- | :-------- |
| **1**     | **2** abc | **3** def |
| **4** ghi | **5** jkl | **6** mno |
| **7** pqrs| **8** tuv | **9** wxyz|
|           | **0** space|          |

263 might be and, 748 might be sit, or pit, 666 might be mom, or non, etc.

For each line read (for each code), for each sequence of non-zero digits, the program displays the matching word from
the dictionary. When more than one match is available, it displays all matches in lexical order between
parentheses, separated by bars. If there is no matching word, it displays a sequence of asterisks of the same
length.

So for a dictionary containing the words..
```
chocolate
I
hear
loud
love
programming
```
..and the sequence..
```
0040568300077647266464077770
```
..the program would output on a single line:
```
I (loud|love) programming ****
```
The program outputs a single line for every line of input, no blank lines (unless a blank line is input).




## How do i run this code?
### Requirements
[ant](http://ant.apache.org/), java
### Running the code
```
$ ant run
```


## Output

The default code provided here gives the following output:
```
PhoneDict:

Main:
    [javac] Compiling 1 source file

compile:

run:
     [java] appointed (pound|round|sound) (clapped|clasped) *****

BUILD SUCCESSFUL
Total time: 5 seconds
```