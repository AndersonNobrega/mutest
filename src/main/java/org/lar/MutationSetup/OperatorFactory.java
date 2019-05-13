package org.lar.MutationSetup;

import org.lar.MutationSetup.MutationOperators.ArithmeticOperator;
import org.lar.MutationSetup.MutationOperators.Operator;

abstract class OperatorFactory {
    static Operator getOperator(String operatorName) {
        Operator mutationOperator = null;
        switch (operatorName) {
            case "AOR":
                mutationOperator = new ArithmeticOperator();
                break;
        }
        return mutationOperator;
    }
}
