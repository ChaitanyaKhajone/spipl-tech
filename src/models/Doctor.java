package models;
public class Doctor
{
    private int id;
    private String name;
    private int specialisationId;
    private double consultationFee;
    public Doctor()
    {
    }
    public Doctor(String name , int specialisationId , double consultationFee)
    {
        this.name = name;
        this.specialisationId = specialisationId;
        this.consultationFee = consultationFee;
    }
    public Doctor(int id , String name , int specialisationId , double consultationFee)
    {
        this.id = id;
        this.name = name;
        this.specialisationId = specialisationId;
        this.consultationFee = consultationFee;
    }
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public int getSpecialisationId()
    {
        return specialisationId;
    }
    public double getConsultationFee()
    {
        return consultationFee;
    }
    @Override
    public String toString()
    {
        return "Doctor [ " + " Id = " + id + ", Name = " + name + ", Specialisation Id = " + specialisationId + ", Consultation Fee = " + consultationFee + " ]";
    }
}
