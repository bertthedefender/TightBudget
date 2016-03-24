package evos.tightbudget.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import evos.tightbudget.R;
import evos.tightbudget.TightBudgetApplication;
import evos.tightbudget.presenter.MainScreenPresenter;


/**
 * Created by mcdons20 on 19/02/16.
 */
public class MainScreen extends AppCompatActivity implements MainScreenView {

    MainScreenPresenter presenter;

    private ArrayList<CategoryFragment> categoryViews = new ArrayList<>();

    private FloatingActionButton addNewOutgoing;
    private TextView totalBudget;
    private ViewPager viewPager;
    private CategoryPagerAdapter categoryPagerAdapter;
    private List<NewOutgoingClickedCallback> newOutgoingClickedCallbacks = new ArrayList<>();
    private ArrayList<ManageCategoriesClickedCallback> manageCategoriesClickedCallbacks = new ArrayList<>();


    @Override
    public void setCategoryViews(List<CategoryFragmentView> categoryFragmentViews) {
        categoryPagerAdapter = new CategoryPagerAdapter(getSupportFragmentManager(), categoryFragmentViews);
        viewPager.setAdapter(categoryPagerAdapter);
    }

    @Override
    public void setTotalBudgetText(String budgetText) {
        totalBudget.setText(budgetText);
    }

    @Override
    public void addNewOutgoingClickedCallback(NewOutgoingClickedCallback newOutgoingClickedCallback) {
        newOutgoingClickedCallbacks.add(newOutgoingClickedCallback);
    }

    @Override
    public void addManageCategoriesClickedCallback(ManageCategoriesClickedCallback manageCategoriesClickedCallback) {
        manageCategoriesClickedCallbacks.add(manageCategoriesClickedCallback);
    }

    @Override
    public void showNewOutgoingDialog(NewOutgoingExpenseView view) {
        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.DialogFragment dialog = (android.support.v4.app.DialogFragment) view;
        dialog.show(fm, null);

    }

    @Override
    public void showManageCategoriesDialog(NewCategoryView categoryManagementView) {
        FragmentManager fm = getSupportFragmentManager();
        DialogFragment dialog = (DialogFragment) categoryManagementView;
        dialog.show(fm, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        menu.findItem(R.id.menu_main_manageCategories).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                for(ManageCategoriesClickedCallback callback : manageCategoriesClickedCallbacks ) {
                    callback.manageCategoriesClicked();
                }
                return true;
            }
        });

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Budgie Tap");
        actionBar.setIcon(R.drawable.actionicon);

        addNewOutgoing = (FloatingActionButton)findViewById(R.id.main_addNewOutgoing);
        totalBudget = (TextView)findViewById(R.id.main_totalBudget);
        viewPager = (ViewPager)findViewById(R.id.main_viewPager);

        presenter = new MainScreenPresenter(this, TightBudgetApplication.model);
        presenter.bind();

        addNewOutgoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CategoryPagerAdapter adapter = (CategoryPagerAdapter)viewPager.getAdapter();
                CategoryFragment selectedFragment = (CategoryFragment)adapter.categoryFragments.get(viewPager.getCurrentItem());
                for (NewOutgoingClickedCallback newOutgoingClickedCallback : newOutgoingClickedCallbacks) {
                    newOutgoingClickedCallback.addNewOutgoingClicked(selectedFragment.getCategoryName());
                }
            }
        });
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
