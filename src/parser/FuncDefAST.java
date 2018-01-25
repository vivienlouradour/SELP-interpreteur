package parser;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.tokens.RParToken;

import java.util.ArrayList;
import java.util.List;

public class FuncDefAST extends AST{

    private HeadAST head;

    private BodyAST body;
    public FuncDefAST(HeadAST head, BodyAST body) {
        this.head = head;
        this.body = body;
    }

    public static FuncDefAST parse(Token token)throws Exception{
        HeadAST head = HeadAST.parse(token);
        BodyAST body = BodyAST.parse(SLexer.getToken(), new ArrayList<>());
        Token last = SLexer.getToken();

        if(!(last instanceof RParToken))
            throw new SyntaxException("Missing closing prenthesis (found : " + last + ")");
        return new FuncDefAST(head, body);
    }

    //Définition d'une fonction
    public void eval(State<FuncDefAST> funcs){
        if(funcs.lookup(this.head.getFuncId().getValue()) != null)
            throw new SemanticException("La fonction " + this.head.getFuncId().getValue() + " a déjà été définie.");

        funcs.bind(this.head.getFuncId().getValue(), this);
    }

    //Appel d'une fonction
    public int eval(List<Integer> args, State<FuncDefAST> funcs){
        State<Integer> vars = bindArguments(this.head.getVarIds(), args);
        return this.body.eval(vars, funcs);
    }

    private State<Integer> bindArguments(List<VarIdentifierAST> formals, List<Integer> args){
        State<Integer> vars = new State<>();
        for (int i =0 ; i< formals.size(); i++) {
            vars.bind(formals.get(i).getValue(), args.get(i));
        }
        return vars;
    }

    public HeadAST getHead() {
        return head;
    }

    @Override
    public String toString() {
        return "(" + this.head + " " + this.body + ")";
    }
}
