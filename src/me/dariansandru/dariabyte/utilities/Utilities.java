package me.dariansandru.dariabyte.utilities;

import me.dariansandru.dariabyte.lang.BinaryInt;

public class Utilities {

    public static boolean isBinaryInteger(String line){
        return BinaryInt.parseBinaryInt(line);
    }

    public static boolean isString(String line){
        return line.charAt(0) == '"' && line.charAt(line.length() - 1) == '"';
    }

    public static boolean isDigit(char c){
        return '0' <= c && c <= '9';
    }

    public static boolean isInteger(String line){
        int index = 0;
        if (line.charAt(0) == '-') index = 1;

        while (index != line.length()){
            if (!isDigit(line.charAt(index))) return false;
            index++;
        }
        return true;
    }

    public static String removeStringMark(String line){
        StringBuilder newString = new StringBuilder();
        for (int index = 1 ; index < line.length() - 1 ; index++){
            newString.append(line.charAt(index));
        }

        return newString.toString();
    }
}
