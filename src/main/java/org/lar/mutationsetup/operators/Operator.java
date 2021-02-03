package org.lar.mutationsetup.operators;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;

public interface Operator {

    void createMutants(BufferedTokenStream tokenStream, ParserRuleContext ctx);
}
