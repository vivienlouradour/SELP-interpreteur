package calc;

import eval.State;
import lexer.Lexer;
import lexer.SLexer;
import lexer.Token;
import lexer.tokens.EOFToken;
import parser.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Calc {
    public static void main(String[] args)  throws Exception{
        //pisteVerte(args);
        pisteBleue(args);
    }

    private static void pisteBleue(String[] args) throws Exception{
        String inputFile = null;
        if(args.length > 0){
            inputFile = args[0];
        }

        SLexer.init(inputFile);
        BodyAST.init();
        BodyAST arbre = BodyAST.parse(SLexer.getToken());
        System.out.println(arbre.eval());

        Token fin = SLexer.getToken();
        if(!(fin instanceof EOFToken))
            throw new SyntaxException("Un seul body attendu");
    }

    private static void pisteVerte(String[] args) throws Exception{
        String inputFile = null;
        if ( args.length>0 ) {
            inputFile = args[0];
        }

        //Pas de bloc try-catch pour permettre la gestion des exceptions dans les classes de Test.
//        try {
        SLexer.init(inputFile);
        ExpressionAST arbre = ExpressionAST.parse(SLexer.getToken());
        Token fin = SLexer.getToken();
        System.out.println(arbre.eval(new State<>()));

        //On vérifie qu'il n'y ai bien qu'une expression (à supprimer par la suite...)
        if(!(fin instanceof EOFToken)) {
            throw new SyntaxException("Une seule expression attendue");
        }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    private static void mainLexer(String[] args) throws IOException{
        List<Token> tokens;
        String inputFile = null;
        InputStream is = System.in;
        if ( args.length>0 ) {
            inputFile = args[0];
            is = new FileInputStream(inputFile);
        }

        try {
            Lexer lexer = new Lexer(is);
            tokens = lexer.lex();
            // output of the result
            for (Token token : tokens) {
                System.out.println(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//
//    }
}
