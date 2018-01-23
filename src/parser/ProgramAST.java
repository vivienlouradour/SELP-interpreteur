package parser;

import lexer.SLexer;
import lexer.Token;
import lexer.tokens.DefunToken;
import lexer.tokens.LParToken;

import java.util.ArrayList;
import java.util.List;

public class ProgramAST extends AST {
    private static List<FuncDefAST> staticFuncDefs = new ArrayList<>();

    private List<FuncDefAST> funcDefs;
    private BodyAST body;


    public static ProgramAST parse(Token token)throws Exception{
        //Si c'est un '(' -> funcdef ou BodyComposite
        if(token instanceof LParToken){
            Token token2 = SLexer.getToken();
            //Si c'est un 'defun' -> funcDef
            if(token2 instanceof DefunToken){
                staticFuncDefs.add(FuncDefAST.parse(SLexer.getToken()));
                return parse(SLexer.getToken());
            }
            //Sinon c'est un BodyComposite
            else{
                BodyAST bodyAST = BodyAST.parseComposite(token2, new ArrayList<>());
                return new ProgramAST(staticFuncDefs, bodyAST);
            }
        }
        //Sinon : BodySimple
        else{
            BodyAST body = BodyAST.parseSimple(token, new ArrayList<>());
            return new ProgramAST(new ArrayList<>(), body);
        }
    }

    public ProgramAST(List<FuncDefAST> funcDefs, BodyAST body){
        this.body = body;
        this.funcDefs = funcDefs;
    }

    public int eval(){
        //TODO eval defunc
        return this.body.eval();
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
