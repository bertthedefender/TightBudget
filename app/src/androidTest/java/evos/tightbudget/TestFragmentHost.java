package evos.tightbudget;


import android.app.Activity;
import android.app.Fragment;

/**
 * Created by mcdons20 on 16/02/16.
 */
public class TestFragmentHost extends Activity {


    public void addFragment(android.app.Fragment fragment) {

        getFragmentManager().beginTransaction().add(fragment, null).commit();

    }


}
