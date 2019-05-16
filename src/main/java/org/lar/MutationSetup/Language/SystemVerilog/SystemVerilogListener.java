package org.lar.MutationSetup.Language.SystemVerilog;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.lar.LanguageUtils.SystemVerilog.SystemVerilogBaseListener;
import org.lar.LanguageUtils.SystemVerilog.SystemVerilogParser;
import org.lar.MutationSetup.MutationOperators.Operator;

public class SystemVerilogListener extends SystemVerilogBaseListener {

    private BufferedTokenStream tokenStream;
    private Operator mutationOperator;
    private TokenStreamRewriter rewriter;

    SystemVerilogListener(BufferedTokenStream tokenStream, Operator mutationOperator) {
        this.tokenStream = tokenStream;
        this.mutationOperator = mutationOperator;
        this.rewriter = new TokenStreamRewriter(tokenStream);
    }

    @Override
    public void enterSource_text(SystemVerilogParser.Source_textContext ctx) {
        mutationOperator.createMutants(tokenStream, rewriter, ctx);
    }

}