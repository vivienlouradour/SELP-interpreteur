package parser;

import lexer.SLexer;
import lexer.Token;
import lexer.tokens.DefvarToken;
import lexer.tokens.IdentifierToken;
import lexer.tokens.LParToken;

import java.util.ArrayList;
import java.util.List;

public class BodyAST extends AST{
    private static List<VarDefAST> vDefs = new ArrayList<>();
    private ExpressionAST exp;

    public static BodyAST parse(Token token){
        Token token2 = SLexer.getToken();
        while (
            token instanceof LParToken &&
            token2 instanceof DefvarToken
              )
        {
            Token idToken = SLexer.getToken();
            if(!(idToken instanceof IdentifierToken))
                throw new SyntaxException("Identifier expected. Found : " + idToken);
            VarIdentifierAST varIdentifierAST = new VarIdentifierAST(idToken.toString());
            ExpressionAST expressionAST = ExpressionAST.parse(SLexer.getToken());
            vDefs.add(new VarDefAST(varIdentifierAST, expressionAST));

            token = SLexer.getToken();
            token2 = SLexer.getToken();
        }


    }

    @Override
    public String toString() {
        return null;
    }
}
