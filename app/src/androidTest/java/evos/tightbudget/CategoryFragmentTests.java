package evos.tightbudget;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import evos.tightbudget.view.CategoryFragment;

/**
 * Created by mcdons20 on 17/02/16.
 */
public class CategoryFragmentTests extends ActivityInstrumentationTestCase2<FragmentHost> {

    public CategoryFragmentTests() {
        super(FragmentHost.class);
    }

    public void testThatCategoryFragmentHasItsDataItemsSet() {

        CategoryFragment categoryFragment = new CategoryFragment();

        ((FragmentHost)getActivity()).addFragment(categoryFragment);

        TextView categoryName = (TextView)categoryFragment.getView().findViewById(R.id.category_fragment_name);


        String expectedName = "";
        assertEquals(categoryName.getText(), expectedName);


    }
}
