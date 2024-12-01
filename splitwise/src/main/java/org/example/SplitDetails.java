package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SplitDetails {

    private final User creditor;

    private List<User> borrowers = new ArrayList<>();

    private final int totalAmount;

    private final SplitType splitType;

    private int[] exactAmounts = new int[0];

    private int[] percentageAmounts = new int[0];

    //    u1 1000 4 u1 u2 u3 u4 EQUAL
    public SplitDetails(String splitDetails) {
        String[] splits = splitDetails.split(" ");
        int splitIndex = 1;
        creditor = UserCache.user(splits[splitIndex]);
        splitIndex++;
        totalAmount = Integer.parseInt(splits[splitIndex]);
        splitIndex++;
        int totalBorrowers = Integer.parseInt(splits[splitIndex]);
        for (int i = 1; i <= totalBorrowers; i++) {
            splitIndex++;
            User borrower = UserCache.user(splits[splitIndex]);
            borrowers.add(borrower);
        }

        splitIndex++;
        splitType = SplitType.valueOf(splits[splitIndex]);

        if (splitType == SplitType.EXACT) {
            exactAmounts = new int[totalBorrowers];
            for (int i = 0; i < exactAmounts.length; i++) {
                splitIndex++;
                exactAmounts[i] = Integer.valueOf(splits[splitIndex]);
            }
        }

        if (splitType == SplitType.PERCENT) {
            percentageAmounts = new int[totalBorrowers];
            for (int i = 0; i < percentageAmounts.length; i++) {
                splitIndex++;
                percentageAmounts[i] = Integer.valueOf(splits[splitIndex]);
            }
        }

    }

    public User creditor() {
        return creditor;
    }

    public List<User> borrowers() {
        return borrowers;
    }

    public int totalAmount() {
        return totalAmount;
    }

    public SplitType splitType() {
        return splitType;
    }

    public int[] exactAmounts() {
        return exactAmounts;
    }

    public int[] percentageAmounts() {
        return percentageAmounts;
    }

    @Override
    public String toString() {
        return "SplitDetails{" +
                "creditor=" + creditor +
                ", borrowers=" + borrowers +
                ", totalAmount=" + totalAmount +
                ", splitType=" + splitType +
                '}';
    }

    public Set<BorrowerDetails> borrowerDetails() {
        return null;
    }
}
