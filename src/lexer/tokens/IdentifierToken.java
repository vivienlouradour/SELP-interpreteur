package lexer.tokens;

import lexer.Token;

/**
 * Représente soit un FUNC_ID soit un VAR_ID
 */
public class IdentifierToken implements Token {
    private String value;
    public IdentifierToken(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String getValue(){
        return this.value;
    }
}
