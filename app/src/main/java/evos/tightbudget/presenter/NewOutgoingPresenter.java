package evos.tightbudget.presenter;

import java.util.ArrayList;
import java.util.Date;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.Expense;
import evos.tightbudget.model.OutgoingExpense;
import evos.tightbudget.view.NewOutgoingExpenseView;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public class NewOutgoingPresenter implements NewOutgoingExpenseView.Callback {
    private NewOutgoingExpenseView newOutgoingExpenseView;
    private BudgetCategory category;
    private ArrayList<NewOutgoingAddedCallback> newOutgoingAddedCallbacks = new ArrayList<>();

    public NewOutgoingPresenter(NewOutgoingExpenseView newOutgoingExpenseView, BudgetCategory category) {
        this.newOutgoingExpenseView = newOutgoingExpenseView;
        this.category = category;
        newOutgoingExpenseView.addCallback(this);
    }

    @Override
    public void addClicked() {

        String newOutgoingDescription = newOutgoingExpenseView.getOutgoingDescription();
        Date newOutgoingDate = newOutgoingExpenseView.getOutgoingDate();
        Amount newOutgoingAmount = Amount.fromPence(newOutgoingExpenseView.getOutgoingAmount());

        Expense expense = new OutgoingExpense(newOutgoingDescription, newOutgoingDate, newOutgoingAmount);

        category.addOutgoing(expense);

        for (NewOutgoingAddedCallback callback:newOutgoingAddedCallbacks) {
            callback.newOutgoingAdded();
        }
    }

    public NewOutgoingExpenseView getView() {
        return newOutgoingExpenseView;
    }

    public void addNewOutgoingAddedCallback(NewOutgoingAddedCallback callback) {
        newOutgoingAddedCallbacks.add(callback);
    }

    public interface NewOutgoingAddedCallback {
        void newOutgoingAdded();
    }
}
