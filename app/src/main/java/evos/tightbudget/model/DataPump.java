package evos.tightbudget.model;

/**
 * Created by S on 22/02/2016.
 */
public class DataPump {
    private static TightBudgetModel model = null;

    public static void setModel(TightBudgetModel model) {
        DataPump.model = model;
    }

    public static TightBudgetModel model() {
        if (DataPump.model != null) {
            return DataPump.model;
        } else {
            DataPump.setModel(new TightBudgetModel(Amount.fromPence(0)));
            return DataPump.model;
        }
    }
}
