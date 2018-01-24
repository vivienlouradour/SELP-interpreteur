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
        String inputFile = null;
        if(args.length > 0){
            inputFile = args[0];
        }

        SLexer.init(inputFile);

//        pisteVerte(args);
//        pisteBleue(args);
        pisteRouge(args);

        Token fin = SLexer.getToken();
        if(!(fin instanceof EOFToken))
            throw new SyntaxException("Un seul body attendu");
    }

    private static void pisteRouge(String[] args) throws Exception{
        ProgramAST.init();
        ProgramAST programAST = ProgramAST.parse(SLexer.getToken());
        System.out.println(programAST.eval());


    }

    private static void pisteBleue(String[] args) throws Exception{
        BodyAST arbre = BodyAST.parse(SLexer.getToken(), new ArrayList<>());
        //System.out.println(arbre.eval());


    }

    private static void pisteVerte(String[] args) throws Exception {
        ExpressionAST arbre = ExpressionAST.parse(SLexer.getToken());
        //System.out.println(arbre.eval(new State<>()));

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
