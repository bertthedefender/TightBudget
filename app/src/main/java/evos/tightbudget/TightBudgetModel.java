package evos.tightbudget;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mcdons20 on 14/12/15.
 */
class TightBudgetModel {

    private Map<String, BudgetCategory> categories;

    public TightBudgetModel() {
        categories = new HashMap<>();
    }

    public void addCategory(BudgetCategory category) {
        categories.put(category.getName(), category);
    }

    public BudgetCategory getCategory(String expectedName) {
        return categories.get(expectedName);
    }
}
