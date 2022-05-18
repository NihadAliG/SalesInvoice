
package salesinvoice.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import salesinvoice.model.InvoiceHeader;
import salesinvoice.model.InvoiceHeaderTableModel;
import salesinvoice.model.InvoiceLine;
import salesinvoice.model.InvoiceLineTableModel;
import salesinvoice.view.GUI;

public class Controller implements ActionListener, ListSelectionListener{
    
   private GUI gui;
    
    public Controller(GUI gui) {
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("action performed");
        String actionCommand = e.getActionCommand();
        System.out.println("Action: " + actionCommand);
        switch (actionCommand) {
            case "Load File":
                loadFile();
                break;
            case "Save File":
                saveFile();
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "Create New Item":
                createNewItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = gui.getInvoiceTable().getSelectedRow();
        System.out.println("You have selected row: " + selectedIndex);
        InvoiceHeader currentInvoice = gui.getInvoices().get(selectedIndex);
        gui.getInvoiceNumberLabelData().setText(""+currentInvoice.getInvoiceNum());
        gui.getInvoiceDateLabelData().setText(currentInvoice.getInvoiceDate());
        gui.getCustomerNameLabelData().setText(currentInvoice.getCustomerName());
        gui.getInvoiceTotalLabelData().setText(""+currentInvoice.getInvoiceTotal());
        InvoiceLineTableModel linesTableModel = new InvoiceLineTableModel(currentInvoice.getInvoiceLines());
        gui.getInvoiceItemTable().setModel(linesTableModel);
        linesTableModel.fireTableDataChanged();
    }
    
    private void loadFile() {
        System.out.println("loood file");
        JFileChooser fc = new JFileChooser();
        try {
            int result = fc.showOpenDialog(gui);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                System.out.println("Invoices have been read");
                // 1,22-11-2020,Ali
                // 2,13-10-2021,Saleh
                // 3,09-01-2019,Ibrahim
                ArrayList<InvoiceHeader> invoicesArray = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] headerParts = headerLine.split(",");
                    int invoiceNum = Integer.parseInt(headerParts[0]);
                    String invoiceDate = headerParts[1];
                    String customerName = headerParts[2];
                    
                    InvoiceHeader invoice = new InvoiceHeader(invoiceNum, invoiceDate, customerName);
                    invoicesArray.add(invoice);
                }
                System.out.println("Check point");
                result = fc.showOpenDialog(gui);
                if(result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    System.out.println("Lines have been read");
                    for (String lineLine : lineLines) {
                        String lineParts[] = lineLine.split(",");
                        int invoiceNum = Integer.parseInt(lineParts[0]);
                        String itemName = lineParts[1];
                        double itemPrice = Double.parseDouble(lineParts[2]);
                        int count = Integer.parseInt(lineParts[3]);
                        InvoiceHeader inv = null;
                        for (InvoiceHeader invoice : invoicesArray) {
                            if (invoice.getInvoiceNum() == invoiceNum) {
                                inv = invoice;
                                break;
                            }
                        }
                        
                        InvoiceLine line = new InvoiceLine(itemName, itemPrice, count, inv);
                        inv.getInvoiceLines().add(line);
                    }
                    System.out.println("Check point");
                }
                gui.setInvoices(invoicesArray);
                InvoiceHeaderTableModel invoicesTableModel = new InvoiceHeaderTableModel(invoicesArray);
                gui.setInvoicesTableModel(invoicesTableModel);
                gui.getInvoiceTable().setModel(invoicesTableModel);
                gui.getInvoicesTableModel().fireTableDataChanged();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void saveFile() {
    }

    private void createNewInvoice() {
    }

    private void deleteInvoice() {
    }

    private void createNewItem() {
    }

    private void deleteItem() {
    }
 
}
