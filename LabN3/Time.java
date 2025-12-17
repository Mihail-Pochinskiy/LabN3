class Time {
    public int hours;
    public int minutes;
    // Храним количество часов и минут

    public Time(String input) {
        int i = 0;
        int read=0;
        while (input.charAt(i) != ':') {
            read = read*10+(input.charAt(i)-'0');
            i++;
        }
        hours = read;
        i++;
        read = 0;
        while (i<input.length()) {
            read = read*10+(input.charAt(i)-'0');
            i++;
        }
        minutes = read;
    }

    public Time(int hours, int minutes) {
        this.hours=hours;
        this.minutes=minutes;
    }

    // Сравниваем два времени
    public static boolean isTimeLess(Time time1, Time time2) {
        if (time1.hours<time2.hours)
            return true;
        if (time1.hours>time2.hours)
            return false;
        return time1.minutes<time2.minutes;
    }

    // Сравниваем два времени нестрогим сравнением
    public static boolean isTimeNotStrongLess(Time time1, Time time2) {
        return isTimeLess(time1, time2) || time1.isEqual(time2);
    }

    // Проверяем, находится ли время в указанном промежутке
    public boolean isTimeBetween(Time time1, Time time2) {
        return isTimeNotStrongLess(time1, this) && isTimeNotStrongLess(this, time2);
    }

    // Проверяем, одинаковое ли время
    public boolean isEqual(Time time) {
        return hours==time.hours && minutes==time.minutes;
    }

    // Находим сумму времени
    public static Time plus(Time time1, Time time2) {
        int minutes = time1.minutes+time2.minutes;
        int hours = time1.hours+time2.hours;
        if (minutes>=60) {
            minutes-=60;
            hours++;
        }
        if (hours>=24)
            hours-=24;
        return new Time(hours, minutes);
    }
}
