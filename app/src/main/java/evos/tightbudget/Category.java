package evos.tightbudget;

/**
 * Created by mcdons20 on 14/12/15.
 */
class Category implements BudgetCategory {

    private String categoryName;

    public Category(String categoryName) {

        this.categoryName = categoryName;
    }

    @Override
    public String getName() {
        return this.categoryName;
    }
}
