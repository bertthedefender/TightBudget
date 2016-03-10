package evos.tightbudget;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
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

        CapturingRegularPaymentsView capturingRegularPaymentsView = new CapturingRegularPaymentsView();
        BudgetCategory emptyCategory = new Category("regular",Amount.fromPence(1000));

        RegularPaymentsPresenter presenter = new RegularPaymentsPresenter(capturingRegularPaymentsView, emptyCategory);

        String expectedDesc = "expectedDesc";
        Date expectedDate = TestHelpers.anyDate;
        Amount expectedAmount = Amount.fromPence(0);

        capturingRegularPaymentsView.invokeItemAdded(expectedDesc, expectedDate, expectedAmount);

        assertThat(emptyCategory.getOutgoingCount(), is(1));

        Expense addedOutgoing = emptyCategory.getOutgoing(0);
        assertThat(addedOutgoing.getAmount().asPence(), is(expectedAmount.asPence()));
        assertThat(addedOutgoing.getDate(), is(expectedDate));
        assertThat(addedOutgoing.getDescription(), is (expectedDesc));

    }



    private class RegularPaymentsPresenter implements RegularPaymentsView.ItemAddedCallback {
        private RegularPaymentsView regularPaymentsView;
        private BudgetCategory regularPaymentsCategory;

        public RegularPaymentsPresenter(RegularPaymentsView regularPaymentsView, BudgetCategory category) {
            this.regularPaymentsView = regularPaymentsView;
            regularPaymentsCategory = category;

            regularPaymentsView.setOutgoings(category.getOutgoings());
            regularPaymentsView.addItemAddedCallback(this);
        }

        @Override
        public void itemAdded() {

            String description = regularPaymentsView.getDescription();
            Amount amount = regularPaymentsView.getAmount();
            Date dateAdded = Utils.anyDate();


            regularPaymentsCategory.addOutgoing(new OutgoingExpense(description,dateAdded, amount));

        }
    }

    private class CapturingRegularPaymentsView implements RegularPaymentsView {

        private List<Expense> regularPayments = new ArrayList<>();
        private ArrayList<ItemAddedCallback> itemAddedCallbacks = new ArrayList<>();
        private String expectedDesc;
        private Date anyDate;
        private Amount amount;


        @Override
        public List<Expense> getRegularPayments() {
            return regularPayments;
        }

        @Override
        public void setOutgoings(List<Expense> outgoings) {
            regularPayments = outgoings;
        }

        @Override
        public void addItemAddedCallback(ItemAddedCallback itemAddedCallback) {
            itemAddedCallbacks.add(itemAddedCallback);
        }

        @Override
        public String getDescription() {
            return expectedDesc;
        }

        @Override
        public Amount getAmount() {
            return amount;
        }

        public void invokeItemAdded(String expectedDesc, Date anyDate, Amount amount) {
            this.expectedDesc = expectedDesc;
            this.anyDate = anyDate;
            this.amount = amount;
            for (ItemAddedCallback callback:itemAddedCallbacks) {
                callback.itemAdded();
            }
        }
    }

    interface RegularPaymentsView {


        List<Expense> getRegularPayments();

        void setOutgoings(List<Expense> outgoings);

        interface ItemAddedCallback {
            void itemAdded();
        }

        void addItemAddedCallback(ItemAddedCallback itemAddedCallback);

        String getDescription();
        Amount getAmount();
    }
}
