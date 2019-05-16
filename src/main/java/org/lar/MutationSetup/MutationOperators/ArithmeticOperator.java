package org.lar.MutationSetup.MutationOperators;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.lar.MutationSetup.MutationUtils.TokenReplacer;

import java.util.ArrayList;

public class ArithmeticOperator implements Operator {

    private ArrayList<String> arithmeticOperators = new ArrayList<String>() {
        {
            add("+");
            add("-");
            add("/");
            add("*");
            add("%");
        }
    };

    @Override
    public void createMutants(BufferedTokenStream tokenStream, ParserRuleContext ctx) {
        TokenReplacer tokenReplacer = new TokenReplacer();
        tokenReplacer.replaceTokens(tokenStream, ctx, this.arithmeticOperators, "AOR");
    }
}
