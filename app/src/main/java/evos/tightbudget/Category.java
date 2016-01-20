package evos.tightbudget;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcdons20 on 14/12/15.
 */
class Category implements BudgetCategory {

    private String categoryName;
    private Amount budgetAmount;
    private List<Expense> expenses;

    public Category(String categoryName, Amount budgetAmount) {
        expenses = new ArrayList<>();

        this.categoryName = categoryName;
        this.budgetAmount = budgetAmount;
    }

    @Override
    public String getName() {
        return this.categoryName;
    }

    @Override
    public Amount getBudget() {
        return this.budgetAmount;
    }

    @Override
    public Amount getTotalSpend() {
        int returnVal = 0;
        for (Expense expense : expenses) {
            returnVal += expense.getAmount().asPence();
        }
        return Amount.fromPence(returnVal);
    }

    @Override
    public void addOutgoing(Expense expense) {
        this.expenses.add(expense);
    }

    @Override
    public int getOutgoingCount() {
        return expenses.size();
    }

    @Override
    public JSONObject asJson() {
        return null;
    }


    @Override
    public Expense getOutgoing(int index) {
        return expenses.get(index);
    }
}
