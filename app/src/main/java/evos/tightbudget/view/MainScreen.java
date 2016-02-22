package evos.tightbudget.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import evos.tightbudget.R;
import evos.tightbudget.TightBudgetApplication;
import evos.tightbudget.presenter.MainScreenPresenter;


/**
 * Created by mcdons20 on 19/02/16.
 */
public class MainScreen extends Activity implements MainScreenView {

    MainScreenPresenter presenter;


    private ArrayList<CategoryFragmentView> categoryViews = new ArrayList<>();
    private TextView totalBudget;

    @Override
    public void addCategoryView(CategoryFragmentView categoryFragmentView) {
        categoryViews.add(categoryFragmentView);
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


        presenter = new MainScreenPresenter(this, TightBudgetApplication.model);
        presenter.bind();
    }
}
