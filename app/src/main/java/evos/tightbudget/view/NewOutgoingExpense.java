package evos.tightbudget.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

import evos.tightbudget.R;
import evos.tightbudget.model.Utils;

/**
 * Created by S on 03/03/2016.
 */
public class NewOutgoingExpense extends DialogFragment implements NewOutgoingExpenseView {
    private ArrayList<Callback> callbacks = new ArrayList<>();
    private EditText description;
    private EditText amount;
    private Button addButton;

    @Override
    public void addCallback(Callback callback) {
        this.callbacks.add(callback);
    }

    @Override
    public String getOutgoingDescription() {
        return description.getText().toString();
    }

    @Override
    public Date getOutgoingDate() {
        return Utils.getDate(2001,01,01);
    }

    @Override
    public int getOutgoingAmount() {
        return Integer.valueOf(amount.getText().toString());
    }
    
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dlg_newoutgoing, null))
                // Add action buttons
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NewOutgoingExpense.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }
}
