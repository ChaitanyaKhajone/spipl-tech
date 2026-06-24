package services;
import static configaration.config.getConnection;
import java.sql.*;
import models.Appointment;
public class AppoinmentServices
{
    public void addAppoinment(Appointment a)
    {
        String insert = "INSERT INTO appointment(doctor_id,patient_id,appointment_date,slot_time,status) VALUES(?,?,?,?,?)";
        String select = "SELECT id FROM appointment WHERE doctor_id = ? and patient_id = ? and appointment_date = ?";
        try (Connection con = getConnection())
        {
            // Insert
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1 , a.getDoctorId());
            ps.setInt(2 , a.getPatientId());
            ps.setString(3 , a.getAppointmentDate());
            ps.setString(4 , a.getSlotTime());
            ps.setString(5 , a.getStatus());
            ps.executeUpdate();
            System.out.print("\n Appointment added successfully ");
            // Id
            ps = con.prepareStatement(select);
            ps.setInt(1 , a.getDoctorId());
            ps.setInt(2 , a.getPatientId());
            ps.setString(3 , a.getAppointmentDate());
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("with id = " + rs.getInt(1));
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void searchAppoinment(int id)
    {
        String select = "SELECT * FROM appointment WHERE id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1 , id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                System.out.printf("ID : %-2d , Doctor ID : %-2d , Patient ID : %-2d , Date : %-10s , Slot : %-5s , Status : %-10s ,%n" ,
                        rs.getInt(1) ,
                        rs.getInt(2) ,
                        rs.getInt(3) ,
                        rs.getString(4) ,
                        rs.getString(5) ,
                        rs.getString(6));
            }
            else
            {
                System.err.println("\n Appointment not found");
            }
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void allAppoinments()
    {
        String select = "SELECT * FROM appointment";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                System.out.println("\n Appointments Found");
                do
                {
                    System.out.printf("ID : %-2d , Doctor ID : %-2d , Patient ID : %-2d , Date : %-10s , Slot : %-5s , Status : %-10s ,%n" ,
                            rs.getInt(1) ,
                            rs.getInt(2) ,
                            rs.getInt(3) ,
                            rs.getString(4) ,
                            rs.getString(5) ,
                            rs.getString(6));
                }
                while (rs.next());
            }
            else
            {
                System.err.println("\n Appointments not found");
            }
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void updateAppoinment(Appointment a)
    {
        String sql = "update appointment set doctor_id = ? , patient_id = ? , appointment_date = ? , slot_time = ? , status = ?  where id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1 , a.getDoctorId());
            ps.setInt(2 , a.getPatientId());
            ps.setString(3 , a.getAppointmentDate());
            ps.setString(4 , a.getSlotTime());
            ps.setString(5 , a.getStatus());
            ps.setInt(6 , a.getId());
            ps.executeUpdate();
            System.out.print("\n Appointment Updated successfully ");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void deleteAppoinment(int id)
    {
        String sql = "delete from appointment where id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1 , id);
            ps.executeUpdate();
            System.out.print("\n Appointment deleted successfully ");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
}