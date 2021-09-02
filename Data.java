import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class Data {
    String area;
    String zone;
    String [] RangeOfTime;
    String [] userInputString;

    int days;
    int weeks;

    String newInput; //use foor loop input

    int numberOfDay;
    int numberOfWeek;
    LocalDate startDate;
    LocalDate endDate;

    Scanner sc = new Scanner(System.in);

    //constructor

    public Data() {
    }

    public Data(String zone, LocalDate startDate, LocalDate endDate, String[] RangeOfTime) {
        this.zone = zone;
        this.startDate = startDate;
        this.endDate = endDate;
        this.RangeOfTime = RangeOfTime;
    }

//    public Data() {
//        this.zone = zone;
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }

    //method for users to choose their zone
    public void zoneInput(){
        System.out.println("""
                Enter (1) or (2) to choose:
                1. Country
                2. Continent
                """);

        String areaInput = sc.nextLine();

        switch (areaInput) {
            case "1":
                System.out.println("Please input your country: ");
                zone = sc.nextLine();
                break;
            case "2":
                System.out.println("Please input your continent: ");
                zone = sc.nextLine();
                break;
            default:
                System.out.println("Invalid zone!! Please input again.");
                zoneInput();
        }
    }
//convert user input from string to localdate format
    public LocalDate inputDate(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date = LocalDate.parse(userInput, formatter);
        return date;
    }

    public void timeRange(){
        String op1;
        String op2;

        System.out.println("""
                Choose a time range to calculate:
                1. Star date & End date.
                2. Number of days/weeks from a particular date.
                3. Number of days/weeks to a particular date.
                """);
        op1 = sc.nextLine();


        switch (op1){
            case "1":
                pairTime();
                break;
            case "2":
                System.out.println("Choose between days(1) or weeks(2) to calculate:");
                op2 = sc.nextLine();
                switch (op2){
                    case "1":
                        dayFrom();
                        break;
                    case "2":
                        weekFrom();
                        break;
                    default:
                        System.out.println("Invalid. Please only choose (1) or (2)");
                        timeRange();
                }
                break;
            case "3":
                System.out.println("Choose between days(1) or weeks(2) to calculate:");
                op2 = sc.nextLine();
                switch (op2){
                    case "1":
                        dayTo();
                        break;
                    case "2":
                        weekTo();
                        break;
                    default:
                        System.out.println("Invalid. Please only choose (1) or (2)");
                        timeRange();
                }
                break;
        }
    }

//user input
    public void inputDate(){
        String userInput = sc.nextLine();
        if (userInput.contains("-")){
            userInputString = userInput.split("-");
            int month = Integer.parseInt(userInputString[0]);
            int day = Integer.parseInt(userInputString[1]);
            int year = Integer.parseInt(userInputString[2]);

//condition for user input
            if (month < 1 || month > 12){
                System.out.println("You input an invalid month. Please try again");
                inputDate();
            }

            if (day < 1 || day > 31){
                System.out.println("You input an invalid date. Please try again");
                inputDate();
            }

            if (month == 2)
                if(day > 29) {
                    System.out.println("Invalid day. Try again");
                    inputDate();
                }

            if(year != 2020 && year != 2021){
                System.out.println("Only 2020 and 2021 is valid. Please try again");
                inputDate();
            }

//special case for condition
            if (month == 2)
                if (day == 29)
                    if (year == 2020) {
                        System.out.println("Invalid date. There is no 2-29 in 2020. Please try again");
                        inputDate();
                    }


//loop until user input a valid date
            for (int i = 0; i < userInputString.length; i++) {
                newInput = userInput;
            }
//convert user input into "/"
            for (int i = 0; i < userInputString.length; i++){
                newInput = String.join("/",userInputString[0],userInputString[1],userInputString[2]);
            }
        }
    }

//method for starDate input
    public void inputStartDate(){
        System.out.println("Please input start date of the time range: (M/d/yyyy)");
        inputDate();
        startDate = inputDate(newInput);
    }

//method for endDate input
    public void inputEndDate(){
        System.out.println("Please input end date of the time range: (M/dd/yyyy)");
        inputDate();
        endDate = inputDate(newInput);
    }


////display date after calculate
//    public void display() {
////        inputStartDate();
////        inputEndDate();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
//        System.out.printf("Time Range: %s -> %s", formatter.format(startDate),formatter.format(endDate));
//    }

//method to get days from a particular day
    public void dayFrom(){
        inputStartDate();
        System.out.println("Enter number of days to calculate (include start date): ");
        days = sc.nextInt();
        //count end date
        this.endDate = this.startDate.plusDays(this.days);
        var lstDate =  ListOfDate(startDate, endDate);
        for (LocalDate d : lstDate){
            System.out.println(d);
        }
    }

//method to get weeks from a particular day
    public void weekFrom(){
        inputStartDate();
        System.out.println("Enter number of weeks to calculate: ");
        weeks = sc.nextInt();
        //change to day
        this.days = this.weeks * 7;
        this.endDate = this.startDate.plusDays(this.days);
        var lstDate =  ListOfDate(startDate, endDate);
        for (LocalDate d : lstDate){
            System.out.println(d);
        }
    }

//method to get days to a particular date
    public void dayTo(){
        inputEndDate();
        System.out.println("Enter number of days to calculate: ");
        days = sc.nextInt();
        //count start date
        this.startDate = this.endDate.minusDays(this.days);
        var lstDate =  ListOfDate(startDate, endDate);
        for (LocalDate d : lstDate){
            System.out.println(d);
        }
    }

//method to get days to a particular week
    public void weekTo(){
        inputEndDate();
        System.out.println("Enter number of weeks to calculate: ");
        weeks = sc.nextInt();
        //convert to day
        this.days = this.weeks * 7;
        this.startDate = this.endDate.minusDays(this.days);
        var lstDate =  ListOfDate(startDate, endDate);
        for (LocalDate d : lstDate){
            System.out.println(d);
        }
    }


//method to get pair of day
    public void pairTime(){
        inputStartDate();
        inputEndDate();
        var lstDate =  ListOfDate(startDate, endDate);
        for (LocalDate d : lstDate){
            System.out.println(d);
        }
    }

//display time range as a list
    public static List<LocalDate> ListOfDate(LocalDate startdate, LocalDate enddate){
        List<LocalDate> dates = new ArrayList<LocalDate>();

        //convert localdate variable to date
        Date beginDate = Date.from(startdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(enddate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(beginDate);

        while (calendar.getTime().before(endDate)){
            LocalDate result = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        dates.add(enddate);
        return dates;
    }
}

