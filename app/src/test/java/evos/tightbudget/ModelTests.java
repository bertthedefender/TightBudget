package evos.tightbudget;

import org.junit.Test;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.Expense;
import evos.tightbudget.model.OutgoingExpense;
import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.model.Utils;

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
        assertTrue(category.getOutgoing(0).getDate().equals(Utils.getDate(2001, 01, 01)));

    }

    @Test
    public void givenACategoryHasMultipleExpenses_theCorrectExpenseTotalIsCalculated() {

        BudgetCategory category = new Category(CATEGORY_NAME, BUDGET_AMOUNT);
        category.addOutgoing(new OutgoingExpense("Item 1", Utils.getDate(2015, 12, 15), Amount.fromPence(50)));
        category.addOutgoing(new OutgoingExpense("Item 2", Utils.getDate(2015, 12, 16), Amount.fromPence(100)));

        assertThat(category.getTotalSpend().asPence(), is(150));
    }

    @Test
    public void whenANewOutgoingIsAdded_theCateogryNotifiesItsObservers() {

        BudgetCategory category = new Category("Test", Amount.fromPence(100));
        OutgoingExpense expense = new OutgoingExpense("X", TestHelpers.anyDate, Amount.fromPence(10));
        CapturingNewExpenseListener capturingNewExpenseListener = new CapturingNewExpenseListener();

        category.addOutgoingExpenseAddedCallback(capturingNewExpenseListener);

        category.addOutgoing(expense);

        assertThat(capturingNewExpenseListener.capturedCategory, is("Test"));

    }

    @Test
    public void whenANewCategoryIsAdded_theModelNotifiesItsObservers() {

        CapturingCategoryAddedCallback capturingCategoryAddedCallback = new CapturingCategoryAddedCallback();


        TightBudgetModel model = new TightBudgetModel(Amount.fromPence(1000));

        model.addCategoryAddedCallback(capturingCategoryAddedCallback);

        Category category = new Category("expected",Amount.fromPence(100));
        model.addCategory(category);

        assertThat(capturingCategoryAddedCallback.capturedName, is("expected"));

    }

    private class CapturingCategoryAddedCallback implements TightBudgetModel.CategoryAddedCallback {


        public String capturedName;

        @Override
        public void invoke(String categoryName) {
            capturedName = categoryName;
        }

    }

    private class CapturingNewExpenseListener implements BudgetCategory.OutgoingExpenseAddedCallback {

        public String capturedCategory;

        @Override
        public void invoke(String categoryName) {
            capturedCategory = categoryName;
        }
    }


}
