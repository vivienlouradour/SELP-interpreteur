package parser;

import lexer.Token;

import java.util.ArrayList;
import java.util.List;

public class BodyAST extends AST{
    private List<VarDefAST> vDefs = new ArrayList<>();
    private ExpressionAST exp;

    public static BodyAST parse(Token token){
        
    }

    @Override
    public String toString() {
        return null;
    }
}
