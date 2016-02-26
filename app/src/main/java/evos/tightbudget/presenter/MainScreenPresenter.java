package evos.tightbudget.presenter;

import java.util.ArrayList;

import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.view.MainScreenView;
import evos.tightbudget.view.factories.CategoryFragmentViewFactory;

/**
 * Created by mcdons20 on 19/02/16.
 */
public class MainScreenPresenter {
    private final MainScreenView mainScreenView;
    private final TightBudgetModel model;
    private ArrayList<CategoryFragmentPresenter> categoryPresenters = new ArrayList<>();

    public MainScreenPresenter(MainScreenView mainScreenView, TightBudgetModel model) {
        this.mainScreenView = mainScreenView;
        this.model = model;

        for (String name : model.categories.keySet()) {
            CategoryFragmentPresenter categoryFragmentPresenter = new CategoryFragmentPresenter(CategoryFragmentViewFactory.create(), model.categories.get(name));
            this.addCategoryPresenter(categoryFragmentPresenter);
            categoryFragmentPresenter.bind();
        }
    }

    private void addCategoryPresenter(CategoryFragmentPresenter categoryFragmentPresenter) {
        this.categoryPresenters.add(categoryFragmentPresenter);
    }

    public void bind() {

        for (CategoryFragmentPresenter categoryFragmentPresenter:categoryPresenters) {
                mainScreenView.addCategoryView(categoryFragmentPresenter.getView());
        }

        mainScreenView.setTotalBudgetText(String.valueOf(model.budgetAmount.asPence()));
        mainScreenView.refresh();

    }
}
