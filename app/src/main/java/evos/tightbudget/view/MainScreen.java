package evos.tightbudget.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import evos.tightbudget.R;
import evos.tightbudget.TightBudgetApplication;
import evos.tightbudget.presenter.MainScreenPresenter;


/**
 * Created by mcdons20 on 19/02/16.
 */
public class MainScreen extends FragmentActivity implements MainScreenView {

    MainScreenPresenter presenter;


    private ArrayList<CategoryFragment> categoryViews = new ArrayList<>();
    private TextView totalBudget;
    private LinearLayout categoryContainer;

    @Override
    public void addCategoryView(CategoryFragmentView categoryFragmentView) {
        categoryViews.add((CategoryFragment)categoryFragmentView);
        getSupportFragmentManager().beginTransaction().add(R.id.main_categoryContainer,(CategoryFragment)categoryFragmentView,null).commit();
    }

    @Override
    public void setTotalBudgetText(String budgetText) {
        totalBudget.setText(budgetText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        totalBudget = (TextView)findViewById(R.id.main_totalBudget);
        categoryContainer = (LinearLayout)findViewById(R.id.main_categoryContainer);

        presenter = new MainScreenPresenter(this, TightBudgetApplication.model);
        presenter.bind();
    }
}
