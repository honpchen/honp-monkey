package ink.honp.core.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具类，扩展了 {@link DateUtils}
 * @author jeff chen
 * @since 1.0.0
 */
public abstract class DateUtil extends DateUtils  {

    /**
     * 格式化时间
     * @param date          时间
     * @param formatPattern 格式
     * @return 输出指定格式的时间字符串
     */
    public static String format(final Date date, final String formatPattern) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);

        return dateFormat.format(date);
    }


    /**
     * 格式化时间
     * @param date          时间
     * @param formatPattern 格式
     * @return 输出指定格式的时间字符串
     */
    public static String format(final LocalDateTime date, final String formatPattern) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        return date.format(formatter);
    }
}
