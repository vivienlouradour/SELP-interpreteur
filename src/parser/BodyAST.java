package parser;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.tokens.DefvarToken;
import lexer.tokens.LParToken;

import java.util.List;

public class BodyAST extends AST {
    private List<VarDefAST> defs;
    private ExpressionAST exp;


    /**
     * Parse un objet Body à partir du premier caractère (que ce soit une '(' ou non)
     * @param token
     * @param vDefs
     * @return
     * @throws Exception
     */
    public static BodyAST parse(Token token, List<VarDefAST> vDefs) throws Exception{
        //Si c'est un '(' => VarDef ou Expression composite de fin du Body
        if(token instanceof LParToken){
            return parseComposite(SLexer.getToken(), vDefs);
        }
        //Sinon c'est une Expression simple de fin du Body
        else{
            return parseSimple(token, vDefs);
        }
    }

    /**
     * Parse un objet Body à partir du 2e caractère,en sachant que le premier était une '('
     * @param token
     * @param vDefs
     * @return
     * @throws Exception
     */
    public static BodyAST parseComposite(Token token, List<VarDefAST> vDefs)throws Exception{
        //Si c'est un '=' => VarDef
        if(token instanceof DefvarToken){
            VarDefAST defAST = VarDefAST.parse(SLexer.getToken());
            vDefs.add(defAST);
            return parse(SLexer.getToken(), vDefs);
        }
        //Sinon c'est l'Expression composite de fin du body
        else {
            ExpressionAST expr = ExpressionAST.parseComposite(token);
            return new BodyAST(vDefs, expr);
        }
    }

    /**
     * Parse un objet Body à partir du 1er caractère,en sachant qu'il ne s'agit pas d'une '('
     * @param token
     * @param vDefs
     * @return
     * @throws Exception
     */
    public static BodyAST parseSimple(Token token, List<VarDefAST> vDefs)throws Exception{
        ExpressionAST exp = ExpressionAST.parseSimple(token);
        return new BodyAST(vDefs, exp);
    }


    public BodyAST(List<VarDefAST> defs, ExpressionAST expr){
        this.defs = defs;
        this.exp = expr;
    }


    public int eval(State<Integer> args,  State<FuncDefAST> funcs) {
        this.defs.forEach(varDefAST -> varDefAST.eval(args, funcs));

        return this.exp.eval(args, funcs);
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