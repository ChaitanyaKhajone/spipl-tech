package models;
public class Appointment
{
    private int id;
    private int doctorId;
    private int patientId;
    private String appointmentDate;
    private String slotTime;
    private String status;
    public Appointment()
    {
    }
    public Appointment(int doctorId , int patientId , String appointmentDate , String slotTime , String status)
    {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.slotTime = slotTime;
        this.status = status;
    }
    public Appointment(int id , int doctorId , int patientId , String appointmentDate , String slotTime , String status)
    {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.slotTime = slotTime;
        this.status = status;
    }
    public int getId()
    {
        return id;
    }
    public int getDoctorId()
    {
        return doctorId;
    }
    public int getPatientId()
    {
        return patientId;
    }
    public String getAppointmentDate()
    {
        return appointmentDate;
    }
    public String getSlotTime()
    {
        return slotTime;
    }
    public String getStatus()
    {
        return status;
    }
    @Override
    public String toString()
    {
        return "Appointment [ " + "Id = " + id + ", Doctor Id = " + doctorId + ", Patient Id = " + patientId + ", Appointment Date = " + appointmentDate + ", Slot Time = " + slotTime + ", Status = " + status + " ]";
    }
}
