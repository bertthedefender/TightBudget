package evos.tightbudget.model;

import java.util.Date;

/**
 * Created by mcdons20 on 14/12/15.
 */
public interface Expense extends JSONPersistence<Expense> {
    String getDescription();
    Date getDate();
    Amount getAmount();
}
