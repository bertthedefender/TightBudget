package evos.tightbudget;

import android.app.Application;
import android.content.Intent;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.DataPump;
import evos.tightbudget.model.OutgoingExpense;
import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.model.Utils;
import evos.tightbudget.view.MainScreen;

/**
 * Created by mcdons20 on 18/02/16.
 */
public class TightBudgetApplication extends Application{

    public static TightBudgetModel model = new TightBudgetModel(Amount.fromPence(1000));


    @Override
    public void onCreate() {
                super.onCreate();


        //TODO: Setup datamodel here


        Category category1 = new Category("Category 1", Amount.fromPence(500));
        Category category2 = new Category("Category 2", Amount.fromPence(1000));

        category1.addOutgoing(new OutgoingExpense("Expense 1_1", Utils.getDate(2001, 01, 01), Amount.fromPence(50)));

        category2.addOutgoing(new OutgoingExpense("Expense 2_1", Utils.getDate(2001, 01, 30), Amount.fromPence(10)));
        category2.addOutgoing(new OutgoingExpense("Expense 2_2", Utils.getDate(2001, 01, 21), Amount.fromPence(30)));

        model.addCategory(category1);
        model.addCategory(category2);

        DataPump.setModel(model);

    }
}
