package parser;


import eval.State;

public class LiteralAST extends ExpressionAST{
    private int value;
    public LiteralAST(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Litteral(" + String.valueOf(this.value) + ")";
    }

    @Override
    public int eval(State<Integer> state, State<FuncDefAST> funcs) {
        return this.value;
    }
}
