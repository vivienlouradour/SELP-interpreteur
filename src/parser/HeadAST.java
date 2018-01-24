package parser;

import lexer.SLexer;
import lexer.Token;
import lexer.tokens.IdentifierToken;
import lexer.tokens.LParToken;
import lexer.tokens.RParToken;

import java.util.ArrayList;
import java.util.List;

public class HeadAST extends AST{
    public HeadAST(FuncIdentifierAST funcId, List<VarIdentifierAST> varIds) {
        this.funcId = funcId;
        this.varIds = varIds;
    }

    private FuncIdentifierAST funcId;
    private List<VarIdentifierAST> varIds;

    public static HeadAST parse(Token token) throws Exception{
        if(!(token instanceof LParToken))
            throw new SyntaxException("Missing opening prenthesis (found : " + token + ")");

        token = SLexer.getToken();
        if(!(token instanceof IdentifierToken))
            throw new SyntaxException("Func Identifier expected (found : " +token + ")");

        FuncIdentifierAST funcId = new FuncIdentifierAST(token.toString());

        List<VarIdentifierAST> varIds = new ArrayList<>();
        token = SLexer.getToken();
        while (token instanceof IdentifierToken){
            varIds.add(new VarIdentifierAST(token.toString()));
            token = SLexer.getToken();
        }
        if(!(token instanceof RParToken))
            throw new SyntaxException("Missing closing prenthesis (found : " + token + ")");

        return new HeadAST(funcId, varIds);
    }


    public FuncIdentifierAST getFuncId() {
        return funcId;
    }

    public List<VarIdentifierAST> getVarIds(){
        return this.varIds;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(" + this.funcId);
        stringBuilder.append(" ");
        this.varIds.forEach(stringBuilder::append);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
