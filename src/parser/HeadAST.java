package parser;

import lexer.Token;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class HeadAST extends AST{
    private FuncIdentifierAST funcId;
    private List<VarIdentifierAST> varIds;

    public static HeadAST parse(Token token){
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        return null;
    }
}
