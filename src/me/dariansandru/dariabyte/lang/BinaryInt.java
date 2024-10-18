package me.dariansandru.dariabyte.lang;

public class BinaryInt {

    private Integer size;
    private Integer valb10;
    private String valb2;

    public BinaryInt(){
        this.size = 0;
        this.valb2 = "";
        this.valb10 = 0;
    }

    public BinaryInt(String valb2){
        this.size = valb2.length();
        this.valb2 = valb2;
        this.valb10 = LogicOperator.convertToB10(valb2);
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getValb10() {
        return valb10;
    }

    public void setValb10(Integer valb10) {
        this.valb10 = valb10;
        this.valb2 = LogicOperator.convertToB2(valb10);
        this.size = valb2.length();
    }

    public String getValb2() {
        return valb2;
    }

    public void setValb2(String valb2) {
        this.valb2 = valb2;
        this.valb10 = LogicOperator.convertToB10(valb2);
        this.size = valb2.length();
    }

    public void binaryNegation() {
        String binaryVal = this.getValb2();
        StringBuilder newBinaryVal = new StringBuilder();

        for (int index = 0; index < binaryVal.length(); index++) {
            char currentBit = binaryVal.charAt(index);
            if (currentBit == '0') newBinaryVal.append('1');
            else newBinaryVal.append('0');
        }

        this.setValb2(newBinaryVal.toString());
    }

    public static boolean parseBinaryInt(String number){
        for (int index = 0 ; index < number.length() ; index++){
            if (number.charAt(index) != '0' && number.charAt(index) != '1') return false;
        }
        return true;
    }
}
