package org.example;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class User {

    private final String name;

    /**
     * Borrowed details of current user
     */
    private Set<BorrowerDetails> borrowerDetails = new HashSet<>();

    public User(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    /**
     * Creditor pays to borrowers.
     */
    public void pay(SplitDetails splitDetails) {
        SplitStrategy splitStrategy = SplitStrategyFactory.splitStrategy(splitDetails.splitType());
        splitStrategy.split(splitDetails);
    }

    /**
     * Borrower borrows given amount from the creditor.
     */
    public void updateBorrowedAmount(User creditor, int amount) {

        if (this.equals(creditor)) { // Creditor and Borrower are same
            return;
        }

        // Check if creditor has borrowed amount from this user. If yes, then settle the amount
        int settlementAmount = creditor.settleAmount(this);

        Optional<BorrowerDetails> optional = borrowerDetails.stream()
                .filter(b -> b.creditor().equals(creditor)).findFirst();

        if (optional.isPresent()) {
            BorrowerDetails details = optional.get();
            details.updateAmount(details.amount() + amount - settlementAmount);
        } else {
            BorrowerDetails details = new BorrowerDetails(creditor, this, amount - settlementAmount);
            borrowerDetails.add(details);
        }

    }

    private int settleAmount(User creditor) {
        int borrowedAmount = 0;
        for (BorrowerDetails b : borrowerDetails) {
            if (b.creditor().equals(creditor)) {
                borrowedAmount = b.amount();
                borrowerDetails.remove(b);
                break;
            }
        }
        return borrowedAmount;
    }

    public void show() {
        if (borrowerDetails.isEmpty()) {
            System.out.println(String.format("No balances for %s", name));
        }
        System.out.println(borrowerDetails);
    }

    @Override
    public String toString() {
        return name;
    }


}
