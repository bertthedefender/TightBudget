package evos.tightbudget;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by mcdons20 on 16/02/16.
 */
public class TestFragmentHost extends FragmentActivity {

    public void addFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().add(fragment, null).commit();

    }


}
