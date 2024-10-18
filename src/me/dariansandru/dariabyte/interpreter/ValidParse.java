package me.dariansandru.dariabyte.interpreter;

import me.dariansandru.dariabyte.lang.BinaryInt;
import me.dariansandru.dariabyte.utilities.Pair;
import me.dariansandru.dariabyte.utilities.Utilities;

import java.util.Objects;

public abstract class ValidParse {

    public static String getValue(String variable){
        if (!Parser.varTypeMap.containsKey(variable)) return variable;
        return Parser.varTypeMap.get(variable).getElem2();
    }

    public static String getType(String variable){
        return Parser.varTypeMap.get(variable).getElem1();
    }

    public static void updateValue(String variable, String newValue){
        Parser.varTypeMap.put(variable, new Pair<>(getType(variable), newValue));
    }

    public static int getLineBySection(String section){
        return Parser.sectionLineMap.get(section);
    }

    private static Pair<Boolean, String> validArgumentNumber(int size, int argumentNumber){
        if (size > argumentNumber) return new Pair<>(false, "Too many arguments provided.");
        if (size < argumentNumber) return new Pair<>(false, "Too few arguments provided.");
        else return new Pair<>(true, "Correct");
    }

    private static Pair<Boolean, String> variableAlreadyExists(String var){
        if (Parser.varTypeMap.containsKey(var))
            return new Pair<>(false, "Variable " + var + " already exists.");

        return new Pair<>(true, "Correct");
    }

    public static Pair<Boolean, String> validINT(String line){
        line = line.strip();
        int size = line.split(" ").length;

        if (!validArgumentNumber(size, 3).getElem1()) return validArgumentNumber(size, 3);
        if (!variableAlreadyExists(line.split(" ")[1]).getElem1()) return variableAlreadyExists(line.split(" ")[1]);

        try{
            int value = Integer.parseInt(line.split(" ")[2]);
            return new Pair<>(true, "Correct");
        }catch(NumberFormatException e){
            return new Pair<>(false, "Could not convert " + line.split(" ")[2] + " to base10 integer.");
        }
    }

    public static Pair<Boolean, String> validBINT(String line){
        line = line.strip();
        int size = line.split(" ").length;

        if (!validArgumentNumber(size, 3).getElem1()) return validArgumentNumber(size, 3);
        if (!variableAlreadyExists(line.split(" ")[1]).getElem1()) return variableAlreadyExists(line.split(" ")[1]);

        String value = line.split(" ")[2];
        if (!BinaryInt.parseBinaryInt(value)) return new Pair<>(false, "Could not convert " + value + " to base2 integer.");
        return new Pair<>(true, "Correct");
    }

    public static Pair<Boolean, String> validSTRING(String line){
        line = line.strip();
        int size = line.split(" ").length;

        if (!validArgumentNumber(size, 3).getElem1()) return validArgumentNumber(size, 3);
        if (!variableAlreadyExists(line.split(" ")[1]).getElem1()) return variableAlreadyExists(line.split(" ")[1]);

        String value = line.split(" ")[2];
        if (!Utilities.isString(value)) return new Pair<>(false, "Literal " + value + " is not string. Did you forget parentheses?");
        return new Pair<>(true, "Correct");
    }

    public static Pair<Boolean, String> validPRINT(String line){
        line = line.strip();
        int size = line.split( " ").length;

        if (!validArgumentNumber(size, 2).getElem1()) return validArgumentNumber(size, 2);
        if (Utilities.isString(line.split(" ")[1])) return new Pair<>(true, "isString");

        if (!Parser.varTypeMap.containsKey(line.split(" ")[1])) return new Pair<>(false, "Variable " + line.split(" ")[1] + " was not declared.");
        return new Pair<>(true, "isVariable");
    }

    public static Pair<Boolean, String> validOperation(String line){
        line = line.strip();
        int size = line.split( " ").length;

        String opcode = line.split( " ")[0];

        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        String argumentType1 = getType(var1);
        String argumentType2;

        if (!validArgumentNumber(size, 3).getElem1()) return validArgumentNumber(size, 3);
        if (variableAlreadyExists(var1).getElem1()) return new Pair<>(false, "Variable " + var1 + " does not exist.");

        if (variableAlreadyExists(var2).getElem1()){
            if (Utilities.isInteger(var2)) argumentType2 = "INT";
            else if (Utilities.isBinaryInteger(var2)) argumentType2 = "BINT";
            else argumentType2 = "STRING";
        }
        else argumentType2 = getType(var2);

        switch (opcode){
            case "ADD" -> {
                if (!Objects.equals(argumentType1, argumentType2))
                    return new Pair<>(false, "ADD between " + argumentType1 + " and " + argumentType2 + " is not valid.");
            }
            case "SUB", "MUL", "DIV", "MOD" -> {
                if (Objects.equals(argumentType1, "STRING") || Objects.equals(argumentType2, "STRING"))
                    return new Pair<>(false, opcode + " between " + argumentType1 + " and " + argumentType2 + " is not valid.");
                if (!Objects.equals(argumentType1, argumentType2))
                    return new Pair<>(false, opcode + " between " + argumentType1 + " and " + argumentType2 + " is not valid.");
            }
        }

        return new Pair<>(true, "Correct");
    }

    public static Pair<Boolean, String> validLogicalOperation(String line){
        line = line.strip();
        int size = line.split( " ").length;

        String opcode = line.split( " ")[0];
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        if (!validArgumentNumber(size, 3).getElem1()) return validArgumentNumber(size, 3);
        String argumentType1 = getType(var1);
        String argumentType2 = getType(var2);

        if (!Objects.equals(argumentType1, "BINT") || !Objects.equals(argumentType2, "BINT"))
            return new Pair<>(false, opcode + " between " + argumentType1 + " and " + argumentType2 + " is not valid.");

        return new Pair<>(true, "Correct");
    }

    public static Pair<Boolean, String> validSection(String line){
        line = line.strip();
        int size = line.split( " ").length;

        if (!validArgumentNumber(size, 2).getElem1()) return validArgumentNumber(size, 2);
        if (Parser.sectionLineMap.containsKey(line.split(" ")[1]))
            return new Pair<>(false, "Section " + line.split(" ")[1] + " already exists.");

        return new Pair<>(true, "Correct");
    }

    public static Pair<Boolean, String> validJumpSection(String line){
        line = line.strip();
        int size = line.split( " ").length;

        if (!validArgumentNumber(size, 2).getElem1()) return validArgumentNumber(size, 2);
        return new Pair<>(true, "Correct");
    }

    public static Pair<Boolean, String> validJumpConditionSection(String line){
        line = line.strip();
        int size = line.split( " ").length;

        if (!validArgumentNumber(size, 3).getElem1()) return validArgumentNumber(size, 3);
        if (!Parser.varTypeMap.containsKey(line.split(" ")[1]))
            return new Pair<>(false, "Variable " + line.split(" ")[1] + " was not declared.");

        if (!Parser.sectionLineMap.containsKey(line.split(" ")[2]))
            return new Pair<>(false, "Section " + line.split(" ")[2] + " was not declared.");

        if (!getType(line.split(" ")[1]).equals("INT") && !getType(line.split(" ")[1]).equals("BINT"))
            return new Pair<>(false, "Variable " + line.split(" ")[1] + " can not be converted to base10 integer.");

        return new Pair<>(true, "Correct");
    }

    public static Pair<Boolean, String> validMOV(String line){
        line = line.strip();
        int size = line.split( " ").length;

        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];
        String argumentType1 = getType(var1);
        String argumentType2;

        if (!validArgumentNumber(size, 3).getElem1())
            return validArgumentNumber(size, 3);

        if (variableAlreadyExists(var2).getElem1()){
            if (Utilities.isInteger(var2)) argumentType2 = "INT";
            else if (Utilities.isBinaryInteger(var2)) argumentType2 = "BINT";
            else argumentType2 = "STRING";
        }
        else argumentType2 = getType(var2);

        if (!Objects.equals(argumentType1, argumentType2))
            return new Pair<>(false, "Cannot move " + argumentType2+ " in " + argumentType1);

        return new Pair<>(true, "Correct");
    }

    public static Pair<Boolean, String> validJumpCompareCondition(String line){
        line = line.strip();
        int size = line.split( " ").length;

        if (!validArgumentNumber(size, 4).getElem1()) return validArgumentNumber(size, 4);
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        if (!Parser.varTypeMap.containsKey(line.split(" ")[1]))
            return new Pair<>(false, "Variable " + line.split(" ")[1] + " was not declared.");

        if (!Parser.varTypeMap.containsKey(line.split(" ")[2]))
            return new Pair<>(false, "Variable " + line.split(" ")[2] + " was not declared.");

        if (!Parser.sectionLineMap.containsKey(line.split(" ")[3]))
            return new Pair<>(false, "Section " + line.split(" ")[3] + " was not declared.");

        if (!Objects.equals(getType(var1), getType(var2)))
            return new Pair<>(false, "Cannot compare " + getType(var1) + " with " +  getType(var2));

        return new Pair<>(true, "Correct");
    }

    public static Pair<Boolean, String> validHALT(String line){
        line = line.strip();
        int size = line.split(" ").length;

        if (!validArgumentNumber(size, 1).getElem1()) return validArgumentNumber(size, 1);
        return new Pair<>(true, "Correct");
    }
}
