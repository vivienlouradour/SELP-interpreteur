package parser;

import java.util.ArrayList;

public class BodyAST extends AST{
    private List<VarDef> vDefs = new ArrayList<>();
    private ExpressionAST exp;
}
