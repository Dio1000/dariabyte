package me.dariansandru.dariabyte.interpreter;

import me.dariansandru.dariabyte.utilities.Pair;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public abstract class Parser {
    public static Map<String, Pair<String, String>> varTypeMap = new HashMap<>();
    public static Map<String, Integer> sectionLineMap = new HashMap<>();

    public static List<String> lineList = new ArrayList<String>();
    private static final List<String> errorList = new ArrayList<String>();

    private static boolean validCode = true;
    public static Integer currentlyExecutedIndex = 1;

    public static void readFile(){
        try {
            File myObj = new File("code.txt");
            Scanner reader = new Scanner(myObj);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                lineList.add(data);
            }
            lineList.add("DEF END");
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void getSections(){
        for (String line: lineList){
            if (!line.isEmpty()){
                String opcode = line.split(" ")[0];

                if (Objects.equals(opcode, "DEF")){
                    if (!ValidParse.validSection(line).getElem1()) {
                        errorList.add(ValidParse.validSection(line).getElem2());
                        validCode = false;
                        return;
                    }
                    Executor.executeDEF(line);
                }
            }
            currentlyExecutedIndex++;
        }

        if (!sectionLineMap.containsKey("start")){
            System.out.println("Section start was not defined.");
            Executor.executeHALT();
            return;
        }

        currentlyExecutedIndex = ValidParse.getLineBySection("start") + 1;

    }

    public static void parseLine(String line){
        if (line.isEmpty()) return;

        line = line.strip();
        String opcode = line.split(" ")[0];

        switch (opcode){
            case "DEF" -> {
                Executor.executeHALT();
            }

            case "HALT" -> {
                if (!ValidParse.validHALT(line).getElem1()) {
                    errorList.add(ValidParse.validHALT(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeHALT();
            }

            case "INT" -> {
                if (!ValidParse.validINT(line).getElem1()) {
                    errorList.add(ValidParse.validINT(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeINT(line);
            }

            case "BINT" -> {
                if (!ValidParse.validBINT(line).getElem1()) {
                    errorList.add(ValidParse.validBINT(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeBINT(line);
            }

            case "STRING" -> {
                if (!ValidParse.validSTRING(line).getElem1()) {
                    errorList.add(ValidParse.validSTRING(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeSTRING(line);
            }

            case "PRINT" -> {
                if (!ValidParse.validPRINT(line).getElem1()) {
                    errorList.add(ValidParse.validPRINT(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executePRINT(line);
            }

            case "ADD" -> {
                if (!ValidParse.validOperation(line).getElem1()){
                    errorList.add(ValidParse.validOperation(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeADD(line);
            }

            case "SUB" -> {
                if (!ValidParse.validOperation(line).getElem1()){
                    errorList.add(ValidParse.validOperation(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeSUB(line);
            }

            case "MUL" -> {
                if (!ValidParse.validOperation(line).getElem1()){
                    errorList.add(ValidParse.validOperation(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeMUL(line);
            }

            case "DIV" -> {
                if (!ValidParse.validOperation(line).getElem1()){
                    errorList.add(ValidParse.validOperation(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeDIV(line);
            }

            case "MOD" -> {
                if (!ValidParse.validOperation(line).getElem1()){
                    errorList.add(ValidParse.validOperation(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeMOD(line);
            }

            case "AND" -> {
                if (!ValidParse.validLogicalOperation(line).getElem1()){
                    errorList.add(ValidParse.validLogicalOperation(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeAND(line);
            }

            case "NAND" -> {
                if (!ValidParse.validLogicalOperation(line).getElem1()){
                    errorList.add(ValidParse.validLogicalOperation(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeNAND(line);
            }

            case "OR" -> {
                if (!ValidParse.validLogicalOperation(line).getElem1()){
                    errorList.add(ValidParse.validLogicalOperation(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeOR(line);
            }

            case "NOR" -> {
                if (!ValidParse.validLogicalOperation(line).getElem1()){
                    errorList.add(ValidParse.validLogicalOperation(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeNOR(line);
            }

            case "JS" -> {
                if (!ValidParse.validJumpSection(line).getElem1()){
                    errorList.add(ValidParse.validJumpSection(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeJS(line);
            }

            case "JNZ" -> {
                if (!ValidParse.validJumpConditionSection(line).getElem1()){
                    errorList.add(ValidParse.validJumpConditionSection(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeJNZ(line);
            }

            case "JZ" -> {
                if (!ValidParse.validJumpConditionSection(line).getElem1()){
                    errorList.add(ValidParse.validJumpConditionSection(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeJZ(line);
            }

            case "JG" -> {
                if (!ValidParse.validJumpCompareCondition(line).getElem1()){
                    errorList.add(ValidParse.validJumpCompareCondition(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeJG(line);
            }

            case "JL" -> {
                if (!ValidParse.validJumpCompareCondition(line).getElem1()){
                    errorList.add(ValidParse.validJumpCompareCondition(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeJL(line);
            }

            case "JGE" -> {
                if (!ValidParse.validJumpCompareCondition(line).getElem1()){
                    errorList.add(ValidParse.validJumpCompareCondition(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeJGE(line);
            }

            case "JLE" -> {
                if (!ValidParse.validJumpCompareCondition(line).getElem1()){
                    errorList.add(ValidParse.validJumpCompareCondition(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeJLE(line);
            }

            case "JE" -> {
                if (!ValidParse.validJumpCompareCondition(line).getElem1()){
                    errorList.add(ValidParse.validJumpCompareCondition(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeJE(line);
            }

            case "MOV" -> {
                if (!ValidParse.validMOV(line).getElem1()){
                    errorList.add(ValidParse.validMOV(line).getElem2());
                    validCode = false;
                    return;
                }
                Executor.executeMOV(line);
            }

            default -> {
                errorList.add("Command " + line + " could not be found.");
                validCode = false;
            }
        }
    }

    public static void parseCode(){
        readFile();
        getSections();

        while (!Objects.equals(lineList.get(currentlyExecutedIndex - 1), "DEF END")){
            parseLine(lineList.get(currentlyExecutedIndex - 1));
            if (!validCode){
                System.out.println("Error at line " + currentlyExecutedIndex);
                errorList.forEach(System.out::println);
                return;
            }
            currentlyExecutedIndex++;

            if (currentlyExecutedIndex > lineList.size()) break;
        }
    }
}
