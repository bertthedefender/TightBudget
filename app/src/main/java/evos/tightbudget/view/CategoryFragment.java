package evos.tightbudget.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import evos.tightbudget.R;
import evos.tightbudget.model.Amount;
import evos.tightbudget.model.Expense;

/**
 * Created by mcdons20 on 17/02/16.
 */
public class CategoryFragment extends android.app.Fragment implements CategoryFragmentView {
    private TextView categoryNameView;
    private TextView categoryCurrentSpend;

    private Amount currentSpend;
    private Amount budgetTotal;
    private String categoryName;

    @Override
    public void setCurrentPosition(String categoryName, Amount currentSpend, Amount budgetTotal) {

        this.categoryName = categoryName;
        this.currentSpend = currentSpend;
        this.budgetTotal = budgetTotal;
    }

    @Override
    public void setExpenseData(List<Expense> adapter) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.category_fragment, container, false);

        categoryNameView = (TextView)view.findViewById(R.id.category_fragment_name);
        categoryCurrentSpend = (TextView)view.findViewById(R.id.category_fragment_spent);

        categoryNameView.setText(this.categoryName);
        categoryCurrentSpend.setText(String.valueOf(this.currentSpend.asPence()));

        return view;
    }
}
