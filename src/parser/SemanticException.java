package parser;

public class SemanticException extends RuntimeException {
    public SemanticException(String message){
        super("Sementic exception : " + message);
    }
}
