package evos.tightbudget.view.factories;

import evos.tightbudget.view.NewOutgoingExpenseFragment;
import evos.tightbudget.view.NewOutgoingExpenseView;

/**
 * Created by S on 03/03/2016.
 */
public class NewOutgoingExpenseViewFactory {
    public static NewOutgoingExpenseView create() {
        return new NewOutgoingExpenseFragment();
    }
}
