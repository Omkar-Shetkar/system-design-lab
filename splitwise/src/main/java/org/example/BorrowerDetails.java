package org.example;

public record BorrowerDetails(User creditor, User borrower, int amount) {

    public BorrowerDetails withAmount(int amount) {
        return new BorrowerDetails(creditor, borrower, amount);
    }

    @Override
    public String toString() {
        return String.format("%s owes %s", borrower, creditor);
    }
}
