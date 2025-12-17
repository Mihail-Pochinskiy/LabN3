class Test {
    public static void main(String[] args) {
        BusStop busStop = new BusStop();

        // Добавляем автобусы и их времена
        busStop.addBusTime(432, "04:34");
        busStop.addBusTime(432, "13:30");
        busStop.addBusTime(543, "09:32");
        busStop.addBusTime(4, "17:57");
        busStop.addBusTime(432, "14:26");

        // Показываем расписание
        busStop.showSchedule();

        // Удаляем время автобуса
        busStop.deleteTimeBus(432, "04:34");
        // Удаляем автобус
        busStop.deleteBus(543);

        // показываем расписание
        busStop.showSchedule();

        // Добавляем автобус по периоду по количеству приездов
        busStop.addPeriodBusQuantity(75, "12:00", "00:47", 10);
        // Добавляем автобус по периоду по времени окончания
        busStop.addPeriodBusTime(65, "14:58", "00:35", "17:07");

        // Показываем расписание
        busStop.showSchedule();

        // Выводим, дождется ли человек заданного автобуса
        System.out.println(busStop.willWaitBus("13:23", 75, 11));
        System.out.println(busStop.willWaitBus("15:34", 65, 29));
        System.out.println();

        // Показываем автобусы в указанном временном периоде
        busStop.busesInPeriodInDay("14:32", "15:47");
        busStop.busesInPeriod("17:30", "13:40");

        // Показываем автобус из списка, который приедет раньше других
        int [] numbers = new int[3];
        numbers[0] = 65;
        numbers[1] = 75;
        numbers[2] = 4;
        System.out.println(busStop.firstBus(numbers, "16:30"));
    }
}
