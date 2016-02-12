package evos.tightbudget.model;

/**
 * Created by mcdons20 on 12/02/16.
 */
public class BudgetInfoItemModel {

    public final String title;
    public final Amount description;

    public BudgetInfoItemModel(String title, Amount description) {
        this.title = title;
        this.description = description;
    }
}
