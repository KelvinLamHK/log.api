package plus.log.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class StringUtil {

    private StringUtil(){}
    public static Date stringToDate (String dateString, String format) throws ParseException {
        var formatter = new SimpleDateFormat(format);
        return formatter.parse(dateString);
    }

    public static List<String> stringToStringListByDelimiter (String str, String delimiter){
        return new ArrayList<>(Arrays.asList(str.split(delimiter)));
    }
}
