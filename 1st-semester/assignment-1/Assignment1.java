
import java.util.*;
/**
 * Scooter Rental Program - CMPP-269-F Assignment #1
 * This program calculates a customer bill for Figment Scooters for rental services. 
 * The customer have to first input the vehicle code, card information and rental period.
 * Once customer press Enter to unlock, the machine will calculate the total charge, 
 * and then, create a reception for this deal.
 * 
 * Inputs:
 *    vehicle code
 *    card number
 *    name on card
 *    expiry date
 *    rental period
 * 
 * Processing: Calculations:
 *    Total charge
 * 
 * Outputs:
 *    today's date and current time
 *    total charge
 *    last four numbers of the card
 * 
 * @author HSIAO-YU CHEN
 * @version 2019-10-04
 */
public class Assignment1
{
    public static void main (String [] args)
    {
        Scanner k = new Scanner(System.in);
        System.out.println("Welcome to Figment Scooters ");
        System.out.println(" ");
        System.out.print("Enter Vehicle code: ");
        String vehicle = k.nextLine();
        System.out.println("Price is $1.00 plus $0.25 per minute   ");
        System.out.println("Enter payment information    ");
        System.out.print("     Card number: ");
        String cardNumber = k.nextLine();
        System.out.print("     Name on card: ");
        String cardName = k.nextLine();
        System.out.print("     Expiry (MMYY): ");
        String cardExpiry = k.nextLine();
        System.out.print("Enter rental period (minutes): ");
        int rentalPeriod = k.nextInt();
        k.nextLine();
        System.out.print("Press Enter to unlock and start:   ");
        String enterPress = k.nextLine();
        if(enterPress.equals(""))
        {
            float totalCharge = 1 + rentalPeriod * 0.25F;
            System.out.println("Charge: $" + totalCharge);
            System.out.println("Ride safely! ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("------------------------------- ");
            System.out.println("+           Receipt           +");
            System.out.println("------------------------------- ");
            Date date = new Date();
            System.out.println(date);
            System.out.println(" ");
            System.out.printf("%-15s%16s\n","Initial charge ","$1.00");
            System.out.printf("%-15s%16d\n","Rental minutes",rentalPeriod);
            System.out.printf("%-15s%16s\n","Initial charge ","$0.25");
            System.out.println(" ");
            String totalChargeString = String.format("$%.2f",totalCharge);
            System.out.printf("%-15s%16s\n","Total ",totalChargeString);
            char cardNum1 = cardNumber.charAt(cardNumber.length()-1);
            char cardNum2 = cardNumber.charAt(cardNumber.length()-2);
            char cardNum3 = cardNumber.charAt(cardNumber.length()-3);
            char cardNum4 = cardNumber.charAt(cardNumber.length()-4);
            String cardLastFourNum = String.format("%c%c%c%c",cardNum4,cardNum3,cardNum2,cardNum1);
            System.out.printf("%-8s%-7s%16s\n","Card ...",cardLastFourNum,totalChargeString);
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" Thank you for renting from us! ");
            System.out.println(" ");
            System.out.println("               / ");
            System.out.println("              o--o  ");
        }
    }
}
