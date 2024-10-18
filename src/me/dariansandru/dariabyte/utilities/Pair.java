package me.dariansandru.dariabyte.utilities;

public class Pair<Type1, Type2> {
    private final Type1 elem1;
    private final Type2 elem2;

    public Pair(Type1 elem1, Type2 elem2){
        this.elem1 = elem1;
        this.elem2 = elem2;
    }

    public Type1 getElem1() {
        return elem1;
    }

    public Type2 getElem2() {
        return elem2;
    }
}