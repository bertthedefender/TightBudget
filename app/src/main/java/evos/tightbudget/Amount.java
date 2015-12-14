package evos.tightbudget;

/**
 * Created by mcdons20 on 14/12/15.
 */
public class Amount {
    private int amountInPence;

    private Amount(int amountInPence) {
        this.amountInPence = amountInPence;
    }

    public int asPence() {
        return amountInPence;
    }

    public static Amount fromPence(int amountInPence) {
        return new Amount(amountInPence);
    }
}
