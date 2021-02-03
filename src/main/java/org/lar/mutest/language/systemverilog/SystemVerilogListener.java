package org.lar.mutest.language.systemverilog;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.lar.mutest.antlr.systemverilog.SystemVerilogBaseListener;
import org.lar.mutest.antlr.systemverilog.SystemVerilogParser;
import org.lar.mutest.operators.Operator;

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

    @Override
    public void enterUnary_assign_operator(SystemVerilogParser.Unary_assign_operatorContext ctx) {
        mutationOperator.createMutants(tokenStream, ctx);
    }

}
