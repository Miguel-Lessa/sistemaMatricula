<<<<<<< HEAD
=======
package com.sistemaMatricula.sistemaMatricula.util;

import java.io.*;


//tem uns erros ainda, eu vou resolver, mas não hoje, I'm tired boss

public class Arquivo {
    // Método para criar arquivo
    public void criarArquivo(String filePath) {
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            throw new RuntimeException(e);
        }
    }

    // Método para escrever no arquivo
    public void escreverNoArquivo(String filePath, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para ler o arquivo
    public String lerArquivo(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
>>>>>>> e343fb331155923ea7c2de6c3b996c4131a5056e
