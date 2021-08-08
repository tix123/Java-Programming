 /**
 * ServiceItem - This class represents a vehicle service item consisting of vehicle code and status.
 *               Used in CMPP269 Fall 2019 Assignment #4.
 * 
 * @author Doug Shier
 * @version (1.0 Nov. 2019)
 */
public class ServiceItem
{
    // Constants defining the available status codes and corresponding display text
    public static final int PENDING_ASSESSMENT = 0;
    public static final int PENDING_SERVICE = 1;
    public static final int SERVICE_IN_PROGRESS = 2;
    public static final int READY_FOR_PICKUP = 3;
    private static final String[] STATUS_TEXT = {"Pending Assessment",
            "Pending Service",
            "Service in Progress",
            "Ready for Pickup"};

    // Instance fields
    private String vehicleCode;
    private int statusCode;

    /**
     * ServiceItem() - constructs a new service item
     * 
     * @param vCode     initial vehicle code
     * @param sCode     initial status code
     */
    public ServiceItem(String vCode, int sCode)
    {
        vehicleCode = vCode;
        statusCode = sCode;
    }
    
    /**
     * getVehicleCode() - returns the vehicle code
     * 
     * @return     the vehicle code
     */
    public String getVehicleCode()
    {
        return vehicleCode;
    }

    /**
     * getStatusCode() - returns the status code
     * 
     * @return     the status code
     */
    public int getStatusCode()
    {
        return statusCode;
    }

    /**
     * getStatusText() - returns the status text
     * 
     * @return     the status text
     */
    public String getStatusText()
    {
        return STATUS_TEXT[statusCode];
    }

    /**
     * updateStatusCode() - Checks if the current status code can be updated (advanced) and, if so,
     *                      increases the current status by 1. The defined status values are:
     *                         Code           Text             Sequence
     *                          0       Pending Assessment      start
     *                          1       Pending Service           |
     *                          2       Service in Progress       V
     *                          3       Ready for Pickup         end
     *                      Status codes of 3 (Ready for Pickup) are not updated any further.
     * 
     * @return     true if status changed, false if not
     */
    public boolean updateStatusCode()
    {
        boolean result = false;
        if (statusCode >= PENDING_ASSESSMENT && statusCode < READY_FOR_PICKUP)
        {
            statusCode += 1;
            result = true;
        }
        return result;
    }

    /**
     * equals() - overrides Objects.equals to determine service item equality by comparing vehicle
     *            code strings
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }

        if (!ServiceItem.class.isAssignableFrom(obj.getClass()))
        {
            return false;
        }

        final ServiceItem other = (ServiceItem) obj;
        if ((this.vehicleCode == null) ? (other.vehicleCode != null) : !this.vehicleCode.equals(other.vehicleCode))
        {
            return false;
        }

        return true;
    }

    /**
     * hashCode() - overrides Objects.hashCode to derive service item hashcode using vehicle code
     */
    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + (this.vehicleCode != null ? this.vehicleCode.hashCode() : 0);
        return hash;
    }

}

