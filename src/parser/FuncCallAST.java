package parser;

import eval.State;

import java.util.ArrayList;
import java.util.List;

public class FuncCallAST extends ExpressionAST{

    private FuncIdentifierAST funcId;
    private List<ExpressionAST> expressions;


    public FuncCallAST(FuncIdentifierAST funcId, List<ExpressionAST> expressions) {
        this.funcId = funcId;
        this.expressions = expressions;
    }


    @Override
    public int eval(State<Integer> vars, State<FuncDefAST> funcs) {
        FuncDefAST func = funcs.lookup(this.funcId.getValue());
        if(func == null)
            throw new SemanticException("La fonction (" + this.funcId.getValue() + ") n'a pas été définie.");
        if(func.getHead().getVarIds().size() != this.expressions.size())
            throw new SemanticException("La fonction (" + this.funcId.getValue() + ") n'a pas le bon nombre de paramètres");

        List<Integer> args = new ArrayList<>();
        for (ExpressionAST expr: expressions) {
            args.add(expr.eval(vars, funcs));
        }

        return func.eval(args, funcs);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("( ");
        stringBuilder.append(this.funcId);
        this.expressions.forEach(expression ->  stringBuilder.append(" " + expression));
        stringBuilder.append(" )");
        return stringBuilder.toString();
    }
}
