package evos.tightbudget;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mcdons20 on 14/12/15.
 */
public class Utils {
    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
