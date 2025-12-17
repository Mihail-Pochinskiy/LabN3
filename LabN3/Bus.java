class Bus {
    public int number;
    public Time [] times = new Time[1440];
    // Храним номер автобуса и массив времени. Его длина равна количеству минут в сутках

    public Bus(int number, String input) {
        this.number=number;
        times[0] = new Time(input);
        for (int i=1; i<1440; i++)
            times[i]=null;
    }

    public Bus(int number, Time time) {
        this.number=number;
        times[0]=time;
        for (int i=1; i<1440; i++)
            times[i]=null;
    }

    // Переопределение вывода для класса
    @Override
    public String toString() {
        String ans = number + ": ";
        for (int i=0; i<1440 && times[i]!=null; i++) {
            if (times[i].hours>=10)
                ans+=(times[i].hours + ":");
            else
                ans+=("0" + times[i].hours + ":");

            if (times[i].minutes>=10)
                ans+=(times[i].minutes + " ");
            else
                ans+=("0" + times[i].minutes + " ");
        }
        return ans;
    }

    // Добавление времени
    public void addTime(Time newTime) {
        int i = 0; // Хотим отсортировать время по возрастанию
        while (times[i]!=null && Time.isTimeLess(times[i], newTime))
            i++;
        for (int j=1440-1; j>i; j--)
            times[j]=times[j-1];
        times[i]=newTime;
    }

    // Удаление времени
    public void deleteTime(String stringTime) {
        Time delTime = new Time(stringTime);
        int i=thereIsTime(delTime);
        if (i!=-1) {
            for (int j=i; j<1440-1; j++)
                times[j]=times[j+1];
            times[1440-1]=null;
        }
    }

    // Проверяем, не пустой ли массив времени у автобуса
    public boolean thereIsTime() {
        for (int i=0; i<1440; i++)
            if (times[i]!=null)
                return true;
        return false;
    }

    // Проверяем приедет ли автобус в указанный промежуток времени
    public boolean willWaitBus(Time comeTime, Time leaveTime) {
        boolean ans = false;
        for (int i=0; !ans && i<1440 && times[i]!=null; i++)
            ans = times[i].isTimeBetween(comeTime, leaveTime);
        return ans;
    }

    // Находим наименьшее время автобуса, не меньшее чем данное
    public Time nextTime(Time startTime) {
        int i=0;
        while (i<1440 && times[i]!=null && Time.isTimeLess(times[i], startTime))
            i++;
        if (i==1440)
            return null;
        return times[i];
    }

    // Проверяем есть ли уже у автобуса данное время
    public int thereIsTime(Time time) {
        for (int i=0; i<1440 && times[i]!=null; i++)
            if (times[i].isEqual(time))
                return i;
        return -1;
    }
}
