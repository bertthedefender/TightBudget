package evos.tightbudget;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.Expense;
import evos.tightbudget.model.OutgoingExpense;
import evos.tightbudget.model.Utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by S on 09/03/2016.
 */
public class RegularPaymentsPresenterTest {


    private BudgetCategory regularCategory;
    private OutgoingExpense regularExpense1;
    private OutgoingExpense regularExpense2;

    @Before
    public void setUp() throws Exception {
        regularCategory = new Category("Regular", Amount.fromPence(1000));
        regularExpense1 = new OutgoingExpense("Fuel", Utils.getDate(2001, 01, 01), Amount.fromPence(50));
        regularExpense2 = new OutgoingExpense("Fuel", Utils.getDate(2001, 01, 01), Amount.fromPence(50));
        regularCategory.addOutgoing(regularExpense1);
        regularCategory.addOutgoing(regularExpense2);
    }

    @Test
    public void givenACategoryThatIndicatesRegularPayments_thePresenterSetsTheViewAppropriately() {


        CapturingRegularPaymentsView capturingRegularPaymentsView = new CapturingRegularPaymentsView();

        RegularPaymentsPresenter presenter = new RegularPaymentsPresenter(capturingRegularPaymentsView, regularCategory);

        assertThat(capturingRegularPaymentsView.getRegularPayments().size(), is(2));

    }

    @Test
    public void givenACategoryThatIndicatesRegularPayments_thePresenterAddsAPaymentWhenInstructedByTheView() {



    }


    private class RegularPaymentsPresenter {
        public RegularPaymentsPresenter(RegularPaymentsView regularPaymentsView, BudgetCategory category) {

            regularPaymentsView.setOutgoings(category.getOutgoings());

        }
    }

    private class CapturingRegularPaymentsView implements RegularPaymentsView {

        private List<Expense> regularPayments = new ArrayList<>();


        @Override
        public List<Expense> getRegularPayments() {
            return regularPayments;
        }

        @Override
        public void setOutgoings(List<Expense> outgoings) {
            regularPayments = outgoings;
        }
    }

    interface RegularPaymentsView {


        List<Expense> getRegularPayments();

        void setOutgoings(List<Expense> outgoings);
    }
}
