
import java.io.*;
import java.util.*;
/**
 * Assignment3 - This program generates a monthly report for the scooter rental business.
 *               The user has to input the filename and its path, and the program will
 *               accumulate amount for each vehicle, and shows the most frequently rented
 *               vehicle and highest income vehicle. 
 *                            
 * Inputs:
 *    Filename and its path
 * 
 * Processing: 
 *    Reads the file data to arrays
 *    Accumulates rentals, rental hours and income for each vehicle
 *    Sorts array by vehicle code
 *    Accumulates monthly total amount for the rentals, rental hours and income
 *    Finds the most frequently rented vehicle and highest income vehicle.           
 * 
 * Outputs:
 *    A sorted table that shows monthly rentals, rental hours and income for each vehicle
 *    Shows monthly total amount, the most frequently rented vehicle and highest income vehicle.
 * 
 * 
 * @author HSIAO-YU CHEN
 * @version 11/17/2019
 */
public class Assignment3
{
    public static void main (String [] args) throws IOException
    {
        ArrayList<String> vehicleCodeArray = new ArrayList<>();                                    // declaration
        ArrayList<Double> rentHourArray = new ArrayList<>();
        ArrayList<Double> rentIncomeArray = new ArrayList<>();
        ArrayList<Integer> rentCountArray = new ArrayList<>();
        String vehicleCode,vehicleCodeMostFreqRented,vehicleCodeHighestIncome,rentIncomeAmountFormat;
        double rentHour,rentHourAmount,rentIncome,rentIncomeAmount,rentIncomeHighest;
        int year,month,rentCount,rentCountAmount,rentCountHighest,limit;
        boolean swapDone;

        rentHourAmount = 0;                                                                        //initialization
        rentIncomeAmount = 0;
        rentCountAmount = 0;
        rentIncomeHighest = 0;
        rentCountHighest = 0;
        vehicleCodeMostFreqRented = "";
        vehicleCodeHighestIncome = "";

        Scanner k = new Scanner(System.in);                                                   //asks the user to input filename, and opens that file
        System.out.print("Enter the filename: ");
        String fileName = k.nextLine();
        File rentalData = new File(fileName);
        Scanner fileData = new Scanner(rentalData);

        fileData.nextLine();                               //reads(skips) the header

        year = fileData.nextInt();                       // reads the first line of the data
        month = fileData.nextInt();
        fileData.nextInt();
        vehicleCodeArray.add(fileData.next());
        rentHourArray.add(fileData.nextDouble()/60);
        rentIncomeArray.add(fileData.nextDouble());
        rentCountArray.add(1);
        fileData.nextLine();                           //change line

        while(fileData.hasNextLine())                                   //reads all data to array
        {
            fileData.nextInt();
            fileData.nextInt();
            fileData.nextInt();
            vehicleCode = fileData.next();
            if(vehicleCodeArray.contains(vehicleCode))                  //checks vehicle code repeat or not
            {
                int pos = vehicleCodeArray.indexOf(vehicleCode);
                rentHour = rentHourArray.get(pos) + fileData.nextDouble()/60;
                rentHourArray.set(pos,rentHour);
                rentIncome = rentIncomeArray.get(pos) + fileData.nextDouble();
                rentIncomeArray.set(pos,rentIncome);
                rentCount = rentCountArray.get(pos) + 1;
                rentCountArray.set(pos,rentCount);
                fileData.nextLine();                 //change line
            }
            else
            {
                vehicleCodeArray.add(vehicleCode);
                rentHourArray.add(fileData.nextDouble()/60);
                rentIncomeArray.add(fileData.nextDouble());
                rentCountArray.add(1);
                fileData.nextLine();                //change line
            }
        }

        limit = vehicleCodeArray.size();               //bubble sort by vehicle code
        swapDone = true;
        while(limit > 1 && swapDone)
        {
            swapDone = false;
            for(int i = 0; i < limit-1; i++)
            {
                swapDone = true;
                vehicleCode = vehicleCodeArray.get(i);
                String vehicleCodeNext = vehicleCodeArray.get(i+1);
                if(vehicleCode.compareTo(vehicleCodeNext) > 0)
                {
                    String vehicleCodeTemp = vehicleCodeArray.get(i);
                    vehicleCodeArray.set(i,vehicleCodeNext);
                    vehicleCodeArray.set(i+1,vehicleCodeTemp);

                    int rentCountTemp = rentCountArray.get(i);
                    rentCountArray.set(i,rentCountArray.get(i+1));
                    rentCountArray.set(i+1,rentCountTemp);

                    double rentHourTemp = rentHourArray.get(i);
                    rentHourArray.set(i,rentHourArray.get(i+1));
                    rentHourArray.set(i+1,rentHourTemp);

                    double rentIncomeTemp = rentIncomeArray.get(i);
                    rentIncomeArray.set(i,rentIncomeArray.get(i+1));
                    rentIncomeArray.set(i+1,rentIncomeTemp);
                }
            }
            limit--;
        }

        System.out.println("");                                                                            // prints output report
        System.out.println("   Rental Income by Vehicle for Year " + year + " Month " + month);
        System.out.println("");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%15s%15s%15s%15s%n","Vehicle","# Rentals","Rental Hours","Income");
        System.out.println("------------------------------------------------------------");
        for(int i = 0; i < vehicleCodeArray.size(); i++)
        {
            vehicleCode = vehicleCodeArray.get(i);
            rentHour = rentHourArray.get(i);
            rentIncome = rentIncomeArray.get(i);
            rentCount = rentCountArray.get(i);
            rentHourAmount += rentHour;                                                          //Accumunates data amount
            rentIncomeAmount += rentIncome;
            rentCountAmount += rentCount;
            if(rentIncome > rentIncomeHighest)                                                   //finds highest income
            {
                rentIncomeHighest = rentIncome;
                vehicleCodeHighestIncome = vehicleCode;
            }
            if(rentCount > rentCountHighest)                                                   //finds most frequently rented
            {
                rentCountHighest = rentCount;
                vehicleCodeMostFreqRented = vehicleCode;
            }
            System.out.printf("%15s%15d%15.2f%,15.2f%n",vehicleCode,rentCount,rentHour,rentIncome); 
        }
        System.out.println("------------------------------------------------------------");
        rentIncomeAmountFormat = String.format("$%,.2f",rentIncomeAmount);
        System.out.printf("%15s%15d%15.2f%15s%n","Total",rentCountAmount,rentHourAmount,rentIncomeAmountFormat);
        System.out.println("");
        System.out.println("The most frequently rented vehicle was " + vehicleCodeMostFreqRented + " at " + rentCountHighest + " rentals");
        System.out.printf("The highest income vehicle was %s at $%,.2f%n",vehicleCodeHighestIncome,rentIncomeHighest);
        fileData.close();                                                                                                                //closes file
    }
}
