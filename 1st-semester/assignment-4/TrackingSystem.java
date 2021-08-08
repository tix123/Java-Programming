
import java.io.*;
import java.util.*;
/**
 * TrackingSystem - This program is Scooters Service Tracking System. Users
 *                  can choose what service they wawnt, and it will generate
 *                  a list for all scooters and their current status. This 
 *                  program will automatically load file when it starts and
 *                  save file when it closes.
 *                  
 * Inputs:
 *    Service choice
 *    Vehicle code
 * 
 * Processing: 
 *    Generates an ArrayList for all ServiceItem          
 *    Load file form c:/temp/servicedata.txt
 *    Save file to c:/temp/servicedata.txt
 *    
 * Outputs:
 *    A txt file at c:/temp/servicedata.txt
 * 
 * 
 * @author HSIAO-YU CHEN
 * @version 2019-12-07
 * @exception IOException Fail to load or save the file
 */
public class TrackingSystem
{
    public static void main (String [] args) throws IOException
    {
        String vehCode;
        int optionNum = 0;

        ArrayList<ServiceItem> vehicles = new ArrayList<>();
        Scanner keyboard = new Scanner(System.in);
        loadData(vehicles);                                           //load data

        while(optionNum != 4)
        {
            optionNum = display(vehicles,keyboard);
            switch(optionNum)
            {
                case 1:                                               //option 1: add a vehicle
                System.out.println("Add vehicle...");
                System.out.print("  Enter vehicle code:");
                keyboard.nextLine();
                vehCode = keyboard.nextLine();
                addVehicle(vehCode,vehicles);
                System.out.print("Press enter to continue...");
                keyboard.nextLine();
                //System.out.print("\f"); 
                break;

                case 2:                                               //option 2: update status
                System.out.println("Update status...");
                System.out.print("  Enter vehicle code:");
                keyboard.nextLine();
                vehCode = keyboard.nextLine();
                updateStatus(vehCode,vehicles);
                System.out.print("Press enter to continue...");
                keyboard.nextLine();
                //System.out.print("\f"); 
                break;

                case 3:                                                //option 3: remove a vehicle
                System.out.println("Remove vehicle...");
                System.out.print("  Enter vehicle code:");
                keyboard.nextLine();
                vehCode = keyboard.nextLine();
                removeVehicle(vehCode,vehicles);
                System.out.print("Press enter to continue...");
                keyboard.nextLine();
                //System.out.print("\f"); 
                break;

                case 4:                                                //option 4: save & exit
                saveData(vehicles);
                break;
            }
        }
    }

    /**
     * display - display service list and ask the user to input the choice
     * @param sList refer to ArrayList vehicles
     * @param kbrd a Scanner for input
     * @return option number
     */
    public static int display(ArrayList<ServiceItem> sList, Scanner kbrd)
    {
        System.out.println("");
        System.out.println("Figment Scooters Service Tracking System");
        System.out.printf("%-15s%-25s%n","Vehicle Code","Current Status");           
        System.out.println("========================================");
        for(int i = 0; i < sList.size(); i++)
        {
            System.out.printf("%-15s%-25s%n",sList.get(i).getVehicleCode(),sList.get(i).getStatusText());
        }
        System.out.println("========================================");
        System.out.println("1. Add a vehicle");
        System.out.println("2. Update status");
        System.out.println("3. Remove a vehicle");
        System.out.println("4. Exit");
        System.out.print("Enter option number:"); 
        int optNum = kbrd.nextInt();
        return optNum;
    }

    /**
     * addVehicle - add a vehicle into the list 
     * @param sList refer to ArrayList vehicles
     * @param vCode vehicle code
     */
    public static void addVehicle (String vCode, ArrayList<ServiceItem> sList)
    {
        sList.add(new ServiceItem(vCode,ServiceItem.PENDING_ASSESSMENT));
        System.out.printf("  Vehicle code %s has been added%n",vCode);
    }

    /**
     * saveData - Save vehicles list to a txt file  
     * @param sList refer to ArrayList vehicles
     * @exception IOException Fail to write the file
     */
    public static void saveData(ArrayList<ServiceItem> sList) throws IOException
    {
        PrintWriter outputFile = new PrintWriter("c:/temp/servicedata.txt");
        for(int i = 0; i < sList.size(); i++)
        {
            outputFile.println(sList.get(i).getVehicleCode() + " " + sList.get(i).getStatusCode());
        }
        outputFile.close();
    }

    /**
     * loadData - load the file to vehicle list  
     * @param sList refer to ArrayList vehicles
     * @exception IOException Fail to load the file
     */
    public static void loadData(ArrayList<ServiceItem> sList) throws IOException
    {
        File f = new File("c:/temp/servicedata.txt");
        Scanner inputFile = new Scanner(f);
        while(inputFile.hasNextLine())
        {
            sList.add(new ServiceItem(inputFile.next(),inputFile.nextInt()));
            inputFile.nextLine();
        }
        inputFile.close();
    }

    /**
     * updateStatus - update vehicle status  
     * @param sList refer to ArrayList vehicles
     * @param vCode vehicle code
     */
    public static void updateStatus(String vCode, ArrayList<ServiceItem> sList)
    {
        ServiceItem temp = new ServiceItem(vCode,0);
        int i = sList.indexOf(temp);
        if(i>=0)
        {
            sList.get(i).updateStatusCode();
            System.out.printf("  Vehicle code %s status updated%n",vCode);
        }
        else
        {
            System.out.printf("  Vehicle code %s not found in service list%n",vCode);
        }
    }

    /**
     * removeVehicle - remove a vehicle information form the list  
     * @param sList refer to ArrayList vehicles
     * @param vCode vehicle code
     */
    public static void removeVehicle(String vCode, ArrayList<ServiceItem> sList)
    {
        ServiceItem temp = new ServiceItem(vCode,0);
        int i = sList.indexOf(temp);
        if(i>=0)
        {
            sList.remove(i);
            System.out.printf("  Vehicle code %s removed successfully%n",vCode);
        }
        else
        {
            System.out.printf("  Vehicle code %s not found in service list%n",vCode);
        }
    }

}
