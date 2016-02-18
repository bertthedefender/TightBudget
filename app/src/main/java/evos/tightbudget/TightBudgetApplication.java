package evos.tightbudget;

import android.app.Application;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.Category;
import evos.tightbudget.presenter.CategoryFragmentPresenter;
import evos.tightbudget.view.CategoryFragment;

/**
 * Created by mcdons20 on 18/02/16.
 */
public class TightBudgetApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();



        Category category = new Category("Test", Amount.fromPence(500));

        CategoryFragmentPresenter categoryFragmentPresenter = new CategoryFragmentPresenter(new CategoryFragment(),category);
        categoryFragmentPresenter.bind();



    }
}
