package symbolic;

import symbolic.term.Function;
import symbolic.term.Number;
import symbolic.term.Terms;

public class Sum<T extends java.lang.Number> extends Symbol<T>{

    private Terms<T> data;

    public Sum(){
        super();
    }

    public Sum(int countBranches){
        super(countBranches);
    }

    public Sum(java.lang.Number number){
        super();
        data = new Number(number);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Sum.class){
            Sum sum = (Sum)obj;
            if(sum.data == this.data && this.data.getClass() != Function.class){
                return true;
            } else if(sum.branches.size() == this.branches.size() && sum.data == this.data) {
                int size = sum.branches.size();
                boolean existEquals = false;
                for(int i = 0; i < size; i++){
                    for (int j = 0; j < size; j++){
                        if (this.branches.get(i).equals(sum.branches.get(j))){
                            Symbol<T> symbol = (Symbol<T>) sum.branches.get(i);
                            sum.branches.set(i, sum.branches.get(j));
                            sum.branches.set(j, symbol);
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
