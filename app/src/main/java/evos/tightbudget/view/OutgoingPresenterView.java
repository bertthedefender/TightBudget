package evos.tightbudget.view;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public interface OutgoingPresenterView {

    void addCallback(Callback callback);


    interface Callback {
        void addOutgoing();
    }

    String getCategoryName();

    int getCategoryBudget();
}
