package parser;

import eval.State;

public class UnaryExpressionAST extends ExpressionAST {
    private ExpressionAST expressionAST;

    public UnaryExpressionAST(ExpressionAST expressionAST){
        this.expressionAST = expressionAST;
    }

    @Override
    public String toString() {
        return  "UnaryExpression(" + this.expressionAST + ")";
    }

    @Override
    public int eval(State<Integer> state, State<FuncDefAST> funcs) {
        return -1*(this.expressionAST.eval(state, funcs));
    }
}
