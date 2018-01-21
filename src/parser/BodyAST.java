package parser;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.tokens.DefvarToken;
import lexer.tokens.IdentifierToken;
import lexer.tokens.LParToken;
import lexer.tokens.RParToken;

import java.util.ArrayList;
import java.util.List;

public class BodyAST extends AST {
    public static List<VarDefAST> vDefs = new ArrayList<>();
    private List<VarDefAST> defs;
    private ExpressionAST exp;

    public static BodyAST parse(Token token) throws Exception{
        //Si c'est un '(' => VarDef ou Expression composite de fin du Body
        if(token instanceof LParToken){
            Token token2 = SLexer.getToken();
            //Si c'est un '=' => VarDef
            if(token2 instanceof DefvarToken){
                VarDefAST defAST = VarDefAST.parse(SLexer.getToken());
                vDefs.add(defAST);
                return parse(SLexer.getToken());
            }
            //Sinon c'est l'Expression composite de fin du body
            else {
                ExpressionAST expr = ExpressionAST.parseComposite(token2);
                return new BodyAST(vDefs, expr);
            }
        }
        //Sinon c'est une Expression simple de fin du Body
        else{
            return parseSimpleBody(token, vDefs);
        }

    }

    private static BodyAST parseSimpleBody(Token token, List<VarDefAST> vDefs){
        ExpressionAST expressionAST = ExpressionAST.parseSimple(token);
        return new BodyAST(vDefs, expressionAST);
    }


    public BodyAST(List<VarDefAST> defs, ExpressionAST expr){
        this.defs = defs;
        this.exp = expr;
    }


    public int eval() {
        State<Integer> vars = new State<>();
        this.defs.forEach(varDefAST -> varDefAST.eval(vars));

        return this.exp.eval(vars);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.defs.forEach(stringBuilder::append);
        stringBuilder.append(" ");
        stringBuilder.append(this.exp);
        return stringBuilder.toString();
    }
}