package evos.tightbudget;

import android.test.ActivityInstrumentationTestCase2;
import android.view.ViewGroup;


import evos.tightbudget.model.Amount;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.DataPump;
import evos.tightbudget.model.OutgoingExpense;
import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.model.Utils;
import evos.tightbudget.view.MainScreen;

/**
 * Created by S on 22/02/2016.
 */
public class MainScreenTests extends ActivityInstrumentationTestCase2<MainScreen> {

    public MainScreenTests() {
        super(MainScreen.class);

        TightBudgetModel model = new TightBudgetModel(Amount.fromPence(1000));

        Category category1 = new Category("Category 1", Amount.fromPence(500));
        Category category2 = new Category("Category 2", Amount.fromPence(1000));

        category1.addOutgoing(new OutgoingExpense("Expense 1_1", Utils.getDate(2001, 01, 01), Amount.fromPence(50)));

        category2.addOutgoing(new OutgoingExpense("Expense 2_1", Utils.getDate(2001, 01, 30), Amount.fromPence(10)));
        category2.addOutgoing(new OutgoingExpense("Expense 2_2", Utils.getDate(2001, 01, 21), Amount.fromPence(30)));

        model.addCategory(category1);
        model.addCategory(category2);

        DataPump.setModel(model);

    }

    public void test_whenTheScreenIsBound_itHasTheCorrectNumberOfCategoryFragmentsSet()  {

        getInstrumentation().waitForIdleSync();

        MainScreen mainScreen = getActivity();


        assertEquals(((ViewGroup) mainScreen.findViewById(R.id.main_categoryContainer)).getChildCount(),2);
    }


}
