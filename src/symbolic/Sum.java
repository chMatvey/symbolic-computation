package symbolic;

import symbolic.term.TermNumber;
import symbolic.term.Term;

public class Sum<T extends Number> extends AbstractSymbol<T>{

    private Term<T> data;

    private AbstractSymbol degree;

    public Sum(){
        super();
    }

    public Sum(T number){
        super();
        degree = createOne();
        data = new TermNumber<T>(number);
    }

    public Sum(Term<T> data){
        super();
        degree = createOne();
        this.data = data;
    }

    public Sum(Term<T> data, AbstractSymbol coefficient, AbstractSymbol degree){
        super(coefficient);
        this.data = data;
        this.degree = degree;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass() || super.equals(obj)) return false;
        Sum sum = (Sum) obj;
        return data.equals(sum.data);
    }

    @Override
    public int compareTo(AbstractSymbol o) {
        int result = super.compareTo(o);
        if (result == 0 && countBranches == 0){
            Sum sum = (Sum) o;
        }
        return 0;
    }
}
