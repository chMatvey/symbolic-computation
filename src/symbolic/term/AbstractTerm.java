package symbolic.term;

public abstract class AbstractTerm<T extends Number> implements Term<T>, Comparable<T> {

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
    public int compareTo(T o) {
        return 0;
    }
}
