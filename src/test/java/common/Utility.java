package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utility {
    private static final  String  dateFormat ="yyyy-MM-dd";
    public static String getFormattedDate(){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = Calendar.getInstance().getTime();
        return  sdf.format(date);
    }

    public static boolean isFutureDate(String inputDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = Calendar.getInstance().getTime();
        Date dateInput = sdf.parse(inputDate);
        return dateInput.compareTo(date) > 0;
    }

    public static boolean isValidDate(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try{
            sdf.parse(inputDate);
            return true;
        } catch (ParseException e){
            return false;
        }

    }
}
