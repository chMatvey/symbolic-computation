package ru.chudakov.symbolic.term;

public abstract class AbstractTerm<T extends Number> implements Term<T> {

    public abstract TermTypes getType();

    public abstract String getExpressionName();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractTerm term = (AbstractTerm) obj;
        return getExpressionName().equals(term.getExpressionName());
    }

    @Override
    public int compareTo(Term o) {
        if (this.getClass() == o.getClass()){
            return 0;
        } else if (this.getClass() == Function.class) {
            return -1;
        } else if(this.getClass() == TermNumber.class) {
            return 1;
        } else if (o.getClass() == Function.class){
            return 1;
        } else {
            return -1;
        }
    }
}
