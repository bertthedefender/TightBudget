package evos.tightbudget;

import org.json.JSONException;
import org.junit.Test;

import java.util.Date;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.Expense;
import evos.tightbudget.model.OutgoingExpense;
import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.model.Utils;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mcdons20 on 15/01/16.
 */
public class PersistenceTests {


    @Test
    public void givenAnExpenseObject_theObjectCanBeCorrectlyConvertedToJson() throws JSONException {

        Date expectedDate = Utils.getDate(2016,1,20);
        Expense persistedExpense = new OutgoingExpense("Test Value", expectedDate, Amount.fromPence(50));

        String jsonOutput = persistedExpense.asJson();

        Expense parsedExpense = OutgoingExpense.fromJson(jsonOutput);

        assertEquals("Test Value", parsedExpense.getDescription());
        assertEquals(50, parsedExpense.getAmount().asPence());
        assertEquals(expectedDate.getTime(), parsedExpense.getDate().getTime());
    }

    @Test
    public void givenABudgetCategoryObject_theOBjectCanBeCorrectlyConvertedToJson() throws JSONException {

        BudgetCategory budgetCategory = new Category("Category Name", Amount.fromPence(500));

        String jsonOutput = budgetCategory.asJson();

        BudgetCategory parsedCategory = Category.fromJson(jsonOutput);

        assertEquals("Category Name", parsedCategory.getName());

    }


    @Test
    public void givenAModelContainingMultipleCategories_theCorrectJSONIsOutput() throws JSONException {

        TightBudgetModel model = new TightBudgetModel();

        Category categoryA = new Category("CategoryA", Amount.fromPence(50));
        Category categoryB = new Category("CategoryB", Amount.fromPence(120));

        categoryA.addOutgoing(new OutgoingExpense("Exp 1", Utils.getDate(2013,1,1), Amount.fromPence(50)));
        categoryA.addOutgoing(new OutgoingExpense("Exp 2", Utils.getDate(2014,2,2), Amount.fromPence(120)));

        categoryB.addOutgoing(new OutgoingExpense("Exp 3", Utils.getDate(2015,3,3), Amount.fromPence(270)));

        model.addCategory(categoryA);
        model.addCategory(categoryB);

        String outputJSON = model.asJSON();

        TightBudgetModel parsedModel = model.fromJson(outputJSON);

        assertNotNull(parsedModel.getCategory("CategoryA"));
        assertEquals(parsedModel.getCategory("CategoryA").getBudget().asPence(),50);

        assertNotNull(parsedModel.getCategory("CategoryB"));
        assertEquals(parsedModel.getCategory("CategoryB").getBudget().asPence(),120);

    }


}
