package parser;

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
}
