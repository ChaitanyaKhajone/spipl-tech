package models;
public class Patient
{
    private int id;
    private String name;
    private int age;
    private String gender;
    private String contact;
    private String address;
    public Patient()
    {
    }
    public Patient(String name , int age , String gender , String contact , String address)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
    }
    public Patient(int id , String name , int age , String gender , String contact , String address)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
    }
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }
    public String getGender()
    {
        return gender;
    }
    public String getContact()
    {
        return contact;
    }
    public String getAddress()
    {
        return address;
    }
    @Override
    public String toString()
    {
        return "Patient [ " + "Id = " + id + ", Name = " + name + ", Age = " + age + ", Gender = " + gender + ", Contact = " + contact + ", Address = " + address + " ]";
    }
}
