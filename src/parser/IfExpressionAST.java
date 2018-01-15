package parser;

public class IfExpressionAST extends ExpressionAST {
    private ExpressionAST exp1;
    private ExpressionAST exp2;
    private ExpressionAST exp3;

    public IfExpressionAST(ExpressionAST exp1, ExpressionAST exp2, ExpressionAST exp3){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public String toString() {
        return "IfExpression(" + exp1 + ", " + exp2 + ", " + exp3 + ")";
    }
}
