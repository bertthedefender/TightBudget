package evos.tightbudget;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public JSONObject asJSON() throws JSONException {

        JSONObject modelJSON = new JSONObject();
        List<JSONObject> categoriesAsJson = new ArrayList<>();

        for (Map.Entry<String,BudgetCategory> entry : categories.entrySet()) {

            JSONObject categoryJSON = new JSONObject();
            categoryJSON.put(entry.getKey(), entry.getValue().asJSON());

            categoriesAsJson.add(categoryJSON);
        }

        modelJSON.put("categories", categoriesAsJson);

        return modelJSON;

    }
}
