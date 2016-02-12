package evos.tightbudget;

import org.junit.Test;

import evos.tightbudget.model.BudgetInfoItemModel;
import evos.tightbudget.presenter.BudgetInfoItemPresenter;
import evos.tightbudget.view.BudgetInfoView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by mcdons20 on 12/02/16.
 */
public class BudgetInfoItemPresenterTest {

    class CapturingBudgetInfoView implements BudgetInfoView {

        public String titleSet;
        public String descriptionSet;

        @Override
        public void setTitle(String title) {

            this.titleSet = title;
        }

        @Override
        public void setDescription(String description) {

            descriptionSet = description;
        }
    }


    @Test
    public void whenABudgetInfoItemPresenter_HasBudgetInfoSetOnIt_ItTellsTheViewAboutTheData()
    {
        String expectedTitle = "expectedTitle";
        String expectedDescription = "expectedDescription";

        CapturingBudgetInfoView capturingBudgetInfoView = new CapturingBudgetInfoView();

        BudgetInfoItemModel budgetInfoItemModel = new BudgetInfoItemModel(expectedTitle,expectedDescription);

        BudgetInfoItemPresenter budgetInfoItemPresenter = new BudgetInfoItemPresenter(capturingBudgetInfoView, budgetInfoItemModel);

        budgetInfoItemPresenter.bind();

        assertThat(capturingBudgetInfoView.titleSet, is(expectedTitle));
        assertThat(capturingBudgetInfoView.descriptionSet, is(expectedDescription));
    }

}
