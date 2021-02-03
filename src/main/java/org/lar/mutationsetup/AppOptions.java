package org.lar.mutationsetup;

import org.lar.mutationsetup.utils.ConstantsUtil;

public class AppOptions {

    private AppOptions() {
        throw new IllegalStateException(ConstantsUtil.CLASS_NOT_INSTANTIABLE);
    }

    private static boolean memoryRuntimeEnabled;

    /**
     * Verifica se a opção de gerar dados de runtime foi marcada
     *
     * @return Boolean da opção do memoryRuntime
     */
    public static boolean isMemoryRuntimeEnabled() {
        return AppOptions.memoryRuntimeEnabled;
    }

    /**
     * Seta a opção de gerar dados de runtime
     *
     * @param memoryRuntimeEnabled True ou False para os dados de runtime
     */
    public static void setMemoryRuntimeEnabled(boolean memoryRuntimeEnabled) {
        AppOptions.memoryRuntimeEnabled = memoryRuntimeEnabled;
    }
}
