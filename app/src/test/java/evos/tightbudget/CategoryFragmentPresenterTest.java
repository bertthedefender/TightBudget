package evos.tightbudget;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.Expense;
import evos.tightbudget.model.OutgoingExpense;
import evos.tightbudget.presenter.CategoryFragmentPresenter;
import evos.tightbudget.view.CategoryFragmentView;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by S on 15/02/2016.
 */
public class CategoryFragmentPresenterTest {

    final Amount EXPECTED_BUDGET_AMOUNT = Amount.fromPence(500);
    final String EXPECTED_CATEGORY_NAME = "Category A";
    final Amount EXPECTED_CURRENT_POSITION = Amount.fromPence(150);

    private CapturingCategoryFragment capturingCategoryFragment;
    private BudgetCategory category;


    @Before
    public void setup() {
        capturingCategoryFragment = new CapturingCategoryFragment();
        category = new Category(EXPECTED_CATEGORY_NAME, EXPECTED_BUDGET_AMOUNT);
        category.addOutgoing(new OutgoingExpense("Expense A", TestHelpers.anyDate, EXPECTED_CURRENT_POSITION));
    }

    @Test
    public void givenACategory_theCategoryFragmentPresenter_CorrectlySetsTheCategoryPositionText()
    {
        CategoryFragmentPresenter categoryFragmentPresenter = new CategoryFragmentPresenter(capturingCategoryFragment, category);

        categoryFragmentPresenter.bind();

        assertThat(capturingCategoryFragment.categoryName, is(EXPECTED_CATEGORY_NAME));
        assertThat(capturingCategoryFragment.currentSpend.asPence(), is(EXPECTED_CURRENT_POSITION.asPence()));
        assertThat(capturingCategoryFragment.budgetTotal.asPence(), is(EXPECTED_BUDGET_AMOUNT.asPence()));
        assertThat(capturingCategoryFragment.capturedData.size(), is(1));
    }

    private class CapturingCategoryFragment implements CategoryFragmentView {

        public String categoryName;
        public Amount currentSpend;
        public Amount budgetTotal;
        public List<Expense> capturedData;


        @Override
        public void setCurrentPosition(String categoryName, Amount currentSpend, Amount budgetTotal) {
            this.categoryName = categoryName;
            this.currentSpend = currentSpend;
            this.budgetTotal = budgetTotal;
        }

        @Override
        public void setOutgoingExpenseData(List<Expense> expenseData) {
            this.capturedData = expenseData;
        }

    }

}
