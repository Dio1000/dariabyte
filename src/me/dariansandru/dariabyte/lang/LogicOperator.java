package me.dariansandru.dariabyte.lang;

public abstract class LogicOperator {

    public static Integer convertToB10(String valb2){
        int size = valb2.length() - 1;
        int current = size;
        int newVal = 0;

        while (current >= 0){
            newVal += (int) (Math.pow(2, size - current) * Integer.parseInt(String.valueOf(valb2.charAt(current))));
            current--;
        }
        return newVal;
    }

    public static String convertToB2(Integer valb10){
        StringBuilder newVal = new StringBuilder();

        while (valb10 != 0){
            newVal.append(valb10 % 2);
            valb10 /= 2;
        }

        newVal.reverse();
        return newVal.toString();
    }

    private static char bitAnd(char bit1, char bit2){
        if (bit1 == '1' && bit2 == '1') return '1';
        return '0';
    }

    private static char bitOr(char bit1, char bit2){
        if (bit1 == '0' && bit2 == '0') return '0';
        return '1';
    }

    public static String binaryAnd(BinaryInt binary1, BinaryInt binary2){
        BinaryInt newBinary = new BinaryInt();

        String binaryVal1 = binary1.getValb2();
        int current1 = binary1.getSize() - 1;

        String binaryVal2 = binary2.getValb2();
        int current2 = binary2.getSize() - 1;

        StringBuilder newVal = new StringBuilder();

        while (current1 >= 0 && current2 >= 0){
            newVal.append(bitAnd(binaryVal1.charAt(current1), binaryVal2.charAt(current2)));
            current1--;
            current2--;
        }

        while (current1 >= 0){
            newVal.append('0');
            current1--;
        }

        while (current2 >= 0){
            newVal.append('0');
            current2--;
        }

        newVal.reverse();
        newBinary.setValb2(newVal.toString());
        return newBinary.getValb2();
    }

    public static String binaryOr(BinaryInt binary1, BinaryInt binary2){
        BinaryInt newBinary = new BinaryInt();

        String binaryVal1 = binary1.getValb2();
        int current1 = binary1.getSize() - 1;

        String binaryVal2 = binary2.getValb2();
        int current2 = binary2.getSize() - 1;

        StringBuilder newVal = new StringBuilder();

        while (current1 >= 0 && current2 >= 0){
            newVal.append(bitOr(binaryVal1.charAt(current1), binaryVal2.charAt(current2)));
            current1--;
            current2--;
        }

        while (current1 >= 0){
            newVal.append(binaryVal1.charAt(current1));
            current1--;
        }

        while (current2 >= 0){
            newVal.append(binaryVal2.charAt(current2));
            current2--;
        }

        newVal.reverse();
        newBinary.setValb2(newVal.toString());
        return newBinary.getValb2();
    }

    public static String binaryNand(BinaryInt binary1, BinaryInt binary2){
        BinaryInt newBinary = new BinaryInt();
        newBinary.setValb2(binaryAnd(binary1, binary2));
        newBinary.binaryNegation();

        return newBinary.getValb2();
    }

    public static String binaryNor(BinaryInt binary1, BinaryInt binary2){
        BinaryInt newBinary = new BinaryInt();
        newBinary.setValb2(binaryOr(binary1, binary2));
        newBinary.binaryNegation();

        return newBinary.getValb2();
    }

    public static String BinaryAdd(BinaryInt binary1, BinaryInt binary2){
        BinaryInt newBinary = new BinaryInt();

        int valueBase10 = binary1.getValb10();
        int otherValueBase10 = binary2.getValb10();
        newBinary.setValb10(valueBase10 + otherValueBase10);

        return newBinary.getValb2();
    }

    public static String BinarySub(BinaryInt binary1, BinaryInt binary2){
        BinaryInt newBinary = new BinaryInt();

        int valueBase10 = binary1.getValb10();
        int otherValueBase10 = binary2.getValb10();
        newBinary.setValb10(valueBase10 - otherValueBase10);

        return newBinary.getValb2();
    }

    public static String BinaryMul(BinaryInt binary1, BinaryInt binary2){
        BinaryInt newBinary = new BinaryInt();

        int valueBase10 = binary1.getValb10();
        int otherValueBase10 = binary2.getValb10();
        newBinary.setValb10(valueBase10 * otherValueBase10);

        return newBinary.getValb2();
    }

    public static String BinaryDiv(BinaryInt binary1, BinaryInt binary2){
        BinaryInt newBinary = new BinaryInt();

        int valueBase10 = binary1.getValb10();
        int otherValueBase10 = binary2.getValb10();
        newBinary.setValb10(valueBase10 / otherValueBase10);

        return newBinary.getValb2();
    }

    public static String BinaryMod(BinaryInt binary1, BinaryInt binary2){
        BinaryInt newBinary = new BinaryInt();

        int valueBase10 = binary1.getValb10();
        int otherValueBase10 = binary2.getValb10();
        newBinary.setValb10(valueBase10 % otherValueBase10);

        return newBinary.getValb2();
    }
}
