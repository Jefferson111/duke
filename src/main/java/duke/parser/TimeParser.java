package duke.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeParser {
    private static final int TEN = 10;
    private static final int NINETEEN = 19;

    public String parseStringToDate(String line) {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(line);
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            int day = calender.get(Calendar.DATE);
            if (!((day > TEN) && (day < NINETEEN))) {
                switch (day % TEN) {
                    case 1:
                        return new SimpleDateFormat("dd'st of' MMM yyyy',' hh:mm aaa").format(date);
                    case 2:
                        return new SimpleDateFormat("dd'nd of' MMM yyyy',' hh:mm aaa").format(date);
                    case 3:
                        return new SimpleDateFormat("dd'rd of' MMM yyyy',' hh:mm aaa").format(date);
                }
            }
            return new SimpleDateFormat("dd'th of' MMM yyyy',' hh:mm aaa").format(date);
        } catch (ParseException e) {
            return line;
        }
    }

}
