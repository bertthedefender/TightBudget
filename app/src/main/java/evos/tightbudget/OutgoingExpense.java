package evos.tightbudget;


import java.util.Date;

/**
 * Created by mcdons20 on 14/12/15.
 */
public class OutgoingExpense implements Outgoing {
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
}
