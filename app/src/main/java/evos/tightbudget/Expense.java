package evos.tightbudget;

import java.util.Date;

/**
 * Created by mcdons20 on 14/12/15.
 */
public interface Expense {
    String getDescription();

    Date getDate();

    Amount getAmount();
}