package com.presentation;

import com.model.entities.Invoice;
import com.presentation.tools.csvwriter.CSVWriterInvoices;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

//emil karl 
public class CSVWriterTest extends TestCase {

    public CSVWriterTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */

    public static Test suite()
    {
        return new TestSuite( CSVWriterTest.class );
    }

    //CSV Writer Test for invoices der tjekker om filen bliver skrevet korrekt
    public void testWrite() {
        String filename = "test_invoices";
        String path = System.getProperty("user.home") + "\\Downloads\\";
        List<Invoice> invoices = Arrays.asList(
            //laver 2 nye fakturaer der skal skrives til filen
                new Invoice(1, Date.valueOf(LocalDate.of(2023, 1, 1)),
                 Date.valueOf(LocalDate.of(2023, 1, 31)), 100, 50,
                        500, 450, "Some details"),
                new Invoice(2, Date.valueOf(LocalDate.of(2023, 2, 1)), 
                Date.valueOf(LocalDate.of(2023, 2, 28)), 200, 75,
                        625, 500, "More details"));
        //skriver filen
        CSVWriterInvoices writeCSV = new CSVWriterInvoices(filename, path, invoices);
        writeCSV.WriteCSV();
        //tjekker om filen er blevet skrevet og den passer med det forventede navn
        File file = new File(path, filename + ".csv");
        assertTrue(file.exists());
        //bufferedreader til at læse filen og tjekke om den er skrevet korrekt
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // assert statements til at tjekke om filen er skrevet korrekt
            assertEquals("NR;Dato Start;Dato slut;Plus;Minus;UltimoValue;PrimoPrice;Details", reader.readLine());
            assertEquals(reader.readLine(), "1;2023-01-01;2023-01-31;100;50;500;450;Some details");
            assertEquals(reader.readLine(), "2;2023-02-01;2023-02-28;200;75;625;500;More details");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Files.deleteIfExists(Paths.get(path, filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
