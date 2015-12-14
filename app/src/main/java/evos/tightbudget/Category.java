package evos.tightbudget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcdons20 on 14/12/15.
 */
class Category implements BudgetCategory {

    private String categoryName;
    private Amount budgetAmount;
    private List<Outgoing> outgoings;

    public Category(String categoryName, Amount budgetAmount) {
        outgoings = new ArrayList<>();

        this.categoryName = categoryName;
        this.budgetAmount = budgetAmount;
    }

    @Override
    public String getName() {
        return this.categoryName;
    }

    @Override
    public Amount getBudget() {
        return this.budgetAmount;
    }

    @Override
    public void addOutgoing(Outgoing outgoing) {
        this.outgoings.add(outgoing);
    }

    @Override
    public int getOutgoingCount() {
        return outgoings.size();
    }

    @Override
    public Outgoing getOutgoing(int index) {
        return outgoings.get(index);
    }
}
