package evos.tightbudget.presenter;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.view.OutgoingPresenterView;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public class NewOutgoingPresenter implements OutgoingPresenterView.Callback {
    private OutgoingPresenterView outgoingPresenterView;
    private TightBudgetModel model;

    public NewOutgoingPresenter(OutgoingPresenterView outgoingPresenterView, TightBudgetModel model) {
        this.outgoingPresenterView = outgoingPresenterView;
        this.model = model;
        outgoingPresenterView.addCallback(this);
    }

    @Override
    public void addOutgoing() {

        String newCategoryName = outgoingPresenterView.getCategoryName();
        Amount newCategoryBudget = Amount.fromPence(outgoingPresenterView.getCategoryBudget());

        BudgetCategory newCategory = new Category(newCategoryName, newCategoryBudget);

        model.addCategory(newCategory);

    }
}
