package parser;

public class VarDefAST extends AST {

    private VarIdentifierAST varId;
    private ExpressionAST expr;

    public VarDefAST(VarIdentifierAST varId, ExpressionAST expression){
        this.varId = varId;
        this.expr = expression;
    }


    @Override
    public String toString() {
        return "( = " + this.varId + " " + this.expr + " )";
    }
}
