package parser;

import lexer.Token;
import lexer.tokens.LParToken;

import java.util.ArrayList;
import java.util.List;

public class ProgramAST extends AST {
    private static List<FuncDefAST> staticFuncDefs;

    private List<FuncDefAST> funcDefs;
    private BodyAST body;

    public static void init(){
        staticFuncDefs = new ArrayList<>();
    }

    public static ProgramAST parse(Token token)throws Exception{
        //Si c'est un '(' -> funcdef ou BodyComposite
        if(token instanceof LParToken){

        }
        //Sinon : BodySimple
        else{
            BodyAST body = BodyAST.parseSimple(token, new ArrayList<>());
            return new ProgramAST(new ArrayList<>(), body);
        }
        return null;
    }

    public ProgramAST(List<FuncDefAST> funcDefs, BodyAST body){
        this.body = body;
        this.funcDefs = funcDefs;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.funcDefs.forEach(stringBuilder::append);
        stringBuilder.append(" ");
        stringBuilder.append(this.body);
        return stringBuilder.toString();
    }
}
