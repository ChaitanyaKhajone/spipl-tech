package consoleService;
import static configaration.config.getConnection;
import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class ClinicManager
{
    public void registerPatient(String name , String contact)
    {
        String insert = "INSERT INTO patient(name, contact) VALUES(?, ?)";
        String select = "SELECT id FROM patient WHERE name = ?";
        try (Connection con = getConnection())
        {
            // Insert
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1 , name);
            ps.setString(2 , contact);
            ps.executeUpdate();
            System.out.print("\nPatient registered successfully ");
            // Id
            ps = con.prepareStatement(select);
            ps.setString(1 , name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("with id = " + rs.getInt(1));
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void listDoctorsBySpecialisation(int id)
    {
        String select = "SELECT * FROM doctor WHERE specialisation_id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1 , id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                System.out.println("\nDoctors Found");
                do
                {
                    System.out.printf("ID : %d , Name: %s , Fee: ₹%.2f\n" ,
                            rs.getInt("id") , rs.getString("name") , rs.getDouble("consultation_fee"));
                }
                while (rs.next());
            }
            else
                System.err.println("\nWrong specialisation id is provided");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void bookAppointment(int doctorId , int patientId , String date , String timeIn)
    {
        String checkSql = "SELECT COUNT(*) FROM appointment WHERE doctor_id = ? AND appointment_date = ? AND slot_time = ? AND status = 'BOOKED'";
        String bookSql = "INSERT INTO appointment(doctor_id, patient_id, appointment_date, slot_time) VALUES(?, ?, ?, ?)";
        String idSql = "SELECT id FROM appointment WHERE doctor_id = ? AND patient_id = ? AND appointment_date = ? AND slot_time = ? AND status = 'BOOKED'";
        String feeSql = "SELECT consultation_fee FROM doctor WHERE id = ?";
        try (Connection con = getConnection())
        {
            // Checking time
            LocalTime time = LocalTime.parse(timeIn , DateTimeFormatter.ofPattern("HH:mm"));
            if (time.getMinute() % 15 != 0)
            {
                System.err.println("\nInvalid slot, Appointments must be booked in 15-minute intervals.");
                return;
            }
            boolean morningShift = !time.isBefore(LocalTime.of(10 , 0)) && !time.isAfter(LocalTime.of(13 , 0));
            boolean eveningShift = !time.isBefore(LocalTime.of(17 , 0)) && !time.isAfter(LocalTime.of(20 , 0));
            if (!morningShift && !eveningShift)
            {
                System.err.println("\nClinic closed, Operating slots: 10 AM - 1 PM and 5 PM - 8 PM.");
                return;
            }
            // Checking appointments
            PreparedStatement ps = con.prepareStatement(checkSql);
            ps.setInt(1 , doctorId);
            ps.setString(2 , date);
            ps.setString(3 , timeIn);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0)
            {
                System.err.println("\nConflict Alert, This time slot is already taken for the doctor.");
                return;
            }
            // Booking
            ps = con.prepareStatement(bookSql);
            ps.setInt(1 , doctorId);
            ps.setInt(2 , patientId);
            ps.setString(3 , date);
            ps.setString(4 , timeIn);
            ps.executeUpdate();
            System.out.print("\nAppointment Confirmed ");
            // Getting Id
            ps = con.prepareStatement(idSql);
            ps.setInt(1 , doctorId);
            ps.setInt(2 , patientId);
            ps.setString(3 , date);
            ps.setString(4 , timeIn);
            rs = ps.executeQuery();
            rs.next();
            System.out.println("with id = " + rs.getInt(1));
            // Fees finding
            ps = con.prepareStatement(feeSql);
            ps.setInt(1 , doctorId);
            rs = ps.executeQuery();
            rs.next();
            double fee = rs.getDouble("consultation_fee");
            System.out.println("Bill Calculated = " + fee);
            webService.jsonManger.exportToJSON(1);
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void cancelAppointment(int id)
    {
        String update = "UPDATE appointment SET status = 'CANCELLED' WHERE id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setInt(1 , id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("\nAppointment cancelled, also time slot is freed up.");
            else
                System.err.println("\nAppointment ID not found.");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void searchByName(String name)
    {
        String select = "SELECT id FROM patient WHERE name = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1 , name);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                System.out.println("\nPatient Id is = " + rs.getInt(1));
            }
            else
            {
                System.err.println("\nPatient name not found.");
            }
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
}