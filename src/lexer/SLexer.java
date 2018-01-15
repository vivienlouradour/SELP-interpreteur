package lexer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SLexer {
    private static Lexer lexer;

    public static void init(String inputFile) throws IOException {
            InputStream is = new FileInputStream(inputFile);
            lexer = new Lexer(is);
    }

    public static Token getToken(){
        try {
            return lexer.getToken();
        }
        catch (Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }
    }
}
