import consoleService.ClinicManager;
import java.util.Scanner;
public class main
{
    private static Scanner sc = new Scanner(System.in);
    private static int validateInt()
    {
        int i = 0;
        while (i == 0)
        {
            try
            {
                i = sc.nextInt();
            }
            catch (Exception e)
            {
                System.err.println("Only enter number.");
                sc.next();
            }
        }
        return i;
    }
    private static String validateNum()
    {
        String s = "";
        while (s.length() != 10 || !s.matches("\\d{10}"))
        {
            try
            {
                s = sc.nextLine();
                System.err.print(s.length()==10 ? "" : "\nNumber Should be of length 10\n");
                System.err.print(s.matches("\\d{10}") ? "" : "Number Should be a number\n");
            }
            catch (Exception e)
            {
                System.err.println("Only enter valid contact number.");
                sc.next();
            }
        }
        return s;
    }
    public static void main(String[] args)
    {
        ClinicManager cm = new ClinicManager();
        while (true)
        {
            System.out.println("\n===================================\n"
                    + "Clinic Management System\n"
                    + "===================================\n"
                    + "1. Register Patient\n"
                    + "2. List Doctors By Specialisation\n"
                    + "3. Book Appointment\n"
                    + "4. Cancel Appointment\n"
                    + "5. Search Patient by Name\n"
                    + "6. Export to JSON file\n"
                    + "7. Exit from System\n"
                    + "Choise your option from above");
            switch (validateInt())
            {
                case 1 ->
                {
                    System.out.println("\nEnter Patient Name");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println("Enter Patient Contact Number");
                    String contact = validateNum();
                    cm.registerPatient(name,contact);
                }
                case 2 ->
                {
                    System.out.println("\nEnter Specialisation Id");
                    cm.listDoctorsBySpecialisation(validateInt());
                }
                case 3 ->
                {
                    System.out.println("\nEnter Docter Id");
                    int docterId = validateInt();
                    System.out.println("Enter patient Id");
                    int patientId = validateInt();
                    System.out.println("Enter Appointment Date in format of YYYY-MM-DD");
                    String date = sc.next();
                    System.out.println("Enter Appointment Time in HH:MM");
                    String time = sc.next();
                    cm.bookAppointment(docterId , patientId , date , time);
                }
                case 4 ->
                {
                    System.out.println("\nEnter Appointment Id");
                    cm.cancelAppointment(validateInt());
                }
                case 5 ->
                {
                    System.out.println("\nEnter Patient Name");
                    sc.nextLine();
                    String name = sc.nextLine();
                    cm.searchByName(name);
                }
                case 6 ->
                    webService.jsonManger.exportToJSON(0);
                case 7 ->
                {
                    System.out.println("Have a good Day\n"
                            + "===============");
                    System.exit(0);
                }
                default ->
                    System.err.println("Please enter vaild choice");
            }
        }
    }
}