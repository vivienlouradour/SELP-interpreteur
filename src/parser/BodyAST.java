package parser;

import lexer.Token;

import java.util.ArrayList;

public class BodyAST extends AST{
    private List<VarDef> vDefs = new ArrayList<>();
    private ExpressionAST exp;

    public static BodyAST parse(Token token){
        
    }
}
