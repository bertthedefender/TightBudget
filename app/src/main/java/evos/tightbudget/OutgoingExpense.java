package evos.tightbudget;


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
            jsonObject.put("Date", date.getTime());
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

             String description = jsonObject.getString("Description");
             Date date = new Date();
             date.setTime(jsonObject.getLong("Date"));
             Amount amount = Amount.fromPence(jsonObject.getInt("Amount"));

             expense = new OutgoingExpense(description, date, amount);
        } catch (JSONException e) {
            return null;
        }

        return expense;
    }
}
