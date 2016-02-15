package evos.tightbudget;

import org.junit.Test;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.OutgoingExpense;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by S on 15/02/2016.
 */
public class CategoryFragmentPresenterTest {



    @Test
    public void givenACategory_theCategoryFragmentPresenter_CorrectlySetsTheCategoryPositionText()
    {

        Amount expectedBudgetAmount = Amount.fromPence(500);
        String expectedCategoryName = "Category A";
        Amount expectedCurrentAmount = Amount.fromPence(150);

        CapturingCategoryFragment capturingCategoryFragment = new CapturingCategoryFragment();
        BudgetCategory category = new Category(expectedCategoryName, expectedBudgetAmount);
        category.addOutgoing(new OutgoingExpense("Expense A",TestHelpers.anyDate, expectedCurrentAmount));


        CategoryFragmentPresenter categoryFragmentPresenter = new CategoryFragmentPresenter(capturingCategoryFragment, category);

        categoryFragmentPresenter.bind();


        assertThat(capturingCategoryFragment.categoryName, is(expectedCategoryName));
        assertThat(capturingCategoryFragment.currentSpend.asPence(), is(expectedCurrentAmount.asPence()));
        assertThat(capturingCategoryFragment.budgetTotal.asPence(), is(expectedBudgetAmount.asPence()));

    }


    private class CapturingCategoryFragment implements CategoryFragmentView {

        private String categoryName;
        private Amount currentSpend;
        private Amount budgetTotal;

        @Override
        public void setCurrentPosition(String categoryName, Amount currentSpend, Amount budgetTotal) {

            this.categoryName = categoryName;
            this.currentSpend = currentSpend;
            this.budgetTotal = budgetTotal;
        }

    }

    private class CategoryFragmentPresenter {
        private final CategoryFragmentView categoryFragmentView;
        private final BudgetCategory category;

        public CategoryFragmentPresenter(CategoryFragmentView categoryFragmentView, BudgetCategory category) {

            this.categoryFragmentView = categoryFragmentView;
            this.category = category;
        }

        public void bind() {

            categoryFragmentView.setCurrentPosition(category.getName(), category.getTotalSpend(), category.getBudget());


        }
    }
}
