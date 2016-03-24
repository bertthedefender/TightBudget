package evos.tightbudget.presenter;

import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.view.NewCategoryView;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public class NewCategoryPresenter {
    private NewCategoryView newCategoryView;

    public NewCategoryPresenter(NewCategoryView newCategoryView, TightBudgetModel model) {
        this.newCategoryView = newCategoryView;

        newCategoryView.setCategories(model.categories);

    }

    public NewCategoryView getView() {
        return newCategoryView;
    }
}
