package evos.tightbudget;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.model.Utils;
import evos.tightbudget.presenter.NewOutgoingPresenter;
import evos.tightbudget.view.NewOutgoingExpenseView;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public class NewOutgoingPresenterTest {


    private class TestNewOutgoingExpenseView implements NewOutgoingExpenseView {
        private String name;
        private Amount budget;
        public List<Callback> callbacks = new ArrayList<>();
        private Date date;

        public void setDescription(String name) {
            this.name = name;
        }

        public void setAmount(Amount budget) {
            this.budget = budget;
        }

        public void invokeAdd() {
            for (Callback callback:callbacks) {
                callback.addClicked();
            }
        }

        @Override
        public void addCallback(Callback callback) {
            callbacks.add(callback);
        }

        @Override
        public String getOutgoingDescription() {
            return name;
        }

        @Override
        public Date getOutgoingDate() {
            return date;
        }

        @Override
        public int getOutgoingAmount() {
            return budget.asPence();
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }



    @Test
    public void whenAPresenterIsGivenAView_itSetsupTheCallbacks() {
        TestNewOutgoingExpenseView view  = new TestNewOutgoingExpenseView();
        NewOutgoingPresenter presenter = new NewOutgoingPresenter(view,new Category("",Amount.fromPence(0)));

        assertThat(view.callbacks.size()>0, is(true));
    }


    @Test
    public void whenAPresenterIsGivenAModelAndCategoryDetails_andToldToAddIt_ItIsAddedToTheModel() {

        Amount expectedAmount = Amount.fromPence(100);
        String expectedDescription = "added";
        final Boolean[] callbackfired = new Boolean[1];

        TightBudgetModel model = new TightBudgetModel(Amount.fromPence(1000));

        TestNewOutgoingExpenseView view = new TestNewOutgoingExpenseView();
        view.setDescription(expectedDescription);
        view.setAmount(expectedAmount);
        view.setDate(Utils.getDate(2010, 07, 17));

        Category testCategory = new Category("category", Amount.fromPence(100));

        NewOutgoingPresenter presenter = new NewOutgoingPresenter(view, testCategory);

        presenter.addNewOutgoingAddedCallback(new NewOutgoingPresenter.NewOutgoingAddedCallback() {
            @Override
            public void newOutgoingAdded() {
                callbackfired[0] = true;
            }
        });

        view.invokeAdd();

        assertThat(testCategory.getOutgoings().get(0).getAmount().asPence(), is(expectedAmount.asPence()));
        assertThat(testCategory.getOutgoings().get(0).getDate(), is(Utils.getDate(2010,07,17)));
        assertThat(testCategory.getOutgoings().get(0).getDescription(), is(expectedDescription));
        assertThat(callbackfired[0], is(true));
    }

}
