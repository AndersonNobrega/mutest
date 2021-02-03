package org.lar.mutationsetup.language.systemverilog;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.lar.languageutils.systemverilog.SystemVerilogBaseListener;
import org.lar.languageutils.systemverilog.SystemVerilogParser;
import org.lar.mutationsetup.operators.Operator;

public class SystemVerilogListener extends SystemVerilogBaseListener {

    private final BufferedTokenStream tokenStream;

    private final Operator mutationOperator;

    SystemVerilogListener(BufferedTokenStream tokenStream, Operator mutationOperator) {
        this.tokenStream = tokenStream;
        this.mutationOperator = mutationOperator;
    }

    @Override
    public void enterBinary_operator(SystemVerilogParser.Binary_operatorContext ctx) {
        mutationOperator.createMutants(tokenStream, ctx);
    }

    @Override
    public void enterAssign_statement(SystemVerilogParser.Assign_statementContext ctx) {
        mutationOperator.createMutants(tokenStream, ctx);
    }

}
