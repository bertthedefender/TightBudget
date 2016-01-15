package evos.tightbudget;

import org.json.JSONObject;

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

    public String asJSON() {

        JSONObject modelJSON = new JSONObject();

        for (Map.Entry<String,BudgetCategory> entry : categories.entrySet()) {

            JSONObject categoryJSON = new JSONObject();
            categoryJSON.put(entry.getKey(), entry.getValue().asJSON());


        }

        return modelJSON.toString();

    }
}
