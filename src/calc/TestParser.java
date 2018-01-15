package calc;

import lexer.SLexer;
import lexer.Token;
import parser.AST;
import parser.ExpressionAST;

import java.util.List;

public class TestParser {
    public static void main(String[] args) {
        List<Token> tokens;
        String inputFile = null;
        if ( args.length>0 ) {
            inputFile = args[0];
        }
        try {
            SLexer.init(inputFile);
            AST arbre = ExpressionAST.parse(SLexer.getToken());
            System.out.println(arbre.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
