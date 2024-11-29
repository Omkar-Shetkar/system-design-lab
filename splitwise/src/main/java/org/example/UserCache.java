package org.example;

import java.util.HashSet;
import java.util.Set;

public class UserCache {

    private final Set<BorrowerDetails> borrowerCache = new HashSet<>();

    private final Set<User> users = new HashSet<>();

    public void updateBorrowerDetails(BorrowerDetails borrowerDetails) {

        if (borrowerCache.contains(borrowerDetails)) {
            for (BorrowerDetails details : borrowerCache) {
                if (details.equals(borrowerDetails)) {
                    details = details.withAmount(details.amount() + borrowerDetails.amount());
                    borrowerCache.add(details);
                }
            }
        } else {
            borrowerCache.add(borrowerDetails);
        }


    }
}
