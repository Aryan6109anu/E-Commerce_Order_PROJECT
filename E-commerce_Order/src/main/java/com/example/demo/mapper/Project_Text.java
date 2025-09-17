package com.example.demo.mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Project_Text {
	
	public static void main(String[] args) throws Exception {
        File folder = new File("F:\\ALL PROJECT ARE STORED HERE\\E-commerce_Order"); // Apne project ka path daal
        FileWriter writer = new FileWriter("C:\\Users\\sonun\\OneDrive\\Desktop\\INTERVIEW K LIYE\\SPRING_SECURITY NOTES_2025\\E-commerce.txt"); // Output file

        addFilesFromFolder(folder, writer);

        writer.close();
        System.out.println("âœ… ProjectCode.txt created successfully!");
    }

    private static void addFilesFromFolder(File folder, FileWriter writer) throws IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                addFilesFromFolder(file, writer);
            } else if (file.getName().endsWith(".java")) {
                writer.write("\n==== " + file.getName() + " ====\n\n");

                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    writer.write(line + "\n");
                }
                br.close();
            }
        }
    }


}
