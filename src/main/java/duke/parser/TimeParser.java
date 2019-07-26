package duke.parser;

import duke.commons.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

public class TimeParser {
    private static final int TEN = 10;
    private static final int NINETEEN = 19;

    public static String parseDateToString(Date date) {
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
    }

    public static Date parseStoredStringToDate(String line) {
        try {
            return new SimpleDateFormat("dd' of' MMM yyyy',' hh:mm aaa").parse(line.replaceAll("st|nd|rd|th", ""));
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseStringToDate(String line) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy HHmm").parse(line);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String parseStringToDay(String line) throws DukeException {
         line = capitalizeFirstLetter(line);
         try {
             TemporalAccessor accessor = DateTimeFormatter.ofPattern("E").parse(line);
             return DayOfWeek.from(accessor).toString();
         } catch (DateTimeParseException e1) {
             try {
                 TemporalAccessor accessor = DateTimeFormatter.ofPattern("EEEE").parse(line);
                 return DayOfWeek.from(accessor).toString();
             } catch (DateTimeParseException e2) {
                 throw new DukeException("Please enter days in following format: Monday/Mon, Tuesday/Tue, ... as fields.");
             }
         }
    }

    private static String capitalizeFirstLetter(String line) {
        return line.substring(0, 1).toUpperCase() + line.substring(1).toLowerCase();
    }

}
