package cl.sii.filepackaging.util;


import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import cl.sii.filepackaging.util.PeriodType.Type;

public class DateTime {
    public static final String TIMESTAMP_SIMPLE_FORMAT = "yyyyMMddHHmmssSSS";
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String TIMESTAMP_ss_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String TIMESTAMP_TBL_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String TIMESTAMP_Z_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DAY_FORMAT = "yyyyMMdd";
    private static final String[] FORMATS = new String[]{"yyyyMMdd'T'HHmmssSSS'Z'", "yyyyMMdd'T'HHmmss'Z'", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd", "yyyy"};

    public DateTime() {
    }

    public static void main(String[] args) {
        System.out.println(bgnOfDayUTC(1, 1, 2018));
    }

    public static String genNowSimpleTimestamp() {
        return (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(new Date());
    }

    public static String genNowTimestamp() {
        return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).format(new Date());
    }

    public static String genNowSSTimestamp() {
        return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")).format(new Date());
    }

    public static String genXsdTimestamp() {
        return (new SimpleDateFormat("")).format(new Date());
    }

    public static Date getToday() {
        return new Date();
    }

    public static synchronized Date getUniqueToday() {
        try {
            Thread.sleep(15L);
        } catch (Exception var1) {
        }

        return new Date();
    }

    public static Date add(Date d, TimeUnit tu, int q) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(14, (int)tu.toMillis((long)q));
        return c.getTime();
    }

    public static String addSeconds(String timestamp, int s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        try {
            Date date = dateFormat.parse(timestamp);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(13, s);
            return dateFormat.format(calendar.getTime());
        } catch (Exception var5) {
            Exception e = var5;
            e.printStackTrace();
            return null;
        }
    }

    public static String calcDueDate(String dueDateFormat, String periodType) {
        if (periodType.equalsIgnoreCase(Type.BIYEARLY.name())) {
            return PeriodType.getBiyearly(dueDateFormat);
        } else if (periodType.equalsIgnoreCase(Type.BIMONTHLY.name())) {
            return PeriodType.getBimonthly(dueDateFormat);
        } else if (periodType.equalsIgnoreCase(Type.SEMIMONTHLY.name())) {
            return PeriodType.getSemimonthly(dueDateFormat);
        } else {
            return periodType.equalsIgnoreCase(Type.QUARTERLY.name()) ? PeriodType.getQuarterly(dueDateFormat) : calcDueDate(Calendar.getInstance().get(1), 0, 0, dueDateFormat);
        }
    }

    public static String calcDueDate(int year, int month, int day, String dueDateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        int today = Integer.parseInt(sdf.format(cal.getTime()));
        String yyyy = dueDateFormat.substring(0, 4);
        String mm = dueDateFormat.substring(4, 6);
        String dd = dueDateFormat.substring(6, 8);
        boolean calcYear = "yyyy".equals(yyyy) && year == 0;
        boolean calcMonth = "MM".equals(mm) && month == 0;
        boolean calcDay = "dd".equals(dd) && day == 0;
        if (!calcYear) {
            cal.set(1, year > 0 ? year : Integer.parseInt(yyyy));
        }

        if (!calcMonth) {
            cal.set(2, month > 0 ? month - 1 : Integer.parseInt(mm) - 1);
        }

        int dueDate;
        if (!calcDay) {
            dueDate = cal.getActualMaximum(5);
            if ((day > 0 ? day : Integer.parseInt(dd)) > dueDate) {
                cal.set(5, dueDate);
            } else {
                cal.set(5, day > 0 ? day : Integer.parseInt(dd));
            }
        }

        dueDate = Integer.parseInt(sdf.format(cal.getTime()));
        if (dueDate < today) {
            if (calcYear && calcMonth && calcDay) {
                cal.add(5, 1);
            } else if (calcYear && calcMonth) {
                cal.add(2, 1);
            } else if (calcYear) {
                cal.add(1, 1);
            }

            dueDate = Integer.parseInt(sdf.format(cal.getTime()));
        }

        return String.valueOf(dueDate);
    }

    public static String utc(String format, Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(d);
    }

    public static String utc(Date d) {
        return utc("yyyyMMdd'T'HHmmssSSS'Z'", d);
    }

    public static String utcNoMs(Date d) {
        return utc("yyyyMMdd'T'HHmmss'Z'", d);
    }

    public static String utc() {
        return utc(new Date());
    }

    public static String utcXsd() {
        return utc("yyyy-MM-dd'T'HH:mm:ss'Z'", new Date());
    }

    public static String year(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            return sdf.format(date);
        } else {
            return null;
        }
    }

    public static String formatPretty(String ts) throws Exception {
        return formatPretty(utc2Date(ts));
    }

    public static String formatPretty(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            return sdf.format(date);
        } else {
            return null;
        }
    }

    public static String formatUTCPretty(Date date) {
        return formatUTCPretty(date, false);
    }

    public static String formatUTCPretty(Date date, boolean noMs) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss" + (noMs ? "" : ".SSS") + "'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf.format(date);
        } else {
            return null;
        }
    }

    public static String formatUTC(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmssSSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf.format(date);
        } else {
            return null;
        }
    }

    public static String formatUTC2(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf.format(date);
        } else {
            return null;
        }
    }

    public static Date utc2Date(String utc) throws Exception {
        if (utc == null) {
            return null;
        } else {
            Exception ex = null;
            String[] var2 = FORMATS;
            int var3 = var2.length;
            int var4 = 0;

            while(var4 < var3) {
                String format = var2[var4];
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                try {
                    Date date = sdf.parse(utc);
                    return date;
                } catch (Exception var8) {
                    Exception e = var8;
                    ex = e;
                    ++var4;
                }
            }

            throw (new Exception("Error obteniendo objeto fecha de string [" + utc + "]"));
        }
    }

    public static Date bgnOfYearUTC() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        return bgnOfDayUTC(1, 1, cal.get(1));
    }

    public static Date bgnOfDayUTC(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(14, 0);
        cal.set(5, day);
        cal.set(5, day);
        cal.set(2, month - 1);
        cal.set(1, year);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        return cal.getTime();
    }

    public static XMLGregorianCalendar date2XmlDate(String date) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
        } catch (Exception var2) {
            Exception e = var2;
            throw new RuntimeException("Error convirtiendo date [" + date + "] a xml date", e);
        }
    }

    public static XMLGregorianCalendar yearXmlDate(Integer year) {
        if (year == null) {
            return null;
        } else {
            new GregorianCalendar();

            try {
                XMLGregorianCalendar xmlcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(String.valueOf(year));
                return xmlcal;
            } catch (Exception var3) {
                Exception e = var3;
                throw new RuntimeException("Error convirtiendo date [" + year + "] a xml date", e);
            }
        }
    }

    public static XMLGregorianCalendar date2UtcXmlDate(String date) throws Exception {
        return date2UtcXmlDate(utc2Date(date));
    }

    public static XMLGregorianCalendar date2UtcXmlDate(Date date) {
        if (date != null) {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(date);

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                XMLGregorianCalendar xmlcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateFormat.format(date));
                xmlcal.setTimezone(0);
                return xmlcal;
            } catch (Exception var4) {
                Exception e = var4;
                throw new RuntimeException("Error convirtiendo date [" + date + "] a xml date", e);
            }
        } else {
            return null;
        }
    }

    public static Long utc2Time(String utc) throws Exception {
        return utc != null ? utc2Date(utc).getTime() : null;
    }


}

