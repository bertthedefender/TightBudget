package evos.tightbudget;

/**
 * Created by mcdons20 on 14/12/15.
 */
interface BudgetCategory {
    String getName();

    Amount getBudget();

    void addOutgoing(Outgoing outgoing);

    int getOutgoingCount();

    Outgoing getOutgoing(int index);
}
