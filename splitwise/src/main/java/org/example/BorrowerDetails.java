package org.example;

import java.util.Objects;

public final class BorrowerDetails {

    private final User creditor;
    private final User borrower;
    private int amount;

    public BorrowerDetails(User creditor, User borrower, int amount) {
        this.creditor = creditor;
        this.borrower = borrower;
        this.amount = amount;
    }

    public void updateAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%s owes %s - Rs. %d", borrower.name(), creditor.name(), amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowerDetails that = (BorrowerDetails) o;
        return Objects.equals(creditor, that.creditor) && Objects.equals(borrower, that.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditor, borrower);
    }

    public User creditor() {
        return creditor;
    }

    public User borrower() {
        return borrower;
    }

    public int amount() {
        return amount;
    }

}
