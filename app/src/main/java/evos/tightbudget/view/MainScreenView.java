package evos.tightbudget.view;

import java.util.List;

/**
 * Created by mcdons20 on 19/02/16.
 */
public interface MainScreenView {
    void setCategoryViews(List<CategoryFragmentView> categoryFragmentViews);
    void setTotalBudgetText(String budgetText);

    void addNewOutgoingClickedCallback(NewOutgoingClickedCallback newOutgoingClickedCallback);

    void showNewOutgoingDialog(NewOutgoingExpenseView outgoingExpenseView);

    interface NewOutgoingClickedCallback {
        void addNewOutgoingClicked(String selectedCategory);
    }
}
