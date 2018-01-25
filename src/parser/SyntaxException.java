package parser;

public class SyntaxException extends RuntimeException {
    public SyntaxException(String message){
        super("Syntaxic excpetion : " +  message);
    }
}
