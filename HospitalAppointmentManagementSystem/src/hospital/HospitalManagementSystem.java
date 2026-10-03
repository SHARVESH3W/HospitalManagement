package hospital;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalManagementSystem {

    private Map<Integer, Patient> patients = new HashMap<>();
    private Map<Integer, Doctor> doctors = new HashMap<>();
    private Map<String, Appointment> appointments = new HashMap<>();

    private final List<LocalTime> slots = new ArrayList<>();

    public HospitalManagementSystem() {
        slots.add(LocalTime.of(9, 0));
        slots.add(LocalTime.of(10, 0));
        slots.add(LocalTime.of(11, 0));
        slots.add(LocalTime.of(14, 0));
        slots.add(LocalTime.of(15, 0));
        slots.add(LocalTime.of(16, 0));
    }

    // Register Patient
    public void registerPatient(int id, String name,
                                 int age, String phone) {

        if (patients.containsKey(id)) {
            throw new IllegalArgumentException(
                "Patient ID already exists.");
        }

        patients.put(id,
            new Patient(id, name, age, phone));

        System.out.println(
            "Patient registered successfully.");
    }

    // Add Doctor
    public void addDoctor(int id, String name,
                          String specialization) {

        if (doctors.containsKey(id)) {
            throw new IllegalArgumentException(
                "Doctor ID already exists.");
        }

        doctors.put(id,
            new Doctor(id, name, specialization));

        System.out.println(
            "Doctor added successfully.");
    }

    // Book Appointment
    public void bookAppointment(int patientId,
                                int doctorId,
                                LocalDate date,
                                LocalTime time) {

        Patient patient = patients.get(patientId);
        Doctor doctor = doctors.get(doctorId);

        if (patient == null) {
            throw new IllegalArgumentException(
                "Patient not found.");
        }

        if (doctor == null) {
            throw new IllegalArgumentException(
                "Doctor not found.");
        }

        if (!slots.contains(time)) {
            throw new IllegalArgumentException(
                "Invalid time slot.");
        }

        String key = createKey(doctorId, date, time);

        if (appointments.containsKey(key)) {
            throw new IllegalStateException(
                "This time slot is already booked.");
        }

        Appointment appointment =
            new Appointment(patientId, doctorId, date, time);

        appointments.put(key, appointment);

        patient.addAppointment(appointment);
        doctor.addAppointment(appointment);

        System.out.println(
            "Appointment booked successfully.");
    }

    // Cancel Appointment
    public void cancelAppointment(int doctorId,
                                  LocalDate date,
                                  LocalTime time) {

        String key = createKey(doctorId, date, time);

        Appointment appointment =
            appointments.remove(key);

        if (appointment == null) {
            throw new IllegalArgumentException(
                "Appointment not found.");
        }

        Patient patient =
            patients.get(appointment.getPatientId());

        Doctor doctor =
            doctors.get(doctorId);

        patient.removeAppointment(appointment);
        doctor.removeAppointment(appointment);

        System.out.println(
            "Appointment cancelled successfully.");
    }

    // View Available Slots
    public void viewAvailableSlots(int doctorId,
                                   LocalDate date) {

        if (!doctors.containsKey(doctorId)) {
            throw new IllegalArgumentException(
                "Doctor not found.");
        }

        System.out.println(
            "Available slots for Dr. "
            + doctors.get(doctorId).getName()
            + " on " + date + ":");

        boolean found = false;

        for (LocalTime time : slots) {

            String key =
                createKey(doctorId, date, time);

            if (!appointments.containsKey(key)) {
                System.out.println("- " + time);
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                "No available slots.");
        }
    }

    // View Doctor Schedule
    public void viewDoctorSchedule(int doctorId) {

        Doctor doctor = doctors.get(doctorId);

        if (doctor == null) {
            throw new IllegalArgumentException(
                "Doctor not found.");
        }

        System.out.println(
            "Schedule of Dr. "
            + doctor.getName() + ":");

        if (doctor.getSchedule().isEmpty()) {
            System.out.println(
                "No appointments.");
            return;
        }

        for (Appointment appointment :
             doctor.getSchedule()) {

            System.out.println(appointment);
        }
    }

    // View Patient History
    public void viewPatientHistory(int patientId) {

        Patient patient = patients.get(patientId);

        if (patient == null) {
            throw new IllegalArgumentException(
                "Patient not found.");
        }

        System.out.println(
            "Appointment history of "
            + patient.getName() + ":");

        if (patient.getHistory().isEmpty()) {
            System.out.println(
                "No appointments.");
            return;
        }

        for (Appointment appointment :
             patient.getHistory()) {

            System.out.println(appointment);
        }
    }

    public void showPatients() {

        System.out.println("Patients:");

        for (Patient patient : patients.values()) {
            System.out.println(patient);
        }
    }

    public void showDoctors() {

        System.out.println("Doctors:");

        for (Doctor doctor : doctors.values()) {
            System.out.println(doctor);
        }
    }

    private String createKey(int doctorId,
                             LocalDate date,
                             LocalTime time) {

        return doctorId + "-" + date + "-" + time;
    }
}