package evos.tightbudget;

/**
 * Created by mcdons20 on 14/12/15.
 */
interface BudgetCategory extends JSONPersistence {
    String getName();
    Amount getBudget();
    Amount getTotalSpend();

    void addOutgoing(Expense expense);
    Expense getOutgoing(int index);
    int getOutgoingCount();

}
