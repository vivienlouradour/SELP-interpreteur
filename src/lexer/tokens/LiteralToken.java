package lexer.tokens;

import lexer.Token;

public class LiteralToken implements Token {
    private int val;
    public LiteralToken(int val){
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }
    public int value(){
        return this.val;
    }
}
