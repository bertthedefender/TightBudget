package evos.tightbudget.view;

import java.util.List;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.Expense;

/**
 * Created by mcdons20 on 10/03/16.
 */
public interface RegularPaymentsView {


    List<Expense> getRegularPayments();

    void setOutgoings(List<Expense> outgoings);

    interface ItemAddedCallback {
        void itemAdded();
    }

    void addItemAddedCallback(ItemAddedCallback itemAddedCallback);

    String getDescription();

    Amount getAmount();
}
