package evos.tightbudget.presenter;

import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.view.CategoryFragmentView;
import evos.tightbudget.view.MainScreenView;

/**
 * Created by S on 15/02/2016.
 */
public class CategoryFragmentPresenter implements BudgetCategory.OutgoingExpenseAddedCallback{
    private final CategoryFragmentView categoryFragmentView;
    private final BudgetCategory category;

    public CategoryFragmentPresenter(CategoryFragmentView categoryFragmentView, BudgetCategory category) {

        this.categoryFragmentView = categoryFragmentView;
        this.category = category;

        this.category.addOutgoingExpenseAddedCallback(this);
        categoryFragmentView.setCurrentPosition(category.getName(), category.getTotalSpend(), category.getBudget());
        categoryFragmentView.setOutgoingExpenseData(category.getOutgoings());
    }

    public CategoryFragmentView getView() {
        return categoryFragmentView;
    }

    @Override
    public void invoke(String categoryName) {
        categoryFragmentView.dataUpdated();
    }
}
