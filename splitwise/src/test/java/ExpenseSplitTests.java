import org.example.SplitDetails;
import org.example.User;
import org.junit.jupiter.api.Test;

public class ExpenseSplitTests {

    @Test
    public void expenseTest() {
        SplitDetails splitDetails = new SplitDetails("EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL");
        User creditor = splitDetails.creditor();
        creditor.pay(splitDetails);

        System.out.println(splitDetails);
        creditor.show();
        System.out.println("-------------------------------");
        for(User borrower: splitDetails.borrowers()) {
            borrower.show();
        }
        System.out.println("-------------------------------");

        splitDetails = new SplitDetails("EXPENSE u1 1250 2 u2 u3 EXACT 370 880");
        creditor = splitDetails.creditor();
        creditor.pay(splitDetails);
        System.out.println(splitDetails);
        for(User borrower: splitDetails.borrowers()) {
            borrower.show();
        }
        System.out.println("-------------------------------");

//        EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20

        splitDetails = new SplitDetails("EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20");
        creditor = splitDetails.creditor();
        creditor.pay(splitDetails);
        System.out.println(splitDetails);
        for(User borrower: splitDetails.borrowers()) {
            borrower.show();
        }
        System.out.println("-------------------------------");

    }

    @Test
    public void expenseTest1() {
        SplitDetails splitDetails = new SplitDetails("EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20");
        User creditor = splitDetails.creditor();
        creditor.pay(splitDetails);

        System.out.println("EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20");
        creditor.show();
        System.out.println("-------------------------------");
        for(User borrower: splitDetails.borrowers()) {
            borrower.show();
        }
        System.out.println("-------------------------------");

    }

}
