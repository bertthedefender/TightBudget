package evos.tightbudget;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by mcdons20 on 14/12/15.
 */
public class ModelTests {


    interface BudgetCategory {
        String getName();
    }

    class Category implements BudgetCategory {

        private String categoryName;

        public Category(String categoryName) {

            this.categoryName = categoryName;
        }

        @Override
        public String getName() {
            return this.categoryName;
        }
    }

    private class TightBudgetModel {

        private Map<String, BudgetCategory> categories;

        public TightBudgetModel() {
            categories = new HashMap<>();
        }

        public void addCategory(BudgetCategory category) {
            categories.put(category.getName(), category);
        }

        public BudgetCategory getCategory(String expectedName) {
            return categories.get(expectedName);
        }
    }

    @Test
    public void givenACategoryIsAddedToTheListOfCategories_theListWillContainTheCategory() {

        BudgetCategory category = new Category("ExpectedName");

        TightBudgetModel model = new TightBudgetModel();

        model.addCategory(category);

        BudgetCategory expected = model.getCategory("ExpectedName");

        assertThat(expected.getName(), is("ExpectedName"));

    }
}
