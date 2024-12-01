package org.example;

public class EqualISplitStrategy implements ISplitStrategy {

    @Override
    public void split(SplitDetails splitDetails) {
        int splitAmount = splitDetails.totalAmount() / splitDetails.borrowers().size();

        for (User borrower : splitDetails.borrowers()) {
            borrower.updateBorrowedAmount(splitDetails.creditor(), splitAmount);
        }

    }
}
