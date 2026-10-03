package hospital;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String phone;
    private List<Appointment> history = new ArrayList<>();

    public Patient(int id, String name, int age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Appointment> getHistory() {
        return history;
    }

    public void addAppointment(Appointment appointment) {
        history.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        history.remove(appointment);
    }

    @Override
    public String toString() {
        return "Patient ID: " + id +
               ", Name: " + name +
               ", Age: " + age +
               ", Phone: " + phone;
    }
}