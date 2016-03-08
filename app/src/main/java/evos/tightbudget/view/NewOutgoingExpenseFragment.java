package evos.tightbudget.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import evos.tightbudget.R;

/**
 * Created by S on 03/03/2016.
 */
public class NewOutgoingExpenseFragment extends DialogFragment implements NewOutgoingExpenseView {
    private ArrayList<Callback> callbacks = new ArrayList<>();
    private EditText description;
    private EditText amount;
    private EditText date;
    private FrameLayout dateTouchCapturer;


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
        try {
            return SimpleDateFormat.getDateInstance().parse(date.getText().toString());
        } catch (ParseException e) {
            Toast.makeText(getContext(),"Unrecognised date",Toast.LENGTH_SHORT).show();
        }
        return Calendar.getInstance().getTime();
    }

    @Override
    public int getOutgoingAmount() {
        return Integer.valueOf(amount.getText().toString());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = inflater.inflate(R.layout.new_outgoing_dialog, null);

        description = (EditText)view.findViewById(R.id.newoutgoing_description);
        amount = (EditText)view.findViewById(R.id.newoutgoing_amount);
        date = (EditText)view.findViewById(R.id.newoutgoing_date);
        date.setText(SimpleDateFormat.getDateInstance().format(Calendar.getInstance().getTime()));

        dateTouchCapturer = (FrameLayout)view.findViewById(R.id.newoutgoing_touchCapturingFrame);

        dateTouchCapturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
                try {
                    datePickerDialogFragment.setInitialDate(SimpleDateFormat.getInstance().parse(date.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                datePickerDialogFragment.setCallback(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar c = Calendar.getInstance();
                        c.set(year,monthOfYear,dayOfMonth);
                        date.setText(SimpleDateFormat.getDateInstance().format(c.getTime()));
                    }
                });
                datePickerDialogFragment.show(getFragmentManager(), null);
            }
        });



        builder.setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        for (Callback callback : NewOutgoingExpenseFragment.this.callbacks) {
                            callback.addClicked();
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NewOutgoingExpenseFragment.this.getDialog().cancel();
                    }
                })
                .setTitle("Add new Outgoing");

        return builder.create();

    }


}
