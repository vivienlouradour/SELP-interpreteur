package lexer.tokens;

import lexer.Token;

public enum OpToken implements Token {
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDE("/"),
    EQUALS("=="),
    LESS("<");

    private final String val;

    private OpToken(final String val){
        this.val = val;
    }

    @Override
    public String toString(){
        return this.val;
    }
}
