package org.lar.mutationsetup.operators;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.lar.mutationsetup.utils.TokenReplacer;

import java.util.Arrays;
import java.util.List;

public class ShiftOperator implements Operator {

    private static final List<String> SHIFT_OPERATORS = Arrays.asList(
            ">>", "<<", ">>>", "<<<"
    );

    @Override
    public void createMutants(BufferedTokenStream tokenStream, ParserRuleContext ctx) {
        TokenReplacer tokenReplacer = new TokenReplacer();
        tokenReplacer.replaceTokens(tokenStream, ctx, SHIFT_OPERATORS, "SOR");
    }
}
