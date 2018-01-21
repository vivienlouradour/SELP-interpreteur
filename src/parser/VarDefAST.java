package parser;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.tokens.IdentifierToken;
import lexer.tokens.RParToken;

public class VarDefAST extends AST {

    private VarIdentifierAST varId;
    private ExpressionAST expr;

    /**
     *
     * @param token
     * @throws SyntaxException
     * @return
     */
    public static VarDefAST parse(Token token) throws Exception{
        if(!(token instanceof IdentifierToken))
            throw new SyntaxException("var identifier expected, '" + token + "' found.");

        VarIdentifierAST varIdentifierAST = new VarIdentifierAST(((IdentifierToken)token).toString());
        ExpressionAST expressionAST = ExpressionAST.parse(SLexer.getToken());

        Token token2 = SLexer.getToken();
        if(!(token2 instanceof RParToken))
            throw new SyntaxException("'(' expected but '" + token2 + "' found.");
        return new VarDefAST(varIdentifierAST, expressionAST);
    }

    private VarDefAST(VarIdentifierAST varId, ExpressionAST expr){
        this.varId = varId;
        this.expr = expr;
    }

    public void eval(State<Integer> state){
        String key = this.varId.getValue();
        Integer value = state.lookup(key);
        //On ne peut pas redéfinir une variable
        if(value != null)
            throw new SemanticException(this.varId + " déjà déclarée dans ce Body.");

        state.bind(key, this.expr.eval(state));
    }

    @Override
    public String toString() {
        return "( = " + this.varId + " " + this.expr + " )";
    }
}
