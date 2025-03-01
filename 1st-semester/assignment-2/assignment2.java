
import java.io.*;
import java.util.*;
/**
 * assignment2 - This program generates a monthly report for the scooter rental business.
 *               The user has to input the filename and its position, and the program
 *               will accumulate daily amount. 
 *               Finally, it will generate a total amount at the bottom.
 * 
 * Inputs:
 *    Filename
 * 
 * Processing: 
 *    Read the file line by line
 *    Accumulate daily amount for the number of rental, rental hour, income 
 *    Accumulate monthly amount for the number of rental, rental hour, income
 * 
 * Outputs:
 *    Daily amount for the number of rental, rental hour, income
 *    Monthly amount for the number of rental, rental hour, income
 * 
 * 
 * 
 * 
 * 
 * @author HSIAO-YU CHEN
 * @version 10-27-2019
 */
public class assignment2
{
    public static void main (String [] args) throws IOException
    {
        int year,month,day,previousDay,dailyrentCounter,totalRentCount;                //variables declaration
        double rentMin,rentAmt,dailyRentHour,dailyRentAmt,totalRentHour,totalRentAmt;
 
        dailyRentHour = 0;                 //variables initialization
        dailyRentAmt = 0;
        totalRentCount = 0;
        totalRentHour = 0;
        totalRentAmt = 0;

        Scanner k = new Scanner(System.in);           //asks the user to input filename, and opens that file
        System.out.print("Enter the filename:  ");
        String inputFile = k.nextLine();
        File dailyRentalData = new File(inputFile); 
        Scanner inputData = new Scanner(dailyRentalData);
        
        inputData.nextLine();     //reads(skips) the header

        year = inputData.nextInt();      //reads the first line
        month = inputData.nextInt(); 
        day = inputData.nextInt(); 
        inputData.next(); 
        rentMin = inputData.nextDouble(); 
        rentAmt = inputData.nextDouble(); 
        inputData.nextLine();
        dailyrentCounter = 1;

        previousDay = day;
        dailyRentHour += rentMin/60;
        dailyRentAmt += rentAmt;

        System.out.println("");
        System.out.println("         Daily Rental Income for Year " + year + " Month " + month);
        System.out.println("");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%15s%15s%15s%15s%n","Day of Month","# Rentals","Rental Hours","Income");
        System.out.println("------------------------------------------------------------");

        while(inputData.hasNextLine())  //reads the file while it has next line
        {
            year = inputData.nextInt(); 
            month = inputData.nextInt(); 
            day = inputData.nextInt(); 
            inputData.next(); 
            rentMin = inputData.nextDouble(); 
            rentAmt = inputData.nextDouble(); 
            inputData.nextLine(); 
            if(day == previousDay) //checks if day change or not
            {
                dailyrentCounter++;
                dailyRentHour += rentMin/60;
                dailyRentAmt += rentAmt;
            }
            else
            {
                System.out.printf("%15d%15d%15.2f%,15.2f%n",previousDay,dailyrentCounter,dailyRentHour,dailyRentAmt); 
                totalRentCount += dailyrentCounter;
                totalRentHour += dailyRentHour;
                totalRentAmt += dailyRentAmt;
                previousDay = day;
                dailyrentCounter = 1;
                dailyRentHour = rentMin/60;
                dailyRentAmt = rentAmt;
            }
        }
        System.out.printf("%15d%15d%15.2f%,15.2f%n",previousDay,dailyrentCounter,dailyRentHour,dailyRentAmt);   //prints output for the last day
        totalRentCount += dailyrentCounter;
        totalRentHour += dailyRentHour;
        totalRentAmt += dailyRentAmt;
        System.out.println("------------------------------------------------------------");
        String totalRentAmtFormat = String.format("$%,.2f",totalRentAmt);
        System.out.printf("%15s%15d%15.2f%15s%n","Total",totalRentCount,totalRentHour,totalRentAmtFormat); //prints output for the total amount
        inputData.close();
    }
}
