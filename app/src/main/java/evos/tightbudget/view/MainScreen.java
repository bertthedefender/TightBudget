package evos.tightbudget.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.telecom.Call;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import evos.tightbudget.R;
import evos.tightbudget.TightBudgetApplication;
import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.Category;
import evos.tightbudget.presenter.MainScreenPresenter;


/**
 * Created by mcdons20 on 19/02/16.
 */
public class MainScreen extends FragmentActivity implements MainScreenView {

    MainScreenPresenter presenter;

    private ArrayList<CategoryFragment> categoryViews = new ArrayList<>();

    private FloatingActionButton addNewOutgoing;
    private TextView totalBudget;
    private ViewPager viewPager;
    private CategoryPagerAdapter categoryPagerAdapter;
    private List<CategoryFragmentView> categoryFragmentViews;
    private List<Callback> callbacks = new ArrayList<>();


    @Override
    public void setCategoryViews(List<CategoryFragmentView> categoryFragmentViews) {
        this.categoryFragmentViews = categoryFragmentViews;
        categoryPagerAdapter = new CategoryPagerAdapter(getSupportFragmentManager(), categoryFragmentViews);
        viewPager.setAdapter(categoryPagerAdapter);
    }

    @Override
    public void setTotalBudgetText(String budgetText) {
        totalBudget.setText(budgetText);
    }

    @Override
    public void addCallback(Callback callback) {
        callbacks.add(callback);
    }

    @Override
    public void showNewOutgoingDialog(BudgetCategory category) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        addNewOutgoing = (FloatingActionButton)findViewById(R.id.main_addNewOutgoing);
        totalBudget = (TextView)findViewById(R.id.main_totalBudget);
        viewPager = (ViewPager)findViewById(R.id.main_viewPager);

        viewPager.setOn

        presenter = new MainScreenPresenter(this, TightBudgetApplication.model);
        presenter.bind();

        addNewOutgoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CategoryPagerAdapter adapter = (CategoryPagerAdapter)viewPager.getAdapter();
                Category = adapter.categoryFragments.get(

                for (Callback callback:callbacks) {
                    callback.addNewOutgoing();
                }
            }
        });
    }


    private class CategoryPagerAdapter extends FragmentStatePagerAdapter {

        private List<CategoryFragmentView> categoryFragments;
        private int lastPosition;

        public CategoryPagerAdapter(FragmentManager fm, List<CategoryFragmentView> categoryFragments) {
            super(fm);
            this.categoryFragments = categoryFragments;
        }

        @Override
        public Fragment getItem(int position) {
            lastPosition = position;
            return (CategoryFragment)categoryFragments.get(position);
        }

        @Override
        public int getCount() {
            return categoryFragments.size();
        }
    }

}
