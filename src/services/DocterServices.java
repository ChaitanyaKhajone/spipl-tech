package services;
import static configaration.config.getConnection;
import java.sql.*;
import models.Doctor;
public class DocterServices
{
    public void addDoctor(Doctor d)
    {
        String insert = "INSERT INTO doctor(name,specialisationId,consultationFee) VALUES(?,?,?)";
        String select = "SELECT id FROM doctor WHERE name = ? & specialisationId = ? & consultationFee = ?";
        try (Connection con = getConnection())
        {
            // Insert
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1 , d.getName());
            ps.setInt(2 , d.getSpecialisationId());
            ps.setDouble(3 , d.getConsultationFee());
            ps.executeUpdate();
            System.out.print("\n Doctor added successfully ");
            // Id
            ps = con.prepareStatement(select);
            ps.setString(1 , d.getName());
            ps.setInt(2 , d.getSpecialisationId());
            ps.setDouble(3 , d.getConsultationFee());
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("with id = " + rs.getInt(1));
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void searchDoctor(int id)
    {
        String select = "SELECT * FROM doctor WHERE id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1 , id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            System.out.printf("ID : %d , Name : %s , specialisationId = %d , consultationFee = %.2f" ,
                        rs.getInt(1) , rs.getString(2) , rs.getInt(3) , rs.getDouble(4));
            else
                System.err.println("\n specialisation not found");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void allDoctors()
    {
        String select = "SELECT * FROM doctor";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                System.out.println("\n Doctors Found");
                do
                {
                    System.out.printf("ID : %d , Name : %s , specialisationId = %d , consultationFee = %.2f" ,
                            rs.getInt(1) , rs.getString(2) , rs.getInt(3) , rs.getDouble(4));
                }
                while (rs.next());
            }
            else
                System.err.println("\n Doctors not found");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void updateDoctor(Doctor d)
    {
        String sql = "update doctor set name = ? , specialisationId = ? , consultationFee = ? where id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1 , d.getName());
            ps.setInt(2 , d.getSpecialisationId());
            ps.setDouble(3 , d.getConsultationFee());
            ps.setInt(4 , d.getId());
            ps.executeUpdate();
            System.out.print("\n Doctor Updated successfully ");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void deleteDoctor(int id)
    {
        String sql = "delete from doctor where id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1 , id);
            ps.executeUpdate();
            System.out.print("\n Doctor deleted successfully ");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
}