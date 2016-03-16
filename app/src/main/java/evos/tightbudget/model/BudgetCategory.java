package evos.tightbudget.model;

import java.util.List;

/**
 * Created by mcdons20 on 14/12/15.
 */
public interface BudgetCategory extends JSONPersistence {
    String getName();
    Amount getBudget();
    Amount getTotalSpend();

    void addOutgoing(Expense expense);
    Expense getOutgoing(int index);
    List<Expense> getOutgoings();
    int getOutgoingCount();

    void addOutgoingExpenseAddedCallback(OutgoingExpenseAddedCallback callback);

    interface OutgoingExpenseAddedCallback {
        void invoke(String categoryName);
    }

}
