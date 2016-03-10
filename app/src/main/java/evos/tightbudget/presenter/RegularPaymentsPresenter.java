package evos.tightbudget.presenter;

import java.util.Date;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.OutgoingExpense;
import evos.tightbudget.model.Utils;
import evos.tightbudget.view.RegularPaymentsView;

/**
 * Created by mcdons20 on 10/03/16.
 */
public class RegularPaymentsPresenter implements RegularPaymentsView.ItemAddedCallback {
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


        regularPaymentsCategory.addOutgoing(new OutgoingExpense(description, dateAdded, amount));

    }
}
