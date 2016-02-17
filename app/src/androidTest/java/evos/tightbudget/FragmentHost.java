package evos.tightbudget;


import android.app.Activity;
import android.app.Fragment;

/**
 * Created by mcdons20 on 16/02/16.
 */
public class FragmentHost extends Activity {


    public void addFragment(Fragment fragment) {

        getFragmentManager().beginTransaction().add(fragment, null).commit();

    }


}
