package org.lar.MutationSetup;

import org.lar.MutationSetup.Language.LanguageWalker;
import org.lar.MutationSetup.Language.SystemVerilog.SystemVerilogWalker;

abstract class LanguageWalkerFactory {
    static LanguageWalker getWalker(String walkerName) {
        LanguageWalker walker = null;
        switch (walkerName) {
            case "SV":
                walker = new SystemVerilogWalker();
                break;
        }
        return walker;
    }
}
