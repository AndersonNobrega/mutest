package org.lar.mutest;

import org.lar.mutest.language.LanguageWalker;
import org.lar.mutest.language.systemverilog.SystemVerilogWalker;

interface LanguageWalkerFactory {

    static LanguageWalker getWalker(String walkerName) {
        LanguageWalker walker = null;
        if ("SV".equals(walkerName)) {
            walker = new SystemVerilogWalker();
        }
        return walker;
    }
}
