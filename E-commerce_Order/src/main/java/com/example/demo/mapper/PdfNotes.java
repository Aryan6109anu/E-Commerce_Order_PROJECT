package com.example.demo.mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfNotes {
    public static void main(String[] args) throws Exception {
        File folder = new File("F:\\ALL PROJECT ARE STORED HERE\\E-commerce_Order"); // Apne project ka path daal
        String outputPath = "C:\\Users\\sonun\\OneDrive\\Desktop\\E-commerce_Bussiness\\e-Commerce.pdf"; // Output file

        // iText document create
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputPath));
        document.open();

        // Add project files
        addFilesFromFolder(folder, document);

        // Close document
        document.close();
        System.out.println("✅ ProjectCode.pdf created successfully at: " + outputPath);
    }

    private static void addFilesFromFolder(File folder, Document document) throws IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                addFilesFromFolder(file, document);
            } else if (file.getName().endsWith(".java")) {
                try {
                    // File name as heading
                    document.add(new Paragraph("\n==== " + file.getName() + " ====\n"));

                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null) {
                        document.add(new Paragraph(line));
                    }
                    br.close();

                    document.add(new Paragraph("\n\n")); // spacing between files
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
