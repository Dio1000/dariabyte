# dariabyte

## About the Project
dariabyte is a test assembler language that is a predecessor to the daria programming language, a conceptual language that will be implemented in the future for logic and mathematical operations.

## How to Use
After cloning the repository, users will be able to find a file named `code.txt` (in a future update the executable code will be passed as an argument).
In this file, users will be able to write the code itself, which has the following instructions:

### Variable declaration
- **Integer**: INT x - declares an integer variable
- **Binary integer**: BINT x - declares a binary integer variable
- **String**: STRING x - declares a string variable

### Operations
All operations store the result in the first variable passed as argument.
- **Addition**: ADD x y - adds / concatenates two integers / strings
- **Substraction** SUB x y - substracts the values of two integers
- **Multiplication** MUL x y - multiplies the values of integers x and y
- **Division** DIV x y - divides the value of integer x by integer y
- **Modulo** MOD x y - gets the remainder of the division of integers x and y

### Binary operations
All operations store the result in the first variable passed as argument.
- **Conjunction**: AND x y - logical AND on two binary integers
- **Disjunction**: OR x y - logical OR on two binary integers
- **Negative Conjunction**: NAND x y - logical NAND on two binary integers
- **Negative Disjunction**: NOR x y - logical NOR on two binary integers

### Sections 
Sections are so-called functions that can be jumped to. This means that instructions can be "jumped over" (skipped). A few examples will be provided in the next section.
- **Section definition**: DEF section - Defines a new section
- **Halting** HALT - ends the execution of the program

### Jumps
Jumping to a section means ignoring all other lines that may be in-between the currently executed line and the first line of the specified section. The executor will start executing the lines contained in the specified section.
- **Jump section**: JS section - Jumps to a specified section
- **Jump zero**: JZ x section - Jumps to a specified section if the given value is zero
- **Jump not zero** JNZ x section - Jumps to a specified section if the given value is not zero
- **Jump greater** JG x y section - Jumps to a specified section if the given value x is greater than y
- **Jump lesser** JL x y section - Jumps to a specified section is the given value x is lesser than y
- **Jump greater-equal** JGE x y section - Jumps to a specified section if the given value x is greater or equal to y
- **Jump lesser-equal** JLE x y section - Jumps to a specified section if the given value x is lesser or equal to y

### Variable management 
- **Moving** MOV x y - Allocates the value of a variable y into x
- **Reallocation** REALLOC x y - Allocates the value of a constant value y into x
- **Deallocation** DEALLOC x - Deallocates the value of x (Now this variable will not be usable until declared again)

### Printing
- **Print** PRINT x - Prints x on the screen

Note: dariabyte code must define a `start` section, which will act as the main section (it will be executed first upon running the program).

## Examples

### While loop algorithm

```
INT decrement 1
INT x 10

DEF start
PRINT x
SUB x decrement
JNZ x start
PRINT "DONE"
```

This algorithm declares two integer variables, decrement and x.

A section `start` is also declared, which has the following instructions:

PRINT x - prints the value of x 

SUB x decrement - Substracts the value of decrement from x and stores the new value in x

JNZ x start - Jumps back to the third line (DEF start) if x is not zero

PRINT "DONE" - Prints "DONE" when it reaches that line (after x is equal to 0)

The output of this algorithm would be:
```
10
9
8
7
6
5
4
3
2
1
DONE
```

### Euclid's Algorithm

```
DEF start
    INT input 140
    INT copyInput 140
    INT check 140
    DIV input 2
    INT divisor 2
    JS primeLoop

DEF primeLoop
    JE divisor input endPrimeLoopTrue
    MOD check divisor
    JZ check endPrimeLoopFalse
    MOV check copyInput
    ADD divisor 1
    JS primeLoop

DEF endPrimeLoopTrue
    PRINT "TRUE"

DEF endPrimeLoopFalse
    PRINT "FALSE"
```

This set of instructions will print TRUE if the value of input is prime and FALSE otherwise.

## Prerequisites

To run dariabyte, you'll need:

- **Java JDK 17+**
- **Maven** (for building)

## Usage

1. **Clone the repository**
```bash
git clone https://github.com/Dio1000/dariabyte.git
```

2. **Navigate to the project directory**
```bash
cd dariabyte
```

3. **Build the project**
```bash
mvn clean package
```

4. **Run the assembler**
```bash
java -cp src/main/java me.dariabyte.Main
```

## Contact

Email: [sandru.darian@gmail.com](mailto:sandru.darian@gmail.com)  

ChessEd: [https://github.com/Dio1000/dariabyte](https://github.com/Dio1000/dariabyte)  
