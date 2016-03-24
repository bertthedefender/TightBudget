package evos.tightbudget.view;

import java.util.Map;

import evos.tightbudget.model.BudgetCategory;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public interface NewCategoryView {

    void setCategories(Map<String, BudgetCategory> categories);
}
