package com.presentation.tools;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import com.model.entities.Invoice;
import com.presentation.tools.alert.Alerter;
import com.presentation.tools.csvwriter.CSVWriterInvoices;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//anders
//helps finding folders and pictures
public class FileMethods {
    public static byte[] findImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Vælg et billede");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Billeder png, jpg, jpeg, gif", "*.png",
                "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        try {
            return Files.readAllBytes(file.toPath());
        } catch (Exception e) {
            Alerter.information("Billede fejl", "Filen er ikke et tilgængeligt billede");
            return null;
        }
    }
    public static void makeCSV(String fileName, List<Invoice> invoices, Stage stage) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Vælg folder");
        File file = dirChooser.showDialog(stage);
        CSVWriterInvoices writer = new CSVWriterInvoices(fileName, file.getAbsolutePath() + "\\", invoices);
        writer.WriteCSV();
    }
}
