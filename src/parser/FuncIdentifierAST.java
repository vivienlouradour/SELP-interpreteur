package parser;

public class FuncIdentifierAST extends AST {
    private String value;

    public FuncIdentifierAST(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public String toString() {
        return  "fonction (" + this.getValue() + ")";
    }
}
