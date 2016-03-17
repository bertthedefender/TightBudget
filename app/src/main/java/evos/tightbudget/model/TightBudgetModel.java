package evos.tightbudget.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mcdons20 on 14/12/15.
 */
public class TightBudgetModel {

    public interface CategoryAddedCallback {
        void invoke(String categoryName);
    }

    public Amount budgetAmount;
    public Map<String, BudgetCategory> categories;
    private ArrayList<CategoryAddedCallback> categoryAddedCallbacks = new ArrayList<>();

    public TightBudgetModel() {
        categories = new HashMap<>();
    }

    public void addCategory(BudgetCategory category) {
        categories.put(category.getName(), category);

        for (CategoryAddedCallback categoryAddedCallback : categoryAddedCallbacks) {
            categoryAddedCallback.invoke(category.getName());
        }
    }

    public BudgetCategory getCategory(String expectedName) {
        return categories.get(expectedName);
    }

    public TightBudgetModel(Amount budgetAmount) {
        this();
        this.budgetAmount = budgetAmount;
    }

    public void addCategoryAddedCallback(CategoryAddedCallback categoryAddedCallback) {
        this.categoryAddedCallbacks.add(categoryAddedCallback);
    }
}
