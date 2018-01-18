package lexer;
import lexer.tokens.*;

import java.util.*;
import java.io.*;

public class Lexer {
	private InputStream in;
	private int i; // current ASCII character (coded as an integer)
	
	public Lexer(InputStream in) throws IOException {
		this.in = in;
		i = in.read(); // initialize current character
	}
	
	public List<Token> lex() throws UnexpectedCharacter, IOException {
		// return the list of tokens recorded in the file
		List<Token> tokens = new ArrayList<Token>();
		
		try {
			Token token = getToken();
	
			while (! (token instanceof EOFToken)) {
				tokens.add(token);
				token = getToken();
			}
			tokens.add(token); // this is the EOFToken token
		} catch (IOException e){
			in.close(); // close the reader
			throw e; // pass the exception up the stack
		}
		return tokens;
	}

	public Token getToken() throws UnexpectedCharacter, IOException {
		StringBuilder buffer;
		String bufferStr;
		switch (i){
		    case -1 :
			    in.close();
			    return new EOFToken();
			case 13:
			case 10:
			case 32:
			case '\t' :
				i= in.read();
				return getToken();
			case '0' :
				i = in.read();
				return new LiteralToken(0);
			case '1' :
			case '2' :
			case '3' :
			case '4' :
			case '5' :
			case '6' :
			case '7' :
			case '8' :
			case '9' :
				buffer = new StringBuilder();
				do{
					buffer.append(String.valueOf(Character.getNumericValue(i)));
					i = in.read();
				}while (Character.isDigit(i));
				return new LiteralToken(Integer.parseInt(buffer.toString()));
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'g':
			case 'h':
			case 'i':
			case 'j':
			case 'k':
			case 'l':
			case 'm':
			case 'n':
			case 'o':
			case 'p':
			case 'q':
			case 'r':
			case 's':
			case 't':
			case 'u':
			case 'v':
			case 'w':
			case 'x':
			case 'y':
			case 'z':
				buffer = new StringBuilder();
				Token subToken = null;
				while (Character.isAlphabetic(i)){
					buffer.append((char) i);
					i = in.read();
				}
				while (Character.isDigit(i)){
					buffer.append(String.valueOf(Character.getNumericValue(i)));
					i = in.read();
				}
				bufferStr = buffer.toString();
				switch (bufferStr){
					case "if":
						return new IfToken();
					case "defun":
						return new DefunToken();
					default:
						return new IdentifierToken(bufferStr);
				}
			case '+':
				i = in.read();
				return OpToken.PLUS;
			case '-':
				i = in.read();
				return OpToken.MINUS;
			case '*':
				i = in.read();
				return OpToken.TIMES;
			case '/':
				i = in.read();
				return OpToken.DIVIDE;
			case '=':
				i = in.read();
				if(i == '='){
					i = in.read();
					return OpToken.EQUALS;
				}
				return new DefvarToken();
			case '<':
				i = in.read();
				return OpToken.LESS;
			case '(':
				i = in.read();
				return new LParToken();
			case ')':
				i = in.read();
				return new RParToken();
		    default :
			    throw new UnexpectedCharacter(i);
		}
	}
}