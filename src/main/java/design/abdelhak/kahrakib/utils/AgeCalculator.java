package design.abdelhak.kahrakib.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AgeCalculator {

    public static int avecDateNaissance(Date dateNaissance){
        LocalDate dateNow = LocalDate.now();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateNaissance);

        int yearDob = calendar.get(Calendar.YEAR);
        int monthDob = calendar.get(Calendar.MONTH) + 1;
        int dayDob = calendar.get(Calendar.DAY_OF_MONTH);

        int yearNow = dateNow.getYear();
        int monthNow = dateNow.getMonthValue();
        int dayNow = dateNow.getDayOfMonth();


        int age = yearNow - yearDob;

        //filter age
        if ( monthDob > monthNow ){
            age--;
        }
        if ( (monthDob == monthNow) && dayDob > dayNow){
            age--;
        }

        return age;
    }
}
