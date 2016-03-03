package evos.tightbudget;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.presenter.NewOutgoingPresenter;
import evos.tightbudget.view.OutgoingPresenterView;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public class NewOutgoingPresenterTest {


    private class TestOutgoingPresenterView implements OutgoingPresenterView {
        private String name;
        private Amount budget;
        public List<Callback> callbacks = new ArrayList<>();

        public void setName(String name) {
            this.name = name;
        }

        public void setBudget(Amount budget) {
            this.budget = budget;
        }

        public void invokeAdd() {
            for (Callback callback:callbacks) {
                callback.addOutgoing();
            }
        }

        @Override
        public void addCallback(Callback callback) {
            callbacks.add(callback);
        }

        @Override
        public String getCategoryName() {
            return name;
        }

        @Override
        public int getCategoryBudget() {
            return budget.asPence();
        }
    }

    @Test
    public void whenAPresenterIsGivenAView_itSetsupTheCallbacks() {
        TestOutgoingPresenterView view  = new TestOutgoingPresenterView();
        NewOutgoingPresenter presenter = new NewOutgoingPresenter(view, new TightBudgetModel());

        assertThat(view.callbacks.size()>0, is(true));
    }


    @Test
    public void whenAPresenterIsGivenAModelAndCategoryDetails_andToldToAddIt_ItIsAddedToTheModel() {

        String expectedName = "added";
        Amount expectedBudget = Amount.fromPence(100);

        TightBudgetModel model = new TightBudgetModel(Amount.fromPence(1000));

        TestOutgoingPresenterView view = new TestOutgoingPresenterView();
        view.setName(expectedName);
        view.setBudget(expectedBudget);

        NewOutgoingPresenter presenter = new NewOutgoingPresenter(view, model);

        view.invokeAdd();

        BudgetCategory addedCategory = model.getCategory(expectedName);
        assertNotNull(addedCategory);
        assertThat(addedCategory.getName(), is(expectedName));
        assertThat(addedCategory.getBudget().asPence(), is(expectedBudget.asPence()));
    }

}
