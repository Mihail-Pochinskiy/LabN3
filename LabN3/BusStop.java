class BusStop {
    public Bus [] buses = new Bus[100];
    // Храним массив автобусов. Его длина обусловлена здравым смыслом

    public BusStop() {
        for (int i=0; i<100; i++)
            buses[i]=null;
    }

    // Показ расписания
    public void showSchedule() {
        for (int i=0; i<100 && buses[i]!=null; i++)
            System.out.println(buses[i]);
        System.out.println();
    }

    // Добавление времени автобуса
    public void addBusTime(int number, String stringTime) {
        addBusTime(number, new Time(stringTime));
    }

    // Удаление автобуса
    public void deleteBus(int number) {
        int i = thereIsBus(number);
        if (i!=-1){ // Проверяем, что такой автобус вообще есть, иначе что удалять?)
            for (int j=i; j<99; j++)
                buses[j]=buses[j+1];
            buses[99]=null;
        }
    }

    // Удаление времени у конкретного автобуса
    public void deleteTimeBus(int number, String stringTime) {
        int i=thereIsBus(number);
        if (i!=-1) { // Проверяем, что такой автобус вообще есть, иначе у чего удалять?)
            buses[i].deleteTime(stringTime);
        }

        if (!buses[i].thereIsTime()) // Если мы удалили последнее время автобуса, то надо удалить его2
            deleteBus(number);
    }

    // Добавляем периодический автобус по количеству повторений
    public void addPeriodBusQuantity(int number, String stringTimeStart, String stringInterval, int quantity) {
        Time nextTime = new Time(stringTimeStart);
        Time interval = new Time(stringInterval);
        for (int i=0; i<quantity; i++) {
            addBusTime(number, nextTime);
            nextTime = Time.plus(nextTime, interval);
        }
    }

    // Добавляем периодический автобус по времени окончания
    public void addPeriodBusTime(int number, String stringTimeStart, String stringInterval, String stringEnd) {
        Time nextTime = new Time(stringTimeStart);
        Time interval = new Time(stringInterval);
        Time end = new Time(stringEnd);
        while (Time.isTimeLess(nextTime, end)) {
            addBusTime(number, nextTime);
            nextTime = Time.plus(nextTime, interval);
        }
    }

    // Дождется ли пассажир автобуса
    public boolean willWaitBus(String stringComeTime, int number, int minutes) {
        Time comeTime = new Time(stringComeTime);
        Time leaveTime = Time.plus(comeTime, new Time(0, minutes));
        int i = thereIsBus(number);
        if (i==-1)
            return false;
        return buses[i].willWaitBus(comeTime, leaveTime);
    }

    // Автобусы в некотором периоде в течение дня
    public void busesInPeriodInDay(String stringStart, String stringEnd) {
        Time startTime = new Time(stringStart);
        Time endTime = new Time(stringEnd);
        for (int i=0; i<100 && buses[i]!=null; i++)
            if (buses[i].willWaitBus(startTime, endTime))
                System.out.println(buses[i].number);
        System.out.println();
    }

    // Автобусы в некотором периоде
    public void busesInPeriod(String stringStart, String stringEnd) {
        Time startTime = new Time(stringStart);
        Time endTime = new Time(stringEnd);
        if (Time.isTimeLess(startTime, endTime))
            busesInPeriodInDay(stringStart, stringEnd);
        else {
            Time midnight = new Time(0, 0);
            for (int i=0; i<100 && buses[i]!=null; i++)
                if (buses[i].willWaitBus(startTime, midnight) || buses[i].willWaitBus(midnight, endTime))
                    System.out.println(buses[i].number);
        }
        System.out.println();
    }

    // Первый автобус из списка
    public int firstBus(int [] numbers, String stringTimeCome) {
        Time timeCome = new Time(stringTimeCome);
        Time min = null;
        int iMin = 0;
        for (int i=0; i<100 && buses[i]!=null; i++)
            if (isNumberInList(buses[i].number, numbers)) {
                if (min==null) {
                    min = buses[i].nextTime(timeCome);
                    iMin = i;
                }
                else {
                    Time time = buses[i].nextTime(timeCome);
                    if (time!=null && Time.isTimeLess(time, min))
                        min = time;
                    iMin = i;
                }
            }
        return buses[iMin].number;
    }

    // "Рабочие" методы

    // Есть ли номер в переданном списке для первого метода
    public boolean isNumberInList(int number, int [] numbers) {
        for (int i=0; i<numbers.length; i++)
            if (number==numbers[i])
                return true;
        return false;
    }

    // Добавление автобуса по уже инициализированному времени
    public void addBusTime(int number, Time time) {
        int i = thereIsBus(number);
        if (i!=-1) // Если автобус с таким номером уже есть
            buses[i].addTime(time);
        else { // Если автобуса с таким номером нет, надо создать его
            int j=0; // Хотим отсортировать номера автобусов по возрастанию
            while (j<100 && buses[j]!=null && buses[j].number<number)
                j++;
            for (int k=99; k>j; k--)
                buses[k]=buses[k-1];
            buses[j] = new Bus(number, time);
        }
    }

    // Есть ли автобус с таким номером
    public int thereIsBus(int number) {
        for (int i = 0; i < 100 && buses[i] != null; i++)
            if (buses[i].number == number)
                return i;
        return -1;
    }
}
