package evos.tightbudget.view;

import java.util.Date;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public interface NewOutgoingExpenseView {

    void addCallback(Callback callback);

    interface Callback {
        void addClicked();
    }

    String getOutgoingDescription();

    Date getOutgoingDate();

    int getOutgoingAmount();
}
