package org.lar.mutationsetup.utils.files;

import org.lar.mutationsetup.utils.ConstantsUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class FileCreator {

    private static final Logger LOGGER = Logger.getLogger( FileCreator.class.getName() );

    private FileCreator() {
        throw new IllegalStateException(ConstantsUtil.CLASS_NOT_INSTANTIABLE);
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
            System.out.println("Não foi possivel abrir arquivo");
        } catch (IOException e) {
            System.out.println("Problemas com leitura do arquivo");
        }
    }
}
