package parser;

import lexer.SLexer;
import lexer.Token;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class FuncDefAST extends AST{

    private HeadAST head;
    private BodyAST body;

    public FuncDefAST(HeadAST head, BodyAST body) {
        this.head = head;
        this.body = body;
    }

    public static FuncDefAST parse(Token token)throws Exception{
        HeadAST head = HeadAST.parse(SLexer.getToken());
        BodyAST body = BodyAST.parse(SLexer.getToken(), new ArrayList<>());
        return new FuncDefAST(head, body);
    }

    @Override
    public String toString() {
        return null;
    }
}
