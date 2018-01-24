package parser;

import eval.State;
import lexer.tokens.OpToken;

public class BinaryExpressionAST extends ExpressionAST {
    private ExpressionAST leftExpression;
    private ExpressionAST rightExpression;
    private OpToken operande;

    public BinaryExpressionAST(OpToken operande, ExpressionAST leftExpression, ExpressionAST rightExpression){
        this.operande = operande;
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public String toString() {
        return "BinaryExpression(" + this.operande + " " + this.leftExpression + " " + this.rightExpression + ")";
    }

    @Override
    public int eval(State<Integer> state, State<FuncDefAST> funcs) {
        int val1 = this.leftExpression.eval(state, funcs);
        int val2 = this.rightExpression.eval(state, funcs);
        switch (this.operande){
            case MINUS:
                return val1 - val2;
            case DIVIDE:
                return val1 / val2;
            case PLUS:
                return val1 + val2;
            case TIMES:
                return val1 * val2;
            case EQUALS:
                return val1 == val2 ? 1 : 0;
            case LESS:
                return val1 < val2 ? 1 : 0;
                default: return -1;
        }
    }
}
