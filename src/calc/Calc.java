package calc;

import lexer.SLexer;
import lexer.Token;
import lexer.tokens.EOFToken;
import parser.AST;
import parser.ExpressionAST;
import parser.SyntaxException;

import java.util.List;

public class Calc {
    public static void main(String[] args)  throws Exception{
        List<Token> tokens;
        String inputFile = null;
        if ( args.length>0 ) {
            inputFile = args[0];
        }

        //Pas de bloc try-catch pour permettre la gestion des exceptions dans les classes de Test.
//        try {
            SLexer.init(inputFile);
            AST arbre = ExpressionAST.parse(SLexer.getToken());
            Token fin = SLexer.getToken();
            System.out.println(arbre.eval());

            //On vérifie qu'il n'y ai bien qu'une expression (à supprimer par la suite...)
            if(!(fin instanceof EOFToken)) {
                throw new SyntaxException("Une seule expression attendue");
            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
