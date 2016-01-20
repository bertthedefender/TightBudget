package evos.tightbudget;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by mcdons20 on 14/12/15.
 */
public class OutgoingExpense implements Expense {
    private final String description;
    private final Date date;
    private final Amount amount;

    public OutgoingExpense(String description, Date date, Amount amount) {
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public Amount getAmount() {
        return this.amount;
    }

    @Override
    public String asJson() {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("Description",description);
            jsonObject.put("Date", date);
            jsonObject.put("Amount", amount.asPence());
        } catch (JSONException e) {
            return null;
        }

        return jsonObject.toString();
    }

    public static Expense fromJson(String json) {
        JSONObject jsonObject;
        Expense expense;
        try {
             jsonObject = new JSONObject(json);
             expense = new OutgoingExpense(jsonObject.getString("Description"), Utils.getDate(2000,10,10),Amount.fromPence(jsonObject.getInt("Amount")));
        } catch (JSONException e) {
            return null;
        }

        return expense;
    }
}
