package parser;

import eval.State;

public class ConditionalExpressionAST extends ExpressionAST {
    private ExpressionAST exp1;
    private ExpressionAST exp2;
    private ExpressionAST exp3;

    public ConditionalExpressionAST(ExpressionAST exp1, ExpressionAST exp2, ExpressionAST exp3){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public String toString() {
        return "IfExpression(" + exp1 + ", " + exp2 + ", " + exp3 + ")";
    }

    @Override
    public int eval(State<Integer> state, State<FuncDefAST> funcs) {
        return exp1.eval(state, funcs) == 0 ? exp3.eval(state, funcs) : exp2.eval(state, funcs);
    }
}
