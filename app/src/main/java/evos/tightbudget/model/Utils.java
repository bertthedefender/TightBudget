package evos.tightbudget.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by mcdons20 on 14/12/15.
 */
public class Utils {
    public static Date getDate(int year, int month, int day) {

        Calendar calendar = new GregorianCalendar(year, month-1, day);
        return calendar.getTime();
    }
}
