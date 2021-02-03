package org.lar.mutest;

import org.lar.mutest.operators.*;

interface OperatorFactory {

    static Operator getOperator(String operatorName) {
        Operator mutationOperator = null;
        switch (operatorName) {
            case "AOR":
                mutationOperator = new ArithmeticOperator();
                break;
            case "ROR":
                mutationOperator = new RelationalOperator();
                break;
            case "COR":
                mutationOperator = new ConditionalOperator();
                break;
            case "SOR":
                mutationOperator = new ShiftOperator();
                break;
            case "ASR":
                mutationOperator = new AssignmentOperator();
                break;
            default:
                break;
        }
        return mutationOperator;
    }
}
