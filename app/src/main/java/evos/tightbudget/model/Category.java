package evos.tightbudget.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcdons20 on 14/12/15.
 */
public class Category implements BudgetCategory {

    private String categoryName;
    private Amount budgetAmount;
    private List<Expense> expenses;
    private ArrayList<OutgoingExpenseAddedCallback> outgoingExpenseAddedCallbacks = new ArrayList<>();

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
        this.expenses.add(0,expense);

        for (OutgoingExpenseAddedCallback callback:outgoingExpenseAddedCallbacks) {
            callback.invoke(getName());
        }
    }

    @Override
    public int getOutgoingCount() {
        return expenses.size();
    }

    @Override
    public void addOutgoingExpenseAddedCallback(OutgoingExpenseAddedCallback callback) {
        outgoingExpenseAddedCallbacks.add(callback);
    }

    @Override
    public String asJson() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("CategoryName", categoryName);
            jsonObject.put("Amount", budgetAmount.asPence());
        } catch (JSONException e) {
            return null;
        }

        return jsonObject.toString();
    }

    @Override
    public Expense getOutgoing(int index) {
        return expenses.get(index);
    }

    @Override
    public List<Expense> getOutgoings() {
        return expenses;
    }

    public static BudgetCategory fromJson(String json) {

        JSONObject jsonObject;

        BudgetCategory category = null;

        try {
            jsonObject = new JSONObject(json);
            String categoryName = jsonObject.getString("CategoryName");
            Amount budgetAmount = Amount.fromPence(jsonObject.getInt("Amount"));
            category = new Category(categoryName, budgetAmount);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return category;
    }
}
