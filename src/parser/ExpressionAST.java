package parser;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.tokens.*;

import java.util.ArrayList;
import java.util.List;

public abstract class ExpressionAST extends AST{

    /**
     * Parse un objet Expression à partir du premier caractère (que ce soit une '(' ou non)
     * @param token
     * @return
     * @throws Exception
     */
    public static ExpressionAST parse(Token token) throws Exception{
        if(token instanceof LParToken){
            Token token2 = SLexer.getToken();
            return parseComposite(token2);
        }
        else
            return parseSimple(token);
    }

    /**
     * Parse un objet Expression à partir du 1er caractère,en sachant qu'il ne s'agit pas d'une '('
     * @param token
     * @return
     */
    public static ExpressionAST parseSimple(Token token){
        if (token instanceof IdentifierToken){
            return new VarIdentifierAST(((IdentifierToken)token).getValue());
        }
        else if(token instanceof LiteralToken)
            return new LiteralAST(((LiteralToken)token).value());
        else
            throw new SyntaxException("Syntax error");
    }

    /**
     * Parse un objet Expression à partir du 2e caractère,en sachant que le premier était une '('
     * @param token2
     * @return
     * @throws Exception
     */
    public static ExpressionAST parseComposite(Token token2) throws Exception{
        //Si c'est un opérande => BinnaryExpression ou UnaryExpression
        if(token2 instanceof OpToken){
            ExpressionAST exp1 = ExpressionAST.parse(SLexer.getToken());
            Token suivant = SLexer.getToken();
            if(suivant instanceof RParToken)
                if(token2 == OpToken.MINUS)
                    return new UnaryExpressionAST(exp1);
                else throw new SyntaxException("Syntax error : at least 2 expression needed");
            else {
                ExpressionAST exp2 = ExpressionAST.parse(suivant);
                Token last = SLexer.getToken();
                if(last instanceof RParToken)
                    return new BinaryExpressionAST((OpToken)token2, exp1, exp2);
                else
                    throw new SyntaxException("Missing closing prenthesis (found : " + last + ")");
            }
        }
        else if(token2 instanceof IfToken){
            ExpressionAST exp1 = ExpressionAST.parse(SLexer.getToken());
            ExpressionAST exp2 = ExpressionAST.parse(SLexer.getToken());
            ExpressionAST exp3 = ExpressionAST.parse(SLexer.getToken());
            Token last = SLexer.getToken();
            if(last instanceof RParToken)
                return new ConditionalExpressionAST(exp1, exp2, exp3);
            else
                throw new SyntaxException("Missing closing prenthesis (found : " + last + ")");
        }
        else if(token2 instanceof IdentifierToken){
            FuncIdentifierAST funcId = new FuncIdentifierAST(token2.toString());
            List<ExpressionAST> expressions = new ArrayList<>();
            Token token3 = SLexer.getToken();
            while (!(token3 instanceof RParToken)){
                expressions.add(ExpressionAST.parse(token3));
                token3 = SLexer.getToken();
            }
            return new FuncCallAST(funcId, expressions);
        }
        else
            throw new SyntaxException("Unexpected expression (" + token2 +")");
    }

    public abstract int eval(State<Integer> state, State<FuncDefAST> funcs);

}
