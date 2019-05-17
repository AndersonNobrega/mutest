package org.lar.MutationSetup.Language.SystemVerilog;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.lar.LanguageUtils.SystemVerilog.SystemVerilogBaseListener;
import org.lar.LanguageUtils.SystemVerilog.SystemVerilogParser;
import org.lar.MutationSetup.MutationOperators.Operator;

public class SystemVerilogListener extends SystemVerilogBaseListener {

    private BufferedTokenStream tokenStream;
    private Operator mutationOperator;

    SystemVerilogListener(BufferedTokenStream tokenStream, Operator mutationOperator) {
        this.tokenStream = tokenStream;
        this.mutationOperator = mutationOperator;
    }

    @Override
    public void enterBinary_operator(SystemVerilogParser.Binary_operatorContext ctx) {
        mutationOperator.createMutants(tokenStream, ctx);
    }

    @Override
    public void enterAssignment_operator(SystemVerilogParser.Assignment_operatorContext ctx) {
        mutationOperator.createMutants(tokenStream, ctx);
    }

}
