package evos.tightbudget.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
    private ViewPager viewPager;
    private CategoryPagerAdapter categoryPagerAdapter;
    private List<CategoryFragmentView> categoryFragmentViews;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        totalBudget = (TextView)findViewById(R.id.main_totalBudget);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        presenter = new MainScreenPresenter(this, TightBudgetApplication.model);
        presenter.bind();
    }


    private class CategoryPagerAdapter extends FragmentStatePagerAdapter {

        private List<CategoryFragmentView> categoryFragments;

        public CategoryPagerAdapter(FragmentManager fm, List<CategoryFragmentView> categoryFragments) {
            super(fm);
            this.categoryFragments = categoryFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return (CategoryFragment)categoryFragments.get(position);
        }

        @Override
        public int getCount() {
            return categoryFragments.size();
        }
    }

}
