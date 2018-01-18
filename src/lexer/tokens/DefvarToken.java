package lexer.tokens;

import lexer.Token;

public class Defvar implements Token {
    @Override
    public String toString() {
        return "=";
    }
}
