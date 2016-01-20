package evos.tightbudget;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mcdons20 on 15/01/16.
 */
public class PersistenceTests {


    @Test
    public void givenAnExpenseObject_theObjectCanBeCorrectlyConvertedToJson() throws JSONException {

        Expense persistedExpense = new OutgoingExpense("Test Value", Utils.getDate(2016,1,20), Amount.fromPence(50));

        String jsonOutput = persistedExpense.asJson().toString();

        Expense parsedExpense = OutgoingExpense.fromJson(jsonOutput);

        assertEquals("Test Value", parsedExpense.getDescription());
        assertEquals(50, parsedExpense.getAmount().asPence());
        //TODO: Assert the date....
    }


    @Test
    public void givenAModelContainingMultipleCategories_theCorrectJSONIsOutput() throws JSONException {

        TightBudgetModel model = new TightBudgetModel();

        Category categoryA = new Category("CategoryA", Amount.fromPence(50));
        Category categoryB = new Category("CategoryB", Amount.fromPence(120));

        model.addCategory(categoryA);
        model.addCategory(categoryB);

        JSONObject outputJSON = model.asJSON();

        String json = outputJSON.toString();

        JSONObject parsedJSON = new JSONObject(json);

        TightBudgetModel parsedModel = (TightBudgetModel)(parsedJSON.get("budgetModel"));

        assertNotNull(parsedModel.getCategory("CategoryA"));
        assertEquals(parsedModel.getCategory("CategoryA").getBudget().asPence(),50);

        assertNotNull(parsedModel.getCategory("CategoryB"));
        assertEquals(parsedModel.getCategory("CategoryB").getBudget().asPence(),120);

    }


}
