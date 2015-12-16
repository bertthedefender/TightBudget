package evos.tightbudget;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Created by mcdons20 on 14/12/15.
 */
public class ModelTests {


    public static final String CATEGORY_NAME = "ExpectedName";
    private static final Amount BUDGET_AMOUNT = Amount.fromPence(200);

    @Test
    public void givenACategoryIsAddedToTheListOfCategories_theListWillContainTheCategory() {

        int expectedBudget = 200;

        BudgetCategory category = new Category(CATEGORY_NAME, Amount.fromPence(expectedBudget));

        TightBudgetModel model = new TightBudgetModel();
        model.addCategory(category);
        BudgetCategory expected = model.getCategory(CATEGORY_NAME);
        assertThat(expected.getName(), is(CATEGORY_NAME));
        assertThat(expected.getBudget().asPence(), is(expectedBudget));
    }


    @Test
    public void givenAnOutgoingExpenseIsAddedToACategory_theExpenseIsSuccessfullyAdded() {

        BudgetCategory category = new Category(CATEGORY_NAME, BUDGET_AMOUNT);

        Expense expense = new OutgoingExpense("ExpectedDescription", Utils.getDate(2001,01,01), Amount.fromPence(50));

        category.addOutgoing(expense);

        assertThat(category.getOutgoingCount(), is(1));
        assertThat(category.getOutgoing(0).getDescription(), is("ExpectedDescription"));
        assertThat(category.getOutgoing(0).getAmount().asPence(), is(50));
        assertTrue(category.getOutgoing(0).getDate().equals(Utils.getDate(2001,01,01)));

    }

    @Test
    public void givenACateogryHasMultipleExpenses_theCorrectExpenseTotalIsCalculated() {

        BudgetCategory category = new Category(CATEGORY_NAME, BUDGET_AMOUNT);
        category.addOutgoing(new OutgoingExpense("Item 1", Utils.getDate(2015,12,15), Amount.fromPence(50)));
        category.addOutgoing(new OutgoingExpense("Item 2", Utils.getDate(2015,12,16), Amount.fromPence(100)));

        assertThat(category.getTotalSpend().asPence(), is(150));

    }
}
