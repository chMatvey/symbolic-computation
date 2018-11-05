import com.sun.java_cup.internal.runtime.Symbol;
import symbolic.Sum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Sum symbol = new Sum<Integer>();
        List<A> list = new ArrayList<A>();
        list.add(new B());
        list.add(new B());
        list.add(new B());
        A a = list.get(0);
        A a1 = new C();
        a = a1;
        List<A> aaaaaaaa = new ArrayList<>(0);
    }
}

class A{
    @Override
    public String toString() {
        return "A";
    }
}

class B extends A{
    @Override
    public String toString() {
        return "B";
    }
}

class C extends A{
    @Override
    public String toString() {
        return "C";
    }
}
