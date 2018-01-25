package calc;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import lexer.*;
import lexer.Token;

public class TestLexer {

	/**
	 * @param args - arg[0] is the filename of the file to interpret
     *     (if it exists, otherwise the standard input stream is used).
	 */
	public static void main(String[] args) throws Exception {
		List<Token> tokens;
		String inputFile;
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
}
