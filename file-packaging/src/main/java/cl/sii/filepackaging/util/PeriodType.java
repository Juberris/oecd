package cl.sii.filepackaging.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class PeriodType {
    public static final Integer[] MONTHS_SEMIMONTHLY = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    public static final Integer[] DAY_SEMIMONTHLY = new Integer[]{15, 31};
    public static final Integer[] MONTHS_BIYEARLY = new Integer[]{6, 12};
    public static final Integer[] MONTHS_QUARTERLY = new Integer[]{3, 6, 9, 12};
    public static final Integer[] MONTHS_BIMONTHLY = new Integer[]{2, 4, 6, 8, 10, 12};

    public PeriodType() {
    }

    public static String getBiyearly(String pattern) {
        return getDueDate(MONTHS_BIYEARLY, (Integer[])null, pattern);
    }

    public static String getSemimonthly(String pattern) {
        Integer dueDay = getDay(pattern);
        Integer firstDay;
        Integer lastDay;
        if (dueDay < 15) {
            firstDay = 1;
            lastDay = 15;
        } else {
            dueDay = dueDay > 31 ? 31 : dueDay;
            lastDay = dueDay <= 1 ? 15 : dueDay;
            firstDay = dueDay - 15 < 1 ? 1 : dueDay - 15;
        }

        return getDueDate(MONTHS_SEMIMONTHLY, new Integer[]{firstDay, lastDay}, pattern);
    }

    public static String getQuarterly(String pattern) {
        return getDueDate(MONTHS_QUARTERLY, (Integer[])null, pattern);
    }

    public static String getBimonthly(String pattern) {
        return getDueDate(MONTHS_BIMONTHLY, (Integer[])null, pattern);
    }

    public static PeriodValidation isValidPeriod(String period, String periodType) {
        Integer expectedPeriodLength = 4;
        if (!periodType.equalsIgnoreCase(PeriodType.Type.BIMONTHLY.name()) && !periodType.equalsIgnoreCase(PeriodType.Type.QUARTERLY.name()) && !periodType.equalsIgnoreCase(PeriodType.Type.BIYEARLY.name()) && !periodType.equalsIgnoreCase(PeriodType.Type.MONTHLY.name())) {
            if (periodType.equalsIgnoreCase(PeriodType.Type.SEMIMONTHLY.name()) || periodType.equalsIgnoreCase(PeriodType.Type.DAILY.name())) {
                expectedPeriodLength = 8;
            }
        } else {
            expectedPeriodLength = 6;
        }

        if (period != null && period.length() == expectedPeriodLength) {
            Integer year = Integer.valueOf(period.substring(0, 4));
            if (year < 1900) {
                return new PeriodValidation(periodType, period, false, "Año es menor a 1900");
            } else {
                Integer month = 1;
                Integer lastDayMonth;
                if (expectedPeriodLength == 6) {
                    lastDayMonth = getPeriodMonthsForType(periodType);
                    month = Integer.valueOf(period.substring(4, 6));
                    if (month < 1 || month > lastDayMonth) {
                        return new PeriodValidation(periodType, period, false, "Mes de periodo " + month + " debe estar entre 1 y " + lastDayMonth);
                    }
                }

                if (expectedPeriodLength == 8) {
                    lastDayMonth = getLastDayMonth(year, month);
                    Integer day = Integer.valueOf(period.substring(6, 8));
                    if (periodType.equalsIgnoreCase(PeriodType.Type.SEMIMONTHLY.name()) && day != 1 && day != 2) {
                        return new PeriodValidation(periodType, period, false, "Dia de periodo debe ser 1 o 2");
                    }

                    if (day < 1 || day > lastDayMonth) {
                        return new PeriodValidation(periodType, period, false, "Dia de periodo debe estar entre 1 y " + lastDayMonth);
                    }
                }

                return new PeriodValidation(periodType, period, true, (String)null);
            }
        } else {
            return new PeriodValidation(periodType, period, false, "Largo no corresponde a " + expectedPeriodLength);
        }
    }

    public static List<Integer> getPeriodsFrom(String periodType, String mask, Date fromLimit) {
        List<Integer> list = null;
        if (periodType.equalsIgnoreCase(PeriodType.Type.BIYEARLY.name())) {
            list = getUnusualPeriods(MONTHS_BIYEARLY, periodType, mask, fromLimit);
        } else if (periodType.equalsIgnoreCase(PeriodType.Type.BIMONTHLY.name())) {
            list = getUnusualPeriods(MONTHS_BIMONTHLY, periodType, mask, fromLimit);
        } else if (periodType.equalsIgnoreCase(PeriodType.Type.SEMIMONTHLY.name())) {
            list = getUnusualPeriodsSEMIMONTHLY(periodType, mask, fromLimit);
        } else if (periodType.equalsIgnoreCase(PeriodType.Type.QUARTERLY.name())) {
            list = getUnusualPeriods(MONTHS_QUARTERLY, periodType, mask, fromLimit);
        } else if (periodType.equalsIgnoreCase(PeriodType.Type.YEARLY.name())) {
            list = getYearlyPeriods(periodType, mask, fromLimit);
        } else if (periodType.equalsIgnoreCase(PeriodType.Type.MONTHLY.name())) {
            list = getMonthlyPeriods(periodType, mask, fromLimit);
        } else if (periodType.equalsIgnoreCase(PeriodType.Type.DAILY.name())) {
            list = getDailyPeriods(periodType, mask, fromLimit);
        }

        Collections.sort(list);
        return list;
    }

    public static List<Integer> getUnusualPeriodsSEMIMONTHLY(String periodType, String mask, Date fromLimit) {
        Integer dateMask = Integer.valueOf(DateTime.calcDueDate(mask, periodType));
        Integer dateFromLimit = Integer.valueOf((new SimpleDateFormat("yyyyMMdd")).format(fromLimit));
        List<Integer> periodsForm = null;
        if (dateMask > dateFromLimit) {
            periodsForm = new ArrayList();
            Integer todayYear = Calendar.getInstance().get(1);
            Integer todayMonth = Calendar.getInstance().get(2) + 1;
            Integer today = Integer.valueOf((new SimpleDateFormat("yyyyMMdd")).format(new Date()));
            Integer yearEnd = Integer.valueOf((new SimpleDateFormat("yyyy")).format(fromLimit));
            Integer dueDay = getDay(mask);
            Integer firstDay;
            Integer lastDay;
            if (dueDay < 15) {
                firstDay = 1;
                lastDay = 15;
            } else {
                dueDay = dueDay > 31 ? 31 : dueDay;
                lastDay = dueDay <= 1 ? 15 : dueDay;
                firstDay = dueDay - 15 < 1 ? 1 : dueDay - 15;
            }

            Integer[] daySemimonthly = new Integer[]{firstDay, lastDay};
            Integer indexDayPeriod = 0;

            for(int indexPeriodFor = 0; indexPeriodFor <= daySemimonthly.length - 1; ++indexPeriodFor) {
                Integer todayFor = Calendar.getInstance().get(5);
                if (todayFor <= daySemimonthly[indexPeriodFor]) {
                    indexDayPeriod = indexPeriodFor;
                    break;
                }
            }

            Integer monthPeriod = todayMonth;

            for(int yearFor = todayYear; yearFor >= yearEnd; --yearFor) {
                for(int monthFor = monthPeriod; monthFor >= 0; --monthFor) {
                    for(int dayFor = indexDayPeriod; dayFor >= 0; --dayFor) {
                        int maxDay = getLastDayMonth(yearFor, monthFor);
                        if (daySemimonthly[dayFor] > maxDay) {
                            daySemimonthly[dayFor] = maxDay;
                        }

                        int dateFor = Integer.valueOf(yearFor + "" + addZero(monthFor + 1) + "" + addZero(daySemimonthly[dayFor]));
                        if (dateFor >= dateFromLimit && dateFor < today) {
                            StringBuilder dateYMP = new StringBuilder();
                            dateYMP.append(yearFor);
                            dateYMP.append(addZero(monthFor + 1));
                            dateYMP.append(addZero(dayFor + 1));
                            periodsForm.add(Integer.valueOf(dateYMP.toString()));
                        }
                    }

                    indexDayPeriod = daySemimonthly.length - 1;
                }

                monthPeriod = 11;
            }
        }

        return periodsForm;
    }

    public static List<Integer> getUnusualPeriods(Integer[] months, String periodType, String mask, Date fromLimit) {
        Integer dateMask = Integer.valueOf(DateTime.calcDueDate(mask, periodType));
        Integer dateFromLimit = Integer.valueOf((new SimpleDateFormat("yyyyMMdd")).format(fromLimit));
        List<Integer> periodsForm = null;
        if (dateMask > dateFromLimit) {
            periodsForm = new ArrayList();
            Integer todayYear = Calendar.getInstance().get(1);
            Integer todayMonth = Calendar.getInstance().get(2) + 1;
            Integer today = Integer.valueOf((new SimpleDateFormat("yyyyMMdd")).format(new Date()));
            String dayMask = dateMask.toString().substring(6);
            Integer yearEnd = Integer.valueOf((new SimpleDateFormat("yyyy")).format(fromLimit));
            Integer indexCurrentPeriod = 0;

            int yearFor;
            for(yearFor = 0; yearFor <= months.length - 1; ++yearFor) {
                if (todayMonth <= months[yearFor]) {
                    indexCurrentPeriod = yearFor;
                    break;
                }
            }

            for(yearFor = todayYear; yearFor >= yearEnd; --yearFor) {
                for(int indexPeriodFor = indexCurrentPeriod; indexPeriodFor >= 0; --indexPeriodFor) {
                    int dateFor = Integer.valueOf(yearFor + "" + addZero(months[indexPeriodFor]) + "" + dayMask);
                    if (dateFor >= dateFromLimit && dateFor < today) {
                        StringBuilder dateYP = new StringBuilder();
                        dateYP.append(yearFor);
                        dateYP.append(addZero(indexPeriodFor + 1));
                        periodsForm.add(Integer.valueOf(dateYP.toString()));
                    }
                }

                indexCurrentPeriod = months.length - 1;
            }
        }

        return periodsForm;
    }

    public static List<Integer> getMonthlyPeriods(String periodType, String mask, Date fromLimit) {
        Integer dateMask = Integer.valueOf(DateTime.calcDueDate(mask, periodType));
        Integer dateFromLimit = Integer.valueOf((new SimpleDateFormat("yyyyMMdd")).format(fromLimit));
        List<Integer> periodsForm = null;
        if (dateMask > dateFromLimit) {
            periodsForm = new ArrayList();
            Integer todayYear = Calendar.getInstance().get(1);
            Integer todayMonth = Calendar.getInstance().get(2);
            Integer today = Integer.valueOf((new SimpleDateFormat("yyyyMMdd")).format(new Date()));
            String dayMask = dateMask.toString().substring(6);
            Integer yearEnd = Integer.valueOf((new SimpleDateFormat("yyyy")).format(fromLimit));
            Integer monthPeriod = todayMonth;

            for(int yearFor = todayYear; yearFor >= yearEnd; --yearFor) {
                for(int monthFor = monthPeriod; monthFor >= 0; --monthFor) {
                    int dateFor = Integer.valueOf(yearFor + "" + addZero(monthFor + 1) + "" + dayMask);
                    if (dateFor >= dateFromLimit && dateFor < today) {
                        StringBuilder dateYM = new StringBuilder();
                        dateYM.append(yearFor);
                        dateYM.append(addZero(monthFor + 1));
                        periodsForm.add(Integer.valueOf(dateYM.toString()));
                    }
                }

                monthPeriod = 11;
            }
        }

        return periodsForm;
    }

    public static List<Integer> getYearlyPeriods(String periodType, String mask, Date fromLimit) {
        Integer dateMask = Integer.valueOf(DateTime.calcDueDate(mask, periodType));
        Integer dateFromLimit = Integer.valueOf((new SimpleDateFormat("yyyyMMdd")).format(fromLimit));
        List<Integer> periodsForm = null;
        if (dateMask > dateFromLimit) {
            periodsForm = new ArrayList();
            Integer todayYear = Calendar.getInstance().get(1);
            String dayMonthMask = dateMask.toString().substring(4);
            Integer today = Integer.valueOf((new SimpleDateFormat("yyyyMMdd")).format(new Date()));
            Integer yearEnd = Integer.valueOf((new SimpleDateFormat("yyyy")).format(fromLimit));

            for(int yearFor = todayYear; yearFor >= yearEnd; --yearFor) {
                int dateFor = Integer.valueOf(yearFor + "" + dayMonthMask);
                if (dateFor >= dateFromLimit && dateFor < today) {
                    periodsForm.add(yearFor);
                }
            }
        }

        return periodsForm;
    }

    private static List<Integer> getDailyPeriods(String periodType, String mask, Date fromLimit) {
        Integer dateMask = Integer.valueOf(DateTime.calcDueDate(mask, periodType));
        Integer dateFromLimit = Integer.valueOf((new SimpleDateFormat("yyyyMMdd")).format(fromLimit));
        List<Integer> periodsForm = null;
        if (dateMask > dateFromLimit) {
            periodsForm = new ArrayList();
            Integer todayYear = Calendar.getInstance().get(1);
            Integer todayMonth = Calendar.getInstance().get(2);
            Integer yearEnd = Integer.valueOf((new SimpleDateFormat("yyyy")).format(fromLimit));
            Integer monthEnd = Integer.valueOf((new SimpleDateFormat("yyyyMM")).format(fromLimit));
            Integer dayEnd = Integer.valueOf((new SimpleDateFormat("yyyyMMdd")).format(fromLimit));
            Integer monthPeriod = todayMonth;

            for(int yearFor = todayYear; yearFor >= yearEnd; --yearFor) {
                for(int monthFor = monthPeriod; monthFor >= 0; --monthFor) {
                    StringBuilder dateYM = new StringBuilder();
                    dateYM.append(yearFor);
                    dateYM.append(addZero(monthFor + 1));
                    int ym = Integer.valueOf(dateYM.toString());
                    if (ym < monthEnd) {
                        break;
                    }

                    for(int dayFor = getLastDayMonth(yearFor, monthFor); dayFor >= 1; --dayFor) {
                        StringBuilder dateYMD = new StringBuilder();
                        dateYMD.append(yearFor);
                        dateYMD.append(addZero(monthFor + 1));
                        dateYMD.append(addZero(dayFor));
                        int ymd = Integer.valueOf(dateYMD.toString());
                        if (ymd >= dayEnd) {
                            periodsForm.add(Integer.valueOf(dateYMD.toString()));
                        }
                    }
                }

                monthPeriod = 11;
            }
        }

        return periodsForm;
    }

    private static String getDueDate(Integer[] months, Integer[] days, String pattern) {
        Integer dayPattern = days != null ? null : getDay(pattern);
        Integer month = Calendar.getInstance().get(2) + 1;
        Integer toDay = Calendar.getInstance().get(5);
        Integer currentYear = Calendar.getInstance().get(1);
        Integer lenghtPeriods = days != null ? months.length * days.length : months.length;
        Period[] periods = new Period[lenghtPeriods];
        Integer index = 0;
        Integer[] var10 = months;
        int var11 = months.length;

        for(int var12 = 0; var12 < var11; ++var12) {
            Integer monthFor = var10[var12];
            if (days != null) {
                Integer firstDay = days[0];
                Integer lastDay = days[days.length - 1];
                Integer[] var16 = days;
                int var17 = days.length;

                for(int var18 = 0; var18 < var17; ++var18) {
                    Integer day = var16[var18];
                    periods[index] = new Period();
                    periods[index].year = currentYear;
                    periods[index].month = monthFor;
                    periods[index].day = day;
                    periods[index].setPeriod();
                    if (day == firstDay) {
                        periods[index].previousDay = lastDay;
                    } else if (day == lastDay) {
                        periods[index].afterDay = firstDay;
                    }

                    index = index + 1;
                }
            } else {
                periods[index] = new Period();
                periods[index].year = currentYear;
                periods[index].month = monthFor;
                periods[index].day = dayPattern;
                periods[index].previousDay = dayPattern;
                periods[index].afterDay = dayPattern;
                periods[index].setPeriod();
                index = index + 1;
            }
        }

        Integer currentDate = Integer.valueOf(currentYear + "" + addZero(month) + "" + addZero(toDay));
        return getDueDate(periods, months, dayPattern, currentYear, currentDate);
    }

    private static String getDueDate(Period[] periods, Integer[] months, Integer dueDay, Integer year, Integer currentDate) {
        Integer index = 0;
        Period[] var6 = periods;
        int var7 = periods.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            Period periodFor = var6[var8];
            Integer period = Integer.valueOf(periodFor.period);
            Integer lastPeriod = getRelativePeriod(periods, months, year, index, periodFor, true);
            Integer afterPeriod = getRelativePeriod(periods, months, year, index, periodFor, false);
            if (currentDate > lastPeriod && currentDate <= period) {
                return period.toString();
            }

            if (currentDate > period && currentDate <= afterPeriod) {
                return afterPeriod.toString();
            }

            index = index + 1;
        }

        return null;
    }

    private static Integer getRelativePeriod(Period[] periods, Integer[] months, Integer currentYear, Integer index, Period periodFor, Boolean previous) {
        Integer relativePeriod = null;
        Integer time = previous ? -1 : 1;
        String date;
        Integer year;
        if (index == 0 && previous) {
            year = previous ? currentYear - 1 : currentYear;
            date = year + "" + addZero(months[months.length - 1]) + "" + addZero(periodFor.previousDay);
        } else if (index == periods.length - 1) {
            year = periodFor.isOtherYear(months[0], periodFor.afterDay) ? currentYear + 1 : currentYear;
            date = year + "" + addZero(months[0]) + "" + addZero(periodFor.afterDay);
        } else {
            date = periods[index + time].period;
        }

        relativePeriod = Integer.valueOf(date);
        return relativePeriod;
    }

    private static Integer getDay(String date) {
        return date != null && date.length() == 8 ? Integer.valueOf(date.substring(date.length() - 2)) : 1;
    }

    private static String addZero(Integer num) {
        StringBuilder vreturn = new StringBuilder();
        if (num < 10) {
            vreturn.append("0");
        }

        vreturn.append(num);
        return vreturn.toString();
    }

    public static Date getDateFromLimit(String month) {
        try {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.add(2, -Integer.valueOf(month));
            return calendar.getTime();
        } catch (Exception var2) {
            return new Date();
        }
    }

    public static int getLastDayMonth(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month - 1, 1);
        return calendar.getActualMaximum(5);
    }

    private static int getPeriodMonthsForType(String periodType) {
        Integer periods = 12;
        if (periodType.equalsIgnoreCase(PeriodType.Type.BIYEARLY.name())) {
            periods = 2;
        } else if (periodType.equalsIgnoreCase(PeriodType.Type.BIMONTHLY.name())) {
            periods = 6;
        } else if (periodType.equalsIgnoreCase(PeriodType.Type.QUARTERLY.name())) {
            periods = 4;
        }

        return periods;
    }

    private static class Period {
        public Integer year;
        public Integer month;
        public Integer day;
        public Integer previousDay;
        public Integer afterDay;
        public String period;

        private Period() {
        }

        public void setPeriod() {
            this.evaluateMaxDayMonth();
            this.period = this.year + "" + PeriodType.addZero(this.month) + "" + PeriodType.addZero(this.day);
        }

        private void evaluateMaxDayMonth() {
            Calendar calendar = new GregorianCalendar(this.year, this.month - 1, 1);
            Integer lastDay = calendar.getActualMaximum(5);
            if (this.day > lastDay) {
                this.day = lastDay;
            }

        }

        public Boolean isOtherYear(Integer month, Integer day) {
            Integer monthDayParam = Integer.valueOf(month + "" + day);
            Integer monthDayThis = Integer.valueOf(this.month + "" + this.day);
            return monthDayThis < monthDayParam ? true : false;
        }

        public String toString() {
            return this.period;
        }
    }

    public static class PeriodValidation {
        public final String periodType;
        public final String period;
        public final boolean valid;
        public final String reason;

        public PeriodValidation(String periodType, String period, boolean valid, String reason) {
            this.periodType = periodType;
            this.period = period;
            this.valid = valid;
            this.reason = periodType + ":" + period + ". " + reason;
        }
    }

    public static enum Type {
        YEARLY("yyyy1231", "yyyy", "Anual"),
        MONTHLY("yyyyMM31", "yyyyMM", "Mensual"),
        DAILY("yyyyMMdd", "yyyyMMdd", "Diario"),
        BIYEARLY("yyyyMM31", "yyyypp", "Semestral"),
        BIMONTHLY("yyyyMM31", "yyyypp", "Bimestral"),
        QUARTERLY("yyyyMM31", "yyyypp", "Trimestral"),
        SEMIMONTHLY("yyyyMM31", "yyyyMMpp", "Quincenal");

        private String pattern;
        private String format;
        private String description;

        private Type(String pattern, String format, String description) {
            this.pattern = pattern;
            this.format = format;
            this.description = description;
        }

        public String getPattern() {
            return this.pattern;
        }

        public String getFormat() {
            return this.format;
        }

        public int getId() {
            return this.ordinal() + 1;
        }

        public String getDescription() {
            return this.description;
        }
    }
}
