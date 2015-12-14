package evos.tightbudget;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by mcdons20 on 14/12/15.
 */
public class ModelTests {


    @Test
    public void givenACategoryIsAddedToTheListOfCategories_theListWillContainTheCategory() {

        BudgetCategory category = new Category("ExpectedName");

        TightBudgetModel model = new TightBudgetModel();

        model.addCategory(category);

        BudgetCategory expected = model.getCategory("ExpectedName");

        assertThat(expected.getName(), is("ExpectedName"));

    }
}
