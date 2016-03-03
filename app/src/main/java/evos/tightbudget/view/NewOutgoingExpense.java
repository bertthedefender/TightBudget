package evos.tightbudget.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dlg_newoutgoing, container, false);

        description = (EditText)view.findViewById(R.id.newoutgoing_description);
        amount = (EditText)view.findViewById(R.id.newoutgoing_amount);

        addButton = (Button)view.findViewById(R.id.newoutgoing_btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Callback callback:callbacks) {
                    callback.addOutgoing();
                }
            }
        });

        getDialog().setTitle("Add new Outgoing");

        return view;
    }
}
