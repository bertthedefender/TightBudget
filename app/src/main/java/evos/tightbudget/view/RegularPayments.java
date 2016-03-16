package evos.tightbudget.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import evos.tightbudget.R;
import evos.tightbudget.model.Amount;
import evos.tightbudget.model.Expense;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public class RegularPayments extends DialogFragment implements RegularPaymentsView {
    private ArrayList<ItemAddedCallback> itemAddedCallbacks = new ArrayList<>();

    @Override
    public List<Expense> getRegularPayments() {
        return null;
    }

    @Override
    public void setOutgoings(List<Expense> outgoings) {

    }

    @Override
    public void addItemAddedCallback(ItemAddedCallback itemAddedCallback) {
        this.itemAddedCallbacks.add(itemAddedCallback);
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Amount getAmount() {
        return null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View view = getLayoutInflater(savedInstanceState).inflate(R.layout.fragment_regular_payments,null);

        return builder.setView(view)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDialog().cancel();
                    }
                })
                .create();

    }
}
