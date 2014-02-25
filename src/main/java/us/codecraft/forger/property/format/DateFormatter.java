package us.codecraft.forger.property.format;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author code4crafter@gmail.com
 * @since 0.3.2
 */
public class DateFormatter implements ObjectFormatter<Date> {

    public static final String[] DEFAULT_PATTERN = new String[]{"yyyy-MM-dd HH:mm"};

    @Override
    public Date format(String raw) {
        return format(raw,DEFAULT_PATTERN);
    }

    @Override
    public Date format(String raw, String[] extra) {
        try {
            return DateUtils.parseDate(raw, extra);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Class<Date> clazz() {
        return Date.class;
    }

}
