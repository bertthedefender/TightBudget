package evos.tightbudget;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import evos.tightbudget.model.Amount;
import evos.tightbudget.model.Category;
import evos.tightbudget.model.OutgoingExpense;
import evos.tightbudget.model.TightBudgetModel;
import evos.tightbudget.presenter.MainScreenPresenter;
import evos.tightbudget.view.CategoryFragmentView;
import evos.tightbudget.view.MainScreenView;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mcdons20 on 12/02/16.
 */
public class MainScreenPresenterTest {



    private TightBudgetModel model;

    @Before
    public void setup() {
        model = new TightBudgetModel();

        Category category1 = new Category("Category 1", Amount.fromPence(500));
        Category category2 = new Category("Category 2", Amount.fromPence(1000));

        category1.addOutgoing(new OutgoingExpense("Expense 1_1", TestHelpers.anyDate, Amount.fromPence(50)));

        category2.addOutgoing(new OutgoingExpense("Expense 2_1", TestHelpers.anyDate, Amount.fromPence(10)));
        category2.addOutgoing(new OutgoingExpense("Expense 2_2", TestHelpers.anyDate, Amount.fromPence(30)));

        model.addCategory(category1);
        model.addCategory(category2);

    }

    @Test
    public void whenAModelIsProvided_thePresenterSetsUpTheScreenAsExpected() {

        CapturingMainView capturingMainView = new CapturingMainView();

        MainScreenPresenter presenter = new MainScreenPresenter(capturingMainView, model);

        presenter.bind();
        assertThat(capturingMainView.categories.size(), is (2));

    }


    class CapturingMainView implements MainScreenView {

        List<CategoryFragmentView> categories = new ArrayList<>();

        @Override
        public void addCategoryView(CategoryFragmentView categoryFragmentView) {
            categories.add(categoryFragmentView);
        }
    }

}
