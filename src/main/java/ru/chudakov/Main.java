package ru.chudakov;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        A a = new A("");
    }
}

class A {
    A() {
        System.out.println("A");
    }

    A(String a) {
        System.out.println("A, str a");
    }

    A(int a) {
        System.out.println("A, int a");
    }
}

class B extends A {
    B() {
        System.out.println("B");
    }

    B(String b){
        System.out.println("B str b");
    }
}

