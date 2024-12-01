package org.example;

import java.util.List;

public class User {

    private final String name;

    public User(String name) {
        this.name = name;
    }

    /**
     * Creditor pays to borrowers.
     */
    public void pay(List<BorrowerDetails> borrowers) {

    }

    /**
     * Borrower borrows given amount from the creditor.
     */
    public void updateBorrowedAmount(User creditor, int amount) {

    }

    /**
     * Borrowed details of the current user.
     */
    public List<BorrowerDetails> borrowedDetails() {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
