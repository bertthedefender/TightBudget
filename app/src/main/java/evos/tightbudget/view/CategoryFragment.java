package evos.tightbudget.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class CategoryFragment extends Fragment implements CategoryFragmentView {
    private TextView categoryName;

    @Override
    public void setCurrentPosition(String categoryName, Amount currentSpend, Amount budgetTotal) {

    }

    @Override
    public void setExpenseData(List<Expense> adapter) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =
        inflater.inflate(R.layout.category_fragment, container, false);

        categoryName = (TextView)view.findViewById(R.id.category_fragment_name);

    }
}
