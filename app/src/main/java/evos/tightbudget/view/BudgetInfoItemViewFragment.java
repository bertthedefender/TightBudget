package evos.tightbudget.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import evos.tightbudget.R;
import evos.tightbudget.model.Amount;

/**
 * Created by mcdons20 on 12/02/16.
 */
public class BudgetInfoItemViewFragment extends Fragment implements BudgetInfoView {

    TextView title;
    TextView amount;

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void setAmount(Amount amount) {
        this.amount.setText(amount.asPence());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.budgetinfo_item, container, false);
        title = (TextView)v.findViewById(R.id.budgetInfoTitle);
        amount = (TextView)v.findViewById(R.id.budgetInfoAmount);

        return v;
    }
}
