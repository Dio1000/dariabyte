//TODO beautify error messages
//TODO fix errors like STRING val " HELLO", " HELLO" is a valid string
//TODO fix negative binary numbers
//TODO if a var already exists and is redeclared with the same vartype, allow it -- NOT NEECCEESSARRYY
//TODO Allow declaration with no given value -- MAYBE
//TODO Allow declaration with given variable
//TODO validOperation and executeADD are BEYOND FUCKED... but work

import me.dariansandru.dariabyte.interpreter.Parser;

public class Main {
    public static void main(String[] args) {
        Parser.parseCode();
    }
}