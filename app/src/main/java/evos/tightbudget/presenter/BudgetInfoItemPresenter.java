package evos.tightbudget.presenter;

import evos.tightbudget.model.BudgetInfoItemModel;
import evos.tightbudget.view.BudgetInfoView;

/**
 * Created by mcdons20 on 12/02/16.
 */
public class BudgetInfoItemPresenter {
    private final BudgetInfoView view;
    private final BudgetInfoItemModel budgetInfoItemModel;

    public BudgetInfoItemPresenter(BudgetInfoView view, BudgetInfoItemModel budgetInfoItemModel) {
        this.view = view;
        this.budgetInfoItemModel = budgetInfoItemModel;
    }

    public void bind() {
        view.setTitle(budgetInfoItemModel.title);
        view.setAmount(budgetInfoItemModel.description);
    }
}
