package parser;

import eval.State;

public class VarIdentifierAST extends ExpressionAST {
    private String value;

    public VarIdentifierAST(String value){
        this.value = value;
    }



    @Override
    public String toString() {
        return "Variable(" + this.value + ")";
    }

    @Override
    public int eval(State<Integer> state, State<FuncDefAST> funcs) {
        Integer value = state.lookup(this.value);
        if(value == null)
            throw new SemanticException(this + " was not declared.");
        return value;
    }

    public String getValue(){
        return this.value;
    }
}
