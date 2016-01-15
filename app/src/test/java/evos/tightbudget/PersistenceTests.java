package evos.tightbudget;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by mcdons20 on 15/01/16.
 */
public class PersistenceTests {


    @Test
    public void givenAModelContainingMultipleCategories_theCorrectJSONIsOutput() {

        TightBudgetModel model = new TightBudgetModel();

        Category categoryA = new Category("CategoryA", Amount.fromPence(50));
        Category categoryB = new Category("CategoryB", Amount.fromPence(100));

        model.addCategory(categoryA);
        model.addCategory(categoryB);

        String expectedJSON = "";
        String outputJSON = model.asJSON();

        assertEquals(expectedJSON, outputJSON);
    }


}
