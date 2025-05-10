package org.project.pharmacy.logic;
public interface Sellable {
    void sellItem();
    double getPrice();
    boolean isAvailable();
    boolean isSoldOut();
}
