package org.lar.mutest.utils.performance;

import org.lar.mutest.utils.files.FileCreator;

public class MemoryRuntime {

    /**
     * Calcula memória utilizada pelo extrator
     */
    public void calculateAll() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        long memory = runtime.totalMemory() - runtime.freeMemory();
        FileCreator.appendToMemoryRuntimeFile("Memoria utilizada em bytes: " + memory + "\n");
        FileCreator.appendToMemoryRuntimeFile("Memoria utilizada em megabytes: " + bytesToMegabytes(memory) + "\n");
        FileCreator.appendToMemoryRuntimeFile("Memoria utilizada em Kbytes: " + bytesToKbytes(memory) + "\n");

        memory = runtime.maxMemory();
        FileCreator.appendToMemoryRuntimeFile("Máximo de memória utilizada em Kbytes: " + bytesToKbytes(memory) + "\n");

        long processor = runtime.availableProcessors();
        FileCreator.appendToMemoryRuntimeFile("Número de processadores ativos: " + processor + "\n");
    }

    /**
     * Salva informações de memória utilizada e tempo de execução para arquivo txt
     */
    public void saveInfo(long start, String savePath) {
        MemoryRuntime memoryRuntime = new MemoryRuntime();
        long elapsedTimeMillis;
        long elapsedTimeSec;

        memoryRuntime.calculateAll();
        elapsedTimeMillis = System.currentTimeMillis() - start;
        elapsedTimeSec = elapsedTimeMillis / 1000;

        FileCreator.appendToMemoryRuntimeFile("Tempo em Milisegundos: " + elapsedTimeMillis + "\nTempo em Segundos: " + elapsedTimeSec);
        FileCreator.saveFile("Performance_Data", savePath, FileCreator.getMemoryRuntimeFile().toString(), "txt");
    }

    /**
     * Converte bytes para megabytes
     *
     * @param bytes valor em bytes
     * @return valor convertido em megabytes
     */
    private long bytesToMegabytes(long bytes) {
        final long MEGABYTE = 1024L * 1024L;
        return bytes / MEGABYTE;
    }

    /**
     * Converte bytes para kilobytes
     *
     * @param bytes valor em bytes
     * @return valor convertido em kilobytes
     */
    private long bytesToKbytes(long bytes) {
        final long KBYTE = 1024L;
        return bytes / KBYTE;
    }

}
