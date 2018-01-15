package parser;


public class LiteralAST extends ExpressionAST{
    private int value;
    public LiteralAST(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Litteral(" + String.valueOf(this.value) + ")";
    }
}
