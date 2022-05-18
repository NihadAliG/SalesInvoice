/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesinvoice.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aya
 */
public class InvoiceLineTableModel extends AbstractTableModel{
    
    private ArrayList<InvoiceLine> lines;
    private String[] columns = {"No.", "Item Name", "Item Price", "Count", "Item Total"};

    public InvoiceLineTableModel(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    
    @Override
    public int getRowCount() {
        return lines.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int x) {
        return columns[x];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine line = lines.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return line.getInvoiceHeade().getInvoiceNum();
            case 1: return line.getItemName();
            case 2: return line.getItemPrice();
            case 3: return line.getCount();
            case 4: return line.getLineTotal();
            default : return "";
        }
    }
    
}
