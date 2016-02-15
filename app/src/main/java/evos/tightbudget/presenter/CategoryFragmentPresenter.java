package evos.tightbudget.presenter;

import android.support.v7.widget.RecyclerView;

import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.view.CategoryFragmentView;

/**
 * Created by S on 15/02/2016.
 */
public class CategoryFragmentPresenter {
    private final CategoryFragmentView categoryFragmentView;
    private final BudgetCategory category;

    public CategoryFragmentPresenter(CategoryFragmentView categoryFragmentView, BudgetCategory category) {

        this.categoryFragmentView = categoryFragmentView;
        this.category = category;
    }

    public void bind() {

        categoryFragmentView.setCurrentPosition(category.getName(), category.getTotalSpend(), category.getBudget());

        categoryFragmentView.setExpenseData(category.getOutgoings());

    }
}
