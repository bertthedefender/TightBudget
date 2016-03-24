package evos.tightbudget;

import org.junit.Test;

import java.util.Map;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.presenter.NewCategoryPresenter;
import evos.tightbudget.view.NewCategoryView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public class NewCategoryPresenterTest {

    private class CapturingNewCategoryView implements NewCategoryView {
        public int categoryCount = 0;

        @Override
        public void setCategories(Map<String, BudgetCategory> categories) {
            categoryCount = categories.size();
        }
    }


    @Test
    public void whenACategoryPresenterIsCreated_itSetsTheListOfCurrentCategoriesOnItsView() {

        TightBudgetModel model = new TightBudgetModel(Amount.fromPence(5000));
        model.addCategory(new Category("1",Amount.fromPence(1)));
        model.addCategory(new Category("2",Amount.fromPence(2)));

        CapturingNewCategoryView capturingNewCategoryView = new CapturingNewCategoryView();

        NewCategoryPresenter newCategoryPresenter = new NewCategoryPresenter(capturingNewCategoryView, model);

        assertThat(capturingNewCategoryView.categoryCount, is(2));

    }

}
