package lexer.tokens;

import lexer.Token;

public class Defun implements Token {
    @Override
    public String toString() {
        return "defun";
    }
}
