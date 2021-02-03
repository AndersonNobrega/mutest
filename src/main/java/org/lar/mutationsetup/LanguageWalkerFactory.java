package org.lar.mutationsetup;

import org.lar.mutationsetup.language.LanguageWalker;
import org.lar.mutationsetup.language.systemverilog.SystemVerilogWalker;

interface LanguageWalkerFactory {

    static LanguageWalker getWalker(String walkerName) {
        LanguageWalker walker = null;
        if ("SV".equals(walkerName)) {
            walker = new SystemVerilogWalker();
        }
        return walker;
    }
}
