package org.lar.FileUtils;

import java.io.*;

public class FileCreator {
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
            directory.mkdirs();
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
