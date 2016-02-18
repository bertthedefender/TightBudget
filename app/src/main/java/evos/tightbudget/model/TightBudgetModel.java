package evos.tightbudget.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mcdons20 on 14/12/15.
 */
public class TightBudgetModel {

    public Map<String, BudgetCategory> categories;

    public TightBudgetModel() {
        categories = new HashMap<>();
    }

    public void addCategory(BudgetCategory category) {
        categories.put(category.getName(), category);
    }

    public BudgetCategory getCategory(String expectedName) {
        return categories.get(expectedName);
    }

    public String asJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();

        for (Map.Entry<String, BudgetCategory> category : categories.entrySet()) {
            jsonObject.put(category.getKey(), new JSONObject(categories.get(category.getKey()).asJson()));
            jsonObject.put(category.getKey(), categories.get(category.getKey()).getOutgoings());


        }

        return jsonObject.toString();

    }

    public TightBudgetModel fromJson(String json) {
        return new TightBudgetModel();
    }
}
