package org.lar.mutest.utils.files;

import org.lar.mutest.utils.ConstantsUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class FileCreator {

    private static final Logger LOGGER = Logger.getLogger( FileCreator.class.getName() );

    private static final StringBuilder memoryRuntimeFile = new StringBuilder();

    private FileCreator() {
        throw new IllegalStateException(ConstantsUtil.CLASS_NOT_INSTANTIABLE);
    }

    /**
     * Retorna as metricas do projeto
     *
     * @return String das metricas do projeto
     */
    public static StringBuilder getMemoryRuntimeFile() {
        return FileCreator.memoryRuntimeFile;
    }

    /**
     * Adiciona as metricas do projeto ao arquivo txt
     *
     * @param content Conteudo para adicionar
     */
    public static void appendToMemoryRuntimeFile(String content) {
        FileCreator.memoryRuntimeFile.append(content);
    }

    /**
     * Salva um arquivo
     *
     * @param fileName Nome do arquivo
     * @param path     Caminho do arquivo
     * @param content     String do arquivo para processar
     * @param format   Formato do arquivo
     */
    public static void saveFile(String fileName, String path, String content, String format) {
        FileWriter fileWriter;
        BufferedWriter writer;
        File directory;

        if (path.charAt(path.length() - 1) == '/') {
            path = path.substring(0, path.length() - 1);
        }

        directory = new File(path + "/");
        if (!directory.exists()){
            boolean dirCreated = directory.mkdirs();
            if (!dirCreated) {
                LOGGER.info(ConstantsUtil.DIR_NOT_CREATED);
            }
        }

        try {
            fileWriter = new FileWriter(path + "/" + fileName + "." + format);
            writer = new BufferedWriter(fileWriter);

            writer.append(content);

            writer.flush();
            writer.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            LOGGER.warning(ConstantsUtil.FILE_NOT_OPENED);
        } catch (IOException e) {
            LOGGER.warning(ConstantsUtil.FILE_READ_PROBLEM);
        }
    }
}
