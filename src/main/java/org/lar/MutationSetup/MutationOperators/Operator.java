package org.lar.MutationSetup.MutationOperators;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStreamRewriter;

public interface Operator {

    void createMutants(BufferedTokenStream tokenStream, TokenStreamRewriter rewriter, ParserRuleContext ctx);
}
