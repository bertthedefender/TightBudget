package evos.tightbudget.view;

import java.util.List;

import evos.tightbudget.model.BudgetCategory;

/**
 * Created by mcdons20 on 19/02/16.
 */
public interface MainScreenView {
    void setCategoryViews(List<CategoryFragmentView> categoryFragmentViews);
    void setTotalBudgetText(String budgetText);

    void addCallback(Callback callback);

    void showNewOutgoingDialog(String category);

    interface Callback {
        void addNewOutgoing(String selectedCategory);
    }
}
