package parser;

public class VarIdentifierAST extends ExpressionAST {
    private String value;
    public VarIdentifierAST(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Variable(" + this.value + ")";
    }
}
