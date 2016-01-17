package evos.tightbudget;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mcdons20 on 14/12/15.
 */
interface BudgetCategory {
    String getName();
    Amount getBudget();
    Amount getTotalSpend();

    void addOutgoing(Expense expense);
    Expense getOutgoing(int index);
    int getOutgoingCount();

    JSONObject asJSON() throws JSONException;
}
