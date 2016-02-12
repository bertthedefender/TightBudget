package evos.tightbudget;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by mcdons20 on 12/02/16.
 */
public class BudgetInfoItemPresenterTest {

    interface BudgetInfoView {
        void setTitle(String title);
        void setDescription(String description);
    }

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

    class BudgetInfoItemModel {

        public final String title;
        public final String description;

        public BudgetInfoItemModel(String title, String description) {
            this.title = title;
            this.description = description;
        }
    }

    private class BudgetInfoItemPresenter {
        private final BudgetInfoView view;
        private final BudgetInfoItemModel budgetInfoItemModel;

        public BudgetInfoItemPresenter(BudgetInfoView view, BudgetInfoItemModel budgetInfoItemModel) {
            this.view = view;
            this.budgetInfoItemModel = budgetInfoItemModel;
        }
        
        public void bind() {
            view.setTitle(budgetInfoItemModel.title);
            view.setDescription(budgetInfoItemModel.description);
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
