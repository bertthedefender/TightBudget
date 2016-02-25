package evos.tightbudget;

import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.BudgetCategory;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.OutgoingExpense;
import evos.tightbudget.presenter.CategoryFragmentPresenter;
import evos.tightbudget.view.CategoryFragment;


public class CategoryFragmentTests extends ActivityInstrumentationTestCase2<TestFragmentHost> {


    private static final Amount EXPECTED_CURRENT_POSITION = Amount.fromPence(250);
    private BudgetCategory category;
    private String EXPECTED_CATEGORY_NAME = "category name";
    private Amount EXPECTED_BUDGET_AMOUNT = Amount.fromPence(500);


    public CategoryFragmentTests() {
        super(TestFragmentHost.class);
    }


    public void setup() {
        category = new Category(EXPECTED_CATEGORY_NAME, EXPECTED_BUDGET_AMOUNT);
        category.addOutgoing(new OutgoingExpense("Expense A", TestHelpers.anyDate, EXPECTED_CURRENT_POSITION));
    }

    public void testThatCategoryFragmentHasItsDataItemsSet() {

        setup();

        CategoryFragment categoryFragment = new CategoryFragment();

        CategoryFragmentPresenter presenter = new CategoryFragmentPresenter(categoryFragment, category );

        getActivity().addFragment(categoryFragment);

        presenter.bind();

        getInstrumentation().waitForIdleSync();

        TextView categoryName = (TextView)categoryFragment.getView().findViewById(R.id.category_fragment_name);
        TextView categoryOutgoing = (TextView)categoryFragment.getView().findViewById(R.id.category_fragment_spent);
        RecyclerView outgoingsList = (RecyclerView)categoryFragment.getView().findViewById(R.id.category_fragment_outgoings);

        String expectedName = EXPECTED_CATEGORY_NAME;
        String expectedOutgoing = String.valueOf(EXPECTED_CURRENT_POSITION.asPence());

        assertEquals(expectedName, categoryName.getText());
        assertEquals(expectedOutgoing, categoryOutgoing.getText());
        assertTrue(outgoingsList.getAdapter().getItemCount() == category.getOutgoingCount());

    }
}
