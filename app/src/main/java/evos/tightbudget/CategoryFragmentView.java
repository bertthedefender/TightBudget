package evos.tightbudget;

import evos.tightbudget.model.Amount;

/**
 * Created by S on 15/02/2016.
 */
public interface CategoryFragmentView {
    void setCurrentPosition(String categoryName, Amount currentSpend, Amount budgetTotal);
}
