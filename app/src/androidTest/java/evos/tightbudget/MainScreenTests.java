package evos.tightbudget;

import android.support.design.widget.FloatingActionButton;
import android.test.ActivityInstrumentationTestCase2;


import com.robotium.solo.Solo;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.DataPump;
import evos.tightbudget.model.OutgoingExpense;
import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.model.Utils;
import evos.tightbudget.view.MainScreen;
import evos.tightbudget.view.MainScreenView;

/**
 * Created by S on 22/02/2016.
 */
public class MainScreenTests extends ActivityInstrumentationTestCase2<MainScreen> {

    Solo solo;

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


    public void test_whenTheUserClicksTheAddNewOutgoingButton_theCallbacksAreNotified() throws Throwable {

        final TightBudgetModel model = DataPump.model();
        final String[] capturedIndex = new String[1];

        final MainScreen mainScreen = (MainScreen)getActivity();

        mainScreen.addCallback(new MainScreenView.Callback() {
            @Override
            public void addNewOutgoing(String selectedCategory) {
                capturedIndex[0] = selectedCategory;
            }
        });


        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                solo = new Solo(getInstrumentation(),mainScreen);
                solo.scrollViewToSide(getActivity().findViewById(R.id.main_viewPager), Solo.LEFT,0.9f);

                FloatingActionButton fabAdd = (FloatingActionButton)getActivity().findViewById(R.id.main_addNewOutgoing);
                fabAdd.callOnClick();

                assertEquals(model.getCategory("Category 1"), model.getCategory(capturedIndex[0]));
            }
        });


    }



//    public void test_whenTheScreenIsBound_itHasTheCorrectNumberOfCategoryFragmentsSet()  {
//
//        getInstrumentation().waitForIdleSync();
//
//        MainScreen mainScreen = getActivity();
//
//
//        assertEquals(((ViewGroup) mainScreen.findViewById(R.id.main_categoryContainer)).getChildCount(),2);
//    }


}
