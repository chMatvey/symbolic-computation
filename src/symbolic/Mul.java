package symbolic;

public class Mul<T extends java.lang.Number> extends Symbol<T> {

    public Mul(){
        super();
    }

    public Mul(int countBranches){
        super(countBranches);
    }

    public void checkBranches(){
        for (Symbol<T> s: this.branches) {
            if(!s.branches.isEmpty()){
                mulTerms();
                return;
            }
        }
    }

    public void mulTerms(){
        int i = 0;
        for (Symbol<T> s: this.branches) {
            if(s.branches.isEmpty()){

            } else {
                if (this.coefficients.get(i) != null){
                }
            }
        }
    }

    public void toTheExtent(){

    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Mul.class){
            Mul mul = (Mul)obj;
            if(mul.branches.size() == this.branches.size()){
                int size = this.branches.size();
                boolean existEquals = false;
                for (int i = 0; i < size; i++){
                    for(int j = 0; j < size; j++){
                        if(this.branches.get(i).equals(mul.branches.get(j))){
                            Symbol<T> symbol = (Symbol<T>) mul.branches.get(i);
                            mul.branches.set(i, mul.branches.get(j));
                            mul.branches.set(j, symbol);
                            existEquals = true;
                            break;
                        }
                    }
                    if(!existEquals){
                        return false;
                    }
                    existEquals = false;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
