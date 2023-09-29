import java.util.Calendar;
import java.util.StringTokenizer;
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public Date(String date){
        StringTokenizer part = new StringTokenizer(date,"/");
        this.month= Integer.parseInt(part.nextToken());
//        System.out.println(this.month);
        this.day = Integer.parseInt(part.nextToken());
//        System.out.println(this.day);
        this.year = Integer.parseInt(part.nextToken());
//        System.out.println(this.year);

    }
    public Date(){
        Calendar currInfo = Calendar.getInstance();
        this.year = currInfo.get(Calendar.YEAR);
        this.month = currInfo.get(Calendar.MONTH)+1;
        this.day= currInfo.get(Calendar.DAY_OF_MONTH);
    }

    public int compareTo(Date otherDate) {
        int yearComp = Integer.compare(this.year, otherDate.year);
        if (yearComp != 0) {
            return yearComp;
        }
        int monthComp= Integer.compare(this.month, otherDate.month);
        if (monthComp != 0) {
            return monthComp;
        }
        return Integer.compare(this.day, otherDate.day);
    }

    public boolean isValid(){ //check if the date is a valid calendar date

        if (this.year < 2023 || this.month < 1 || this.month > 12 || this.day< 1|| this.year>2024)
            return false;

        int[] dayMonth = {0, 31, 28 + isLeapYear(), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (checkDate() == false)
            return false;

        Calendar eDate = Calendar.getInstance();
        Calendar specificDate = Calendar.getInstance();
        specificDate.set(Calendar.YEAR, this.year);
        specificDate.set(Calendar.MONTH, (this.month)-1);
        specificDate.set(Calendar.DAY_OF_MONTH, this.day);
        eDate.add(Calendar.MONTH,6);

        if(eDate.after(specificDate)==false){ //6 months or more error statement
            return false;
        }

        if (this.day > dayMonth[month]) {
            return false;
        }
        return true;
    }

    private boolean checkDate(){ //method to check if the givenDate is a future date
        Date currDate = new Date();

        if (this.year > currDate.year) {
            return true;
        } else {
            if (this.year == currDate.year) {
                if (this.month > currDate.month) {
                    return true;
                } else {
                    if (this.month == currDate.month) {
                        return this.day > currDate.day;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
    }
    private int isLeapYear() {
        if (this.year % QUADRENNIAL == 0) {
            if (this.year % CENTENNIAL == 0) {
                if(this.year % QUATERCENTENNIAL==0)
                    return 1;
                else{
                    return 0;
                }
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        //Tests case 1 - invalid month - > Fail
        System.out.println("Running Test Case 1");
        Date testCase1 = new Date("13/1/2023");
        if(testCase1.isValid()) System.out.println("Test Case 1 - invalid month. Passed");
        else System.out.println("Test Case 1 - invalid month. Failed");
        System.out.println("Running Test Case 1");

        // Test Case #2, testing day=31 on a 30-day month - > Fail
        System.out.println("Running Test Case 2");
        Date testCase2 = new Date("9/31/2023");
        if(testCase2.isValid()) System.out.println("Test Case#2, testing day=31 on a 30-day month. Passed");
        else System.out.println("Test Case 2,testing day=31 on a 30-day month. Failed");

        // Test Case #2a, testing day=31 on a 31-day month - > Pass
        System.out.println("Running Test Case #2a");
        Date testCase2a = new Date("12/31/2023");
        if(testCase2a.isValid()) System.out.println("Test Case#2a, testing day=31 on a 31-day month. Passed");
        else System.out.println("Test Case#2a, testing day=31 on a 31-day month. Failed");

        // Test Case#3, checking a date in the future. - Fail
        System.out.println("Running Test Case#3");
        Date testCase3 = new Date("4/30/2023");
        if(testCase3.isValid()) System.out.println("Test Case #3, checking pass date - Passed");
        else System.out.println("Test Case#3. checking pass date - Failed");

        // Test Case#3a, checking a date in the future. - Pass
        System.out.println("Running Test Case#3a");
        Date testCase3a = new Date("2/1/2024");
        if(testCase3a.isValid()) System.out.println("Test Case #3a, checking future date - Passed");
        else System.out.println("Test Case#3a. checking future date - Failed");

        // Test Case #4, checking 02/29 on a leap year -> Pass
        System.out.println("Running Test Case#4");
        Date testCase4 = new Date("2/29/2024");
        if(testCase4.isValid()) System.out.println("Test Case#4, checking a date with 02/29 on a leap year. Passed");
        else System.out.println("Test Case4, checking a date 02/29 on a leap year. Failed");

        //Test Case #5, checking a date that is past 6 months from current date ->Fail
        System.out.println("Running Test Case#5");
        Date testCase5 = new Date("6/30/2024");
        if(testCase5.isValid()) System.out.println("Test Case#5, checking a date that is past 6 months from current date ->Pass");
        else System.out.println("Test Case5, checking a date that is past 6 months from current date ->Fail");

        //Test Case #6, Normal date entry -> Pass
        System.out.println("Running Test Case#6");
        Date testCase6 = new Date("03/28/2024");
        if(testCase6.isValid()) System.out.println("Test Case#6, Normal Date entry -> Pass");
        else System.out.println("Test Case6, Normal Date entry ->Fail");

    }
}
