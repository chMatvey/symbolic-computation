package symbolic;

public interface Symbol<T extends Number> {
    public void addBranch(Symbol<T> branch);
}
