package org.example;

public class ExactISplitStrategy implements ISplitStrategy {
    @Override
    public void split(SplitDetails splitDetails) {

        for (int i = 0; i < splitDetails.borrowers().size(); i++) {
            User borrower = splitDetails.borrowers().get(i);
            borrower.updateBorrowedAmount(splitDetails.creditor(), splitDetails.exactAmounts()[i]);
        }

    }
}
