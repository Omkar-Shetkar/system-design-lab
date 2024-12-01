package org.example;

public class PercentISplitStrategy implements ISplitStrategy {
    @Override
    public void split(SplitDetails splitDetails) {

        for (int i = 0; i < splitDetails.borrowers().size(); i++) {
            User borrower = splitDetails.borrowers().get(i);
            int splitAmount = (splitDetails.totalAmount() * splitDetails.percentageAmounts()[i]) / 100;
            borrower.updateBorrowedAmount(splitDetails.creditor(), splitAmount);
        }
    }
}
