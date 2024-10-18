package me.dariansandru.dariabyte.interpreter;

import me.dariansandru.dariabyte.lang.BinaryInt;
import me.dariansandru.dariabyte.lang.LogicOperator;
import me.dariansandru.dariabyte.utilities.Pair;
import me.dariansandru.dariabyte.utilities.Utilities;

import java.util.Objects;

public class Executor {
    public static void executeINT(String line){
        Pair<String, String> Pair = new Pair<>("INT", line.split(" ")[2]);
        Parser.varTypeMap.put(line.split(" ")[1], Pair);
    }

    public static void executeBINT(String line){
        Pair<String, String> Pair = new Pair<>("BINT", line.split(" ")[2]);
        Parser.varTypeMap.put(line.split(" ")[1], Pair);
    }

    public static void executeSTRING(String line){
        Pair<String, String> Pair = new Pair<>("STRING", Utilities.removeStringMark(line.split(" ")[2]));
        Parser.varTypeMap.put(line.split(" ")[1], Pair);
    }

    public static void executePRINT(String line){
        String var = line.split(" ")[1];
        if (Objects.equals(ValidParse.validPRINT(line).getElem2(), "isString")) System.out.println(Utilities.removeStringMark(var));
        else System.out.println(Parser.varTypeMap.get(var).getElem2());
    }

    public static void executeHALT(){
        executeJS("JS END");
    }

    public static void executeADD(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        String argumentType1 = ValidParse.getType(var1);

        if (argumentType1.equals("INT")){
            int sum = Integer.parseInt(value1) + Integer.parseInt(value2);
            ValidParse.updateValue(var1, Integer.toString(sum));
        }
        if (argumentType1.equals("STRING")){
            String sum = value1 + value2;
            ValidParse.updateValue(var1, sum);
        }
        if (argumentType1.equals("BINT")){
            BinaryInt binary1 = new BinaryInt(value1);
            BinaryInt binary2 = new BinaryInt(value2);

            String sum = LogicOperator.BinaryAdd(binary1, binary2);
            ValidParse.updateValue(var1, sum);
        }
    }

    public static void executeSUB(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        String argumentType1 = ValidParse.getType(var1);

        if (argumentType1.equals("INT")){
            int sub = Integer.parseInt(value1) - Integer.parseInt(value2);
            ValidParse.updateValue(var1, Integer.toString(sub));
        }
        if (argumentType1.equals("BINT")){
            BinaryInt binary1 = new BinaryInt(value1);
            BinaryInt binary2 = new BinaryInt(value2);

            String sub = LogicOperator.BinarySub(binary1, binary2);
            ValidParse.updateValue(var1, sub);
        }
    }

    public static void executeMUL(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        String argumentType1 = ValidParse.getType(var1);

        if (argumentType1.equals("INT")){
            int mul = Integer.parseInt(value1) * Integer.parseInt(value2);
            ValidParse.updateValue(var1, Integer.toString(mul));
        }
        if (argumentType1.equals("BINT")){
            BinaryInt binary1 = new BinaryInt(value1);
            BinaryInt binary2 = new BinaryInt(value2);

            String mul = LogicOperator.BinaryMul(binary1, binary2);
            ValidParse.updateValue(var1, mul);
        }
    }

    public static void executeDIV(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        String argumentType1 = ValidParse.getType(var1);

        if (argumentType1.equals("INT")){
            int div = Integer.parseInt(value1) / Integer.parseInt(value2);
            ValidParse.updateValue(var1, Integer.toString(div));
        }
        if (argumentType1.equals("BINT")){
            BinaryInt binary1 = new BinaryInt(value1);
            BinaryInt binary2 = new BinaryInt(value2);

            String div = LogicOperator.BinaryDiv(binary1, binary2);
            ValidParse.updateValue(var1, div);
        }
    }

    public static void executeMOD(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        String argumentType1 = ValidParse.getType(var1);

        if (argumentType1.equals("INT")){
            int mod = Integer.parseInt(value1) % Integer.parseInt(value2);
            ValidParse.updateValue(var1, Integer.toString(mod));
        }
        if (argumentType1.equals("BINT")){
            BinaryInt binary1 = new BinaryInt(value1);
            BinaryInt binary2 = new BinaryInt(value2);

            String mod = LogicOperator.BinaryMod(binary1, binary2);
            ValidParse.updateValue(var1, mod);
        }
    }

    public static void executeAND(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        BinaryInt binary1 = new BinaryInt(value1);
        BinaryInt binary2 = new BinaryInt(value2);

        String and = LogicOperator.binaryAnd(binary1, binary2);
        ValidParse.updateValue(var1, and);
    }

    public static void executeNAND(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        BinaryInt binary1 = new BinaryInt(value1);
        BinaryInt binary2 = new BinaryInt(value2);

        String nand = LogicOperator.binaryNand(binary1, binary2);
        ValidParse.updateValue(var1, nand);
    }

    public static void executeOR(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        BinaryInt binary1 = new BinaryInt(value1);
        BinaryInt binary2 = new BinaryInt(value2);

        String or = LogicOperator.binaryOr(binary1, binary2);
        ValidParse.updateValue(var1, or);
    }

    public static void executeNOR(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        BinaryInt binary1 = new BinaryInt(value1);
        BinaryInt binary2 = new BinaryInt(value2);

        String nor = LogicOperator.binaryNor(binary1, binary2);
        ValidParse.updateValue(var1, nor);
    }

    public static void executeDEF(String line){
        Parser.sectionLineMap.put(line.split(" ")[1], Parser.currentlyExecutedIndex);
    }

    public static void executeMOV(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];

        Pair<String, String> Pair = new Pair<>(ValidParse.getType(var1), ValidParse.getValue(var2));
        Parser.varTypeMap.put(var1, Pair);
    }

    public static void executeJS(String line){
        if (line.equals("JS END")) Parser.currentlyExecutedIndex = Parser.lineList.size() - 1;
        Parser.currentlyExecutedIndex = ValidParse.getLineBySection(line.split(" ")[1]);
    }

    public static void executeJNZ(String line){
        if (Integer.parseInt(ValidParse.getValue(line.split(" ")[1])) != 0)
            Parser.currentlyExecutedIndex = ValidParse.getLineBySection(line.split(" ")[2]);
    }

    public static void executeJZ(String line){
        if (Integer.parseInt(ValidParse.getValue(line.split(" ")[1])) == 0)
            Parser.currentlyExecutedIndex = ValidParse.getLineBySection(line.split(" ")[2]);
    }

    public static void executeJG(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];
        String destination = line.split(" ")[3];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        if (Integer.parseInt(value1) > Integer.parseInt(value2))
            Parser.currentlyExecutedIndex = ValidParse.getLineBySection(destination);
    }

    public static void executeJGE(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];
        String destination = line.split(" ")[3];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        if (Integer.parseInt(value1) >= Integer.parseInt(value2))
            Parser.currentlyExecutedIndex = ValidParse.getLineBySection(destination);
    }

    public static void executeJL(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];
        String destination = line.split(" ")[3];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        if (Integer.parseInt(value1) < Integer.parseInt(value2))
            Parser.currentlyExecutedIndex = ValidParse.getLineBySection(destination);
    }

    public static void executeJLE(String line){
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];
        String destination = line.split(" ")[3];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        if (Integer.parseInt(value1) <= Integer.parseInt(value2))
            Parser.currentlyExecutedIndex = ValidParse.getLineBySection(destination);
    }

    public static void executeJE(String line) {
        String var1 = line.split(" ")[1];
        String var2 = line.split(" ")[2];
        String destination = line.split(" ")[3];

        String value1 = ValidParse.getValue(var1);
        String value2 = ValidParse.getValue(var2);

        if (Integer.parseInt(value1) == Integer.parseInt(value2))
            Parser.currentlyExecutedIndex = ValidParse.getLineBySection(destination);
    }
}
