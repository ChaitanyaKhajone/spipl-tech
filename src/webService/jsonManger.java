package webService;
import configaration.config;
import java.io.FileWriter;
import java.sql.*;
public class jsonManger
{
    public static void exportToJSON(int i)
    {
        String sql = """
                SELECT
                a.id AS appointmentId,
                p.name AS patientName,
                p.contact AS patientContact,
                d.name AS doctorName,
                s.name AS specialisation,
                a.slot_time AS time,
                a.status AS status,
                d.consultation_fee AS fees
                FROM appointment a
                JOIN doctor d ON a.doctor_id = d.id
                JOIN patient p ON a.patient_id = p.id
                JOIN specialisation s ON d.specialisation_id = s.id
                WHERE a.appointment_date = CURDATE()
                     """;
        try (Connection con = config.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            StringBuilder json = new StringBuilder("[\n");
            boolean check = false;
            while (rs.next())
            {
                if (check)
                    json.append("\n,\n");
                json.append(String.format("""
                                          { "appointmentId" : %d,
                                            "patientName" : "%s",
                                            "patientContact" : "%s",
                                            "doctorName" : "%s",
                                            "specialisation" : "%s",
                                            "time" : "%s",
                                            "status" : "%s",
                                            "fees" : %.2f }""" ,
                        rs.getInt("appointmentId") ,
                        rs.getString("patientName") ,
                        rs.getString("patientContact") ,
                        rs.getString("doctorName") ,
                        rs.getString("specialisation") ,
                        rs.getString("time") ,
                        rs.getString("status") ,
                        rs.getDouble("fees")
                ));
                check = true;
            }
            json.append("\n]");
            FileWriter file = new FileWriter("src/web/appointments.json");
            file.write(json.toString());
            if (i == 0)
            {
                System.out.println("Exported successfully to appointments.json");
            }
            file.close();
        }
        catch (Exception e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
    }
}