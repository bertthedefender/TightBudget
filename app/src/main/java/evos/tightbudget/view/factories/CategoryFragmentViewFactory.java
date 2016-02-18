package evos.tightbudget.view.factories;

import evos.tightbudget.view.CategoryFragment;
import evos.tightbudget.view.CategoryFragmentView;

/**
 * Created by mcdons20 on 18/02/16.
 */
public class CategoryFragmentViewFactory {
    public static CategoryFragmentView create() {
        return new CategoryFragment();
    }
}
