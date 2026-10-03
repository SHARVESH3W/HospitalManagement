package hospital;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private int id;
    private String name;
    private String specialization;
    private List<Appointment> schedule = new ArrayList<>();

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<Appointment> getSchedule() {
        return schedule;
    }

    public void addAppointment(Appointment appointment) {
        schedule.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        schedule.remove(appointment);
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id +
               ", Name: Dr. " + name +
               ", Specialization: " + specialization;
    }
}