package edu.gatech.seclass.tccart.business;

import java.text.DecimalFormat;

public class Item {

    private String itemName;
	private double unitPrice; ;
    private DecimalFormat twoDigits = new DecimalFormat("0.00");

    public Item(){
        this.itemName = "Default";
        this.unitPrice = 0.00;
    }

    public Item(String name, double price) {
        this.itemName = name;
        this.unitPrice = price;
    }

    @Override
    public String toString() {
        return this.itemName + " [$" + twoDigits.format(this.unitPrice) + "]";
    }

    public String getItemName() { return itemName; }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setItemName(String name) {
        this.itemName = name;
    }

    public void setUnitPrice(double price) {
        double priceFormatted = Double.parseDouble(twoDigits.format(price));
        this.unitPrice = priceFormatted;
    }
}
