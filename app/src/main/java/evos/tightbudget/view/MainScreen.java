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
    private ViewPager viewPager;
    private CategoryPagerAdapter categoryPagerAdapter;

    @Override
    public void addCategoryView(CategoryFragmentView categoryFragmentView) {
        categoryViews.add((CategoryFragment)categoryFragmentView);
      // getSupportFragmentManager().beginTransaction().add(R.id.main_categoryContainer,(CategoryFragment)categoryFragmentView,null).commit();
    }

    @Override
    public void setTotalBudgetText(String budgetText) {
        totalBudget.setText(budgetText);
    }

    @Override
    public void refresh() {
        categoryPagerAdapter = new CategoryPagerAdapter(getSupportFragmentManager(), categoryViews);
        viewPager.setAdapter(categoryPagerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        totalBudget = (TextView)findViewById(R.id.main_totalBudget);
      //  categoryContainer = (LinearLayout)findViewById(R.id.main_categoryContainer);
        viewPager = (ViewPager)findViewById(R.id.viewPager);


        presenter = new MainScreenPresenter(this, TightBudgetApplication.model);
        presenter.bind();
    }


    private class CategoryPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<CategoryFragment> categoryFragments;

        public CategoryPagerAdapter(FragmentManager fm, ArrayList<CategoryFragment> categoryFragments) {
            super(fm);
            this.categoryFragments = categoryFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return categoryFragments.get(position);
        }

        @Override
        public int getCount() {
            return categoryFragments.size();
        }
    }

}
