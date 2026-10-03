package hospital;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int patientId;
    private int doctorId;
    private LocalDate date;
    private LocalTime time;

    public Appointment(int patientId, int doctorId,
                       LocalDate date, LocalTime time) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
               " | Doctor ID: " + doctorId +
               " | Date: " + date +
               " | Time: " + time;
    }
}