package evos.tightbudget.presenter;

import java.util.ArrayList;
import java.util.List;

import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.view.CategoryFragmentView;
import evos.tightbudget.view.MainScreenView;
import evos.tightbudget.view.factories.NewCategoryViewFactory;
import evos.tightbudget.view.factories.CategoryFragmentViewFactory;
import evos.tightbudget.view.factories.NewOutgoingExpenseViewFactory;

/**
 * Created by mcdons20 on 19/02/16.
 */
public class MainScreenPresenter {
    private final MainScreenView mainScreenView;
    private final TightBudgetModel model;
    private ArrayList<CategoryFragmentPresenter> categoryPresenters = new ArrayList<>();

    public MainScreenPresenter(final MainScreenView mainScreenView, TightBudgetModel model) {
        this.mainScreenView = mainScreenView;
        this.model = model;

        mainScreenView.addNewOutgoingClickedCallback(new MainScreenView.NewOutgoingClickedCallback() {
            @Override
            public void addNewOutgoingClicked(final String selectedCategory) {
                NewOutgoingPresenter newOutgoingPresenter = new NewOutgoingPresenter(NewOutgoingExpenseViewFactory.create(), MainScreenPresenter.this.model.getCategory(selectedCategory));
                mainScreenView.showNewOutgoingDialog(newOutgoingPresenter.getView());
            }
        });

        mainScreenView.addManageCategoriesClickedCallback(new MainScreenView.ManageCategoriesClickedCallback() {
            @Override
            public void manageCategoriesClicked() {
                NewCategoryPresenter newCategoryPresenter = new NewCategoryPresenter(NewCategoryViewFactory.create(), MainScreenPresenter.this.model);
                mainScreenView.showManageCategoriesDialog(newCategoryPresenter.getView());
            }
        });


        for (String name : model.categories.keySet()) {
            CategoryFragmentPresenter categoryFragmentPresenter = new CategoryFragmentPresenter(CategoryFragmentViewFactory.create(), model.categories.get(name));
            this.addCategoryPresenter(categoryFragmentPresenter);
        }


    }

    private void addCategoryPresenter(CategoryFragmentPresenter categoryFragmentPresenter) {
        this.categoryPresenters.add(categoryFragmentPresenter);
    }

    public void bind() {

        List<CategoryFragmentView> categoryViews = new ArrayList<>();

        for (CategoryFragmentPresenter categoryFragmentPresenter:categoryPresenters) {
            categoryViews.add(categoryFragmentPresenter.getView());
        }

        mainScreenView.setCategoryViews(categoryViews);
        mainScreenView.setTotalBudgetText(String.valueOf(model.budgetAmount.asPence()));

    }
}
