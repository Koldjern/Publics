package com.presentation.tools.csvwriter;

import java.text.DecimalFormat;
import java.util.List;
import com.model.entities.Invoice;
//karl
public class CSVWriterInvoices {
//private fields with one making a header for invoices 
private String Filename;
private String pathinvoices;
private List < Invoice > invoices;
private static final String HEADER[] = { "NR", "Dato Start", "Dato slut", "Plus", "Minus", "UltimoValue", "PrimoPrice",
        "Details" };

    public CSVWriterInvoices(String filename, String pathinvoices, List < Invoice > invoices) {
        this.Filename = filename;
        this.pathinvoices = pathinvoices;
        this.invoices = invoices;
    }
    // writes the csv and makes it and adds as many rows down as needed 
    public void WriteCSV(){
        int rows = invoices.size();
        int columns = 8;
        String [][] array = new String[rows][columns];
        DecimalFormat df = new DecimalFormat("#.####");
        for (int i = 0; i < rows; i++ ){
            array[i][0] = ""+invoices.get(i).getNumber();
            array[i][1] = invoices.get(i).getDatestart().toString();
            array[i][2] = invoices.get(i).getDateend().toString();
            array[i][3] = df.format(invoices.get(i).getPlus());
            array[i][4] = df.format(invoices.get(i).getMinus());
            array[i][5] = df.format(invoices.get(i).getUltimovalue());
            array[i][6] = df.format(invoices.get(i).getPrimoprice());
            array[i][7] = invoices.get(i).getDetails().toString();
            
        }
        CSVWriter writer = new CSVWriter(pathinvoices + Filename, HEADER, array);
        writer.write();
    }
    
    
    



    
    
}
