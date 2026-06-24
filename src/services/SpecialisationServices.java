package services;
import static configaration.config.getConnection;
import java.sql.*;
import models.Specialisation;
public class SpecialisationServices
{
    public void addSpecialisation(String name)
    {
        String insert = "INSERT INTO specialisation(name) VALUES(?)";
        String select = "SELECT id FROM specialisation WHERE name = ?";
        try (Connection con = getConnection())
        {
            // Insert
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1 , name);
            ps.executeUpdate();
            System.out.print("\n Specialisation added successfully ");
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
    public void searchSpecialisation(int id)
    {
        String select = "SELECT name FROM specialisation WHERE id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1 , id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                System.out.printf("ID : %-2d , Name : %-20s ,%n" ,
                        rs.getInt(1) , rs.getString(2));
            else
                System.err.println("\n specialisation not found");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void allSpecialisations()
    {
        String select = "SELECT * FROM specialisation";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                System.out.println("\n  Specialisations Found");
                do
                {
                    System.out.printf("ID : %-2d , Name : %-20s ,%n" ,
                            rs.getInt(1) , rs.getString(2));
                }
                while (rs.next());
            }
            else
                System.err.println("\n specialisations not found");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void updateSpecialisation(Specialisation s)
    {
        String sql = "update specialisation set name = ? where id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1 , s.getName());
            ps.setInt(2 , s.getId());
            ps.executeUpdate();
            System.out.print("\n Specialisation Updated successfully ");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
    public void deleteSpecialisation(int id)
    {
        String sql = "delete from specialisation where id = ?";
        try (Connection con = getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1 , id);
            ps.executeUpdate();
            System.out.print("\n Specialisation deleted successfully ");
        }
        catch (SQLException e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
}