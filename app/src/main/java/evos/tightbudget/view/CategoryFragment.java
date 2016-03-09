package evos.tightbudget.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import evos.tightbudget.R;
import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.DataPump;
import evos.tightbudget.model.Expense;

/**
 * Created by mcdons20 on 17/02/16.
 */

public class CategoryFragment extends Fragment implements CategoryFragmentView {


    private class OutgoingsAdapter extends RecyclerView.Adapter {
        private List<Expense> outgoings;

        public OutgoingsAdapter(List<Expense> outgoings) {
            this.outgoings = outgoings;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater(null).inflate(R.layout.category_fragment_listitem,parent,false);
            return new OutgoingsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            Expense expense = outgoings.get(position);
            OutgoingsViewHolder view = (OutgoingsViewHolder)holder;
            view.setAmount("" + expense.getAmount().asPence());

            view.setDate(SimpleDateFormat.getDateInstance().format(expense.getDate()));
            view.setDescription(expense.getDescription());

        }

        @Override
        public int getItemCount() {
            return outgoings.size();
        }
    }

    private class OutgoingsViewHolder extends RecyclerView.ViewHolder {

        private  TextView description;
        private  TextView amount;
        private  TextView date;

        public OutgoingsViewHolder(View itemView) {
            super(itemView);
            description = (TextView)itemView.findViewById(R.id.category_fragment_item_description);
            amount = (TextView)itemView.findViewById(R.id.category_fragment_item_amount);
            date = (TextView)itemView.findViewById(R.id.category_fragment_item_date);
        }

        public void setDescription(String description) {
            this.description.setText(description);
        }

        public void setAmount(String amount) {
            this.amount.setText(amount);
        }

        public void setDate(String date) {
            this.date.setText(date);
        }
    }

    private TextView categoryNameView;
    private TextView categoryCurrentSpend;
    private TextView categoryBudget;

    private Amount currentSpend;
    private Amount budgetTotal;
    private String categoryName;
    private List<Expense> outgoings;
    private RecyclerView outgoingsRecyclerView;

    @Override
    public void setCurrentPosition(String categoryName, Amount currentSpend, Amount budgetTotal) {
        this.categoryName = categoryName;
        this.currentSpend = currentSpend;
        this.budgetTotal = budgetTotal;
    }

    @Override
    public void setOutgoingExpenseData(List<Expense> expenseList) {
        this.outgoings = expenseList;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.category_fragment, container, false);

        categoryNameView = (TextView)view.findViewById(R.id.category_fragment_name);
        categoryCurrentSpend = (TextView)view.findViewById(R.id.category_fragment_spent);
        categoryBudget = (TextView)view.findViewById(R.id.category_fragment_budget);
        outgoingsRecyclerView = (RecyclerView)view.findViewById(R.id.category_fragment_outgoings);

        outgoingsRecyclerView.setAdapter(new OutgoingsAdapter(outgoings));
        outgoingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataUpdated();

        return view;
    }


    @Override
    public void dataUpdated() {

        BudgetCategory category = DataPump.model().getCategory(this.categoryName);

        this.currentSpend = category.getTotalSpend();
        this.budgetTotal = category.getBudget();

        categoryNameView.setText(this.categoryName);
        categoryCurrentSpend.setText(String.valueOf(this.currentSpend.asPence()));
        categoryBudget.setText(String.valueOf(this.budgetTotal.asPence()));

        outgoingsRecyclerView.getAdapter().notifyDataSetChanged();

        outgoingsRecyclerView.smoothScrollToPosition(outgoingsRecyclerView.getAdapter().getItemCount()-1);
    }
}
