package calc;

import lexer.Lexer;
import lexer.SLexer;
import lexer.Token;
import lexer.tokens.EOFToken;
import parser.BodyAST;
import parser.ExpressionAST;
import parser.ProgramAST;
import parser.SyntaxException;

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

        ProgramAST.init();
        ProgramAST programAST = ProgramAST.parse(SLexer.getToken());
        System.out.println(programAST.eval());

        Token fin = SLexer.getToken();
        if(!(fin instanceof EOFToken))
            throw new SyntaxException("Un seul body attendu");
    }
}
