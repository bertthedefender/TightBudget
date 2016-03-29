package evos.tightbudget.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

import evos.tightbudget.R;
import evos.tightbudget.model.BudgetCategory;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public class NewCategoryFragment extends DialogFragment implements NewCategoryView {
    @Override
    public void setCategories(Map<String, BudgetCategory> categories) {
        int x=0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_manage_categories,null);

        return v;


    }
}
