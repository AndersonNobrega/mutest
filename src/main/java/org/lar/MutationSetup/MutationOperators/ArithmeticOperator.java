package org.lar.MutationSetup.MutationOperators;

import org.antlr.v4.runtime.*;
import org.lar.FileUtils.FileBrowser;
import org.lar.LanguageUtils.SystemVerilog.SystemVerilogLexer;
import org.lar.MutationSetup.MutationUtils.StringReplacer;

import java.util.ArrayList;

public class ArithmeticOperator implements Operator {

    private ArrayList<String> arithmeticOperators = new ArrayList<String>() {
        {
            add("+");
            add("-");
            add("/");
            add("*");
        }
    };
    private StringReplacer stringReplacer = new StringReplacer();

    @Override
    public void createMutants(BufferedTokenStream tokenStream, TokenStreamRewriter rewriter, ParserRuleContext ctx) {
        ArrayList<Integer> index = this.stringReplacer.findCharIndex(tokenStream, ctx, this.arithmeticOperators);
        StringBuilder fileTemp;
        TokenStreamRewriter temp;

        for(int tokenIndex :  index) {
            for(String arithmeticOperator : arithmeticOperators) {
                temp = new TokenStreamRewriter(tokenStream);
                if(!tokenStream.get(tokenIndex).getText().equals(arithmeticOperator)) {
                    temp.replace(tokenIndex, arithmeticOperator);
                    fileTemp = new StringBuilder(temp.getText());
                    FileBrowser.appendToMutations("AOR", fileTemp.toString());
                }
            }
        }
    }
}
