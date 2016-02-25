package evos.tightbudget.view;

import java.util.List;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.Expense;

/**
 * Created by S on 15/02/2016.
 */
public interface CategoryFragmentView {
    void setCurrentPosition(String categoryName, Amount currentSpend, Amount budgetTotal);
    void setOutgoingExpenseData(List<Expense> adapter);
}
