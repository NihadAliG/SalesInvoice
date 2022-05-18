/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesinvoice.model;

public class InvoiceLine {
    
    private String itemName;
    private double itemPrice;
    private int count;
    private InvoiceHeader invoiceHeade;

    public InvoiceLine() {
    }

    
    public InvoiceLine( String itemName, double itemPrice, int count) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
    }

    public InvoiceLine(String itemName, double itemPrice, int count, InvoiceHeader invoiceHeade) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.invoiceHeade = invoiceHeade;
    }
      

  
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InvoiceHeader getInvoiceHeade() {
        return invoiceHeade;
    }

    public void setInvoiceHeade(InvoiceHeader invoiceHeade) {
        this.invoiceHeade = invoiceHeade;
    }
    
    
    
    public double getLineTotal() {
        return itemPrice * count;
    }
    
}
