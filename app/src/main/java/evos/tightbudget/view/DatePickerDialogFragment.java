package evos.tightbudget.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public class DatePickerDialogFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener callback;

    public void setCallback(DatePickerDialog.OnDateSetListener callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar currentTime = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getContext(),callback,currentTime.get(Calendar.YEAR), currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DAY_OF_MONTH));
        return dialog;
    }
}
