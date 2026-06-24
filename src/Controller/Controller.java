package Controller;
import java.util.Scanner;
import models.*;
import services.*;
public class Controller
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        AppoinmentServices as = new AppoinmentServices();
        DocterServices ds = new DocterServices();
        PatientServices ps = new PatientServices();
        SpecialisationServices ss = new SpecialisationServices();
        while (true)
        {
            System.out.println("\n                                          --- CLINIC MANAGEMENT SYSTEM ADMIN CONTROLLER ---");
            System.out.println("1.  Add Specialisation       2. Search Specialisation     3. All Specialisations      4. Update Specialisation     5. Delete Specialisation");
            System.out.println("6.  Add Patient              7. Search Patient            8. All Patients             9. Update Patient           10. Delete Patient");
            System.out.println("11. Add Doctor              12. Search Doctor            13. All Doctors             14. Update Doctor            15. Delete Doctor");
            System.out.println("12. Add Appointment         17. Search Appointment       18. All Appointments        19. Update Appointment       20. Delete Appointment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int c = scanner.nextInt();
            System.out.println("");
            switch (c)
            {
                case 0:
                    System.exit(0);
                case 1:
                    System.out.print("Enter Specialisation Name: ");
                    String specName = scanner.nextLine();
                    ss.addSpecialisation(specName);
                    break;
                case 2:
                    System.out.print("Enter Specialisation ID to Search: ");
                    int searchSpecId = scanner.nextInt();
                    ss.searchSpecialisation(searchSpecId);
                    break;
                case 3:
                    ss.allSpecialisations();
                    break;
                case 4:
                    System.out.print("Enter Existing Specialisation ID to Update: ");
                    int uSpecId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String uSpecName = scanner.nextLine();
                    ss.updateSpecialisation(new Specialisation(uSpecId , uSpecName));
                    break;
                case 5:
                    System.out.print("Enter Specialisation ID to Delete: ");
                    int delSpecId = scanner.nextInt();
                    ss.deleteSpecialisation(delSpecId);
                    break;
                case 6:
                    System.out.print("Enter Patient ID: ");
                    int pId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String pName = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int pAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Gender: ");
                    String pGender = scanner.nextLine();
                    System.out.print("Enter Contact: ");
                    String pContact = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String pAddress = scanner.nextLine();
                    ps.addPatient(new Patient(pId , pName , pAge , pGender , pContact , pAddress));
                    break;
                case 7:
                    System.out.print("Enter Patient ID to Search: ");
                    int searchPId = scanner.nextInt();
                    ps.searchPatient(searchPId);
                    break;
                case 8:
                    ps.allPatients();
                    break;
                case 9:
                    System.out.print("Enter Existing Patient ID to Update: ");
                    int upId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String upName = scanner.nextLine();
                    System.out.print("Enter New Age: ");
                    int upAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Gender: ");
                    String upGender = scanner.nextLine();
                    System.out.print("Enter New Contact: ");
                    String upContact = scanner.nextLine();
                    System.out.print("Enter New Address: ");
                    String upAddress = scanner.nextLine();
                    ps.updatePatient(new Patient(upId , upName , upAge , upGender , upContact , upAddress));
                    break;
                case 10:
                    System.out.print("Enter Patient ID to Delete: ");
                    int delPId = scanner.nextInt();
                    ps.deletePatient(delPId);
                    break;
                case 11:
                    System.out.print("Enter Doctor ID: ");
                    int dId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String dName = scanner.nextLine();
                    System.out.print("Enter Specialisation ID: ");
                    int dSpecId = scanner.nextInt();
                    System.out.print("Enter Consultation Fee: ");
                    double dFee = scanner.nextDouble();
                    ds.addDoctor(new Doctor(dId , dName , dSpecId , dFee));
                    break;
                case 12:
                    System.out.print("Enter Doctor ID to Search: ");
                    int searchDId = scanner.nextInt();
                    ds.searchDoctor(searchDId);
                    break;
                case 13:
                    ds.allDoctors();
                    break;
                case 14:
                    System.out.print("Enter Existing Doctor ID to Update: ");
                    int udId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String udName = scanner.nextLine();
                    System.out.print("Enter New Specialisation ID: ");
                    int udSpecId = scanner.nextInt();
                    System.out.print("Enter New Consultation Fee: ");
                    double udFee = scanner.nextDouble();
                    ds.updateDoctor(new Doctor(udId , udName , udSpecId , udFee));
                    break;
                case 15:
                    System.out.print("Enter Doctor ID to Delete: ");
                    int delDId = scanner.nextInt();
                    ds.deleteDoctor(delDId);
                    break;
                case 16:
                    System.out.print("Enter Appointment ID: ");
                    int aId = scanner.nextInt();
                    System.out.print("Enter Doctor ID: ");
                    int aDocId = scanner.nextInt();
                    System.out.print("Enter Patient ID: ");
                    int aPatId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                    String aDate = scanner.nextLine();
                    System.out.print("Enter Slot Time (HH:MM): ");
                    String aSlot = scanner.nextLine();
                    System.out.print("Enter Status: ");
                    String aStatus = scanner.nextLine();
                    as.addAppoinment(new Appointment(aId , aDocId , aPatId , aDate , aSlot , aStatus));
                    break;
                case 17:
                    System.out.print("Enter Appointment ID to Search: ");
                    int searchAId = scanner.nextInt();
                    as.searchAppoinment(searchAId);
                    break;
                case 18:
                    as.allAppoinments();
                    break;
                case 19:
                    System.out.print("Enter Existing Appointment ID to Update: ");
                    int uaId = scanner.nextInt();
                    System.out.print("Enter New Doctor ID: ");
                    int uaDocId = scanner.nextInt();
                    System.out.print("Enter New Patient ID: ");
                    int uaPatId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Appointment Date (YYYY-MM-DD): ");
                    String uaDate = scanner.nextLine();
                    System.out.print("Enter New Slot Time (HH:MM): ");
                    String uaSlot = scanner.nextLine();
                    System.out.print("Enter New Status: ");
                    String uaStatus = scanner.nextLine();
                    as.updateAppoinment(new Appointment(uaId , uaDocId , uaPatId , uaDate , uaSlot , uaStatus));
                    break;
                case 20:
                    System.out.print("Enter Appointment ID to Delete: ");
                    int delAId = scanner.nextInt();
                    as.deleteAppoinment(delAId);
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
