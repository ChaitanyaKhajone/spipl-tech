package services;
import static configaration.config.getConnection;
import java.sql.*;
import models.Patient;
public class PatientServices
{
    public void addPatient(Patient p)
    {
        String insert = "INSERT INTO patient(name,age,gender,contact,address) VALUES(?,?,?,?,?)";
        String select = "SELECT id FROM patient WHERE name = ? & age = ? & gender = ?";
        try (Connection con = getConnection())
        {
            // Insert
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1 , p.getName());
            ps.setInt(2 , p.getAge());
            ps.setString(3 , p.getGender());
            ps.setString(4 , p.getContact());
            ps.setString(5 , p.getAddress());
            ps.executeUpdate();
            System.out.print("\n Patient added successfully ");
            // Id
            ps = con.prepareStatement(select);
            ps.setString(1 , p.getName());
            ps.setInt(2 , p.getAge());
            ps.setString(3 , p.getGender());
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("with id = " + rs.getInt(1));
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void searchPatient(int id)
    {
        String select = "SELECT * FROM patient WHERE id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1 , id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                System.out.printf("""
                                  ID : %d ,
                                  Name : %s ,
                                  Age = %d ,
                                  Gender = %s,
                                  Contact = %s,
                                  Address = %s
                                  """ ,
                        rs.getInt(1) ,
                        rs.getString(2) ,
                        rs.getInt(3) ,
                        rs.getString(4) ,
                        rs.getString(5) ,
                        rs.getString(6));
            }
            else
                System.err.println("\n Patient not found");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void allPatients()
    {
        String select = "SELECT * FROM patient";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                System.out.println("\n Patients Found");
                do
                {
                    System.out.printf("""
                                  ID : %d ,
                                  Name : %s ,
                                  Age = %d ,
                                  Gender = %s,
                                  Contact = %s,
                                  Address = %s
                                  """ ,
                            rs.getInt(1) ,
                            rs.getString(2) ,
                            rs.getInt(3) ,
                            rs.getString(4) ,
                            rs.getString(5) ,
                            rs.getString(6));
                }
                while (rs.next());
            }
            else
                System.err.println("\n Patients not found");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void updatePatient(Patient p)
    {
        String sql = "update patient set name = ? , age = ? , gender = ? , contact = ? , address = ?  where id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1 , p.getName());
            ps.setInt(2 , p.getAge());
            ps.setString(3 , p.getGender());
            ps.setString(4 , p.getContact());
            ps.setString(5 , p.getAddress());
            ps.setInt(6 , p.getId());
            ps.executeUpdate();
            System.out.print("\n Patient Updated successfully ");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void deletePatient(int id)
    {
        String sql = "delete from patient where id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1 , id);
            ps.executeUpdate();
            System.out.print("\n Patient deleted successfully ");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
}