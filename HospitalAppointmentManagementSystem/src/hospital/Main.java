package hospital;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        HospitalManagementSystem hospital =
            new HospitalManagementSystem();

        // Register Patients
        hospital.registerPatient(
            101, "Rahul", 25, "9876543210");

        hospital.registerPatient(
            102, "Priya", 30, "9876501234");

        // Add Doctors
        hospital.addDoctor(
            1, "Kumar", "Cardiologist");

        hospital.addDoctor(
            2, "Anitha", "Dermatologist");

        // Book Appointments
        try {

            hospital.bookAppointment(
                101,
                1,
                LocalDate.of(2026, 10, 5),
                LocalTime.of(10, 0));

            hospital.bookAppointment(
                102,
                2,
                LocalDate.of(2026, 10, 5),
                LocalTime.of(11, 0));

        } catch (Exception e) {

            System.out.println(
                "Error: " + e.getMessage());
        }

        System.out.println(
            "\n===== HOSPITAL APPOINTMENT MANAGEMENT SYSTEM =====");

        hospital.showPatients();
        hospital.showDoctors();

        // Available Slots
        System.out.println("\n--- Available Slots ---");

        hospital.viewAvailableSlots(
            1,
            LocalDate.of(2026, 10, 5));

        // Doctor Schedule
        System.out.println("\n--- Doctor Schedule ---");

        hospital.viewDoctorSchedule(1);

        // Patient History
        System.out.println("\n--- Patient History ---");

        hospital.viewPatientHistory(101);

        // Test Duplicate Booking
        System.out.println(
            "\n--- Testing Duplicate Booking ---");

        try {

            hospital.bookAppointment(
                102,
                1,
                LocalDate.of(2026, 10, 5),
                LocalTime.of(10, 0));

        } catch (Exception e) {

            System.out.println(
                "Error handled: " + e.getMessage());
        }

        // Cancel Appointment
        System.out.println(
            "\n--- Cancelling Appointment ---");

        hospital.cancelAppointment(
            1,
            LocalDate.of(2026, 10, 5),
            LocalTime.of(10, 0));

        // Check slots again
        System.out.println(
            "\n--- Available Slots After Cancellation ---");

        hospital.viewAvailableSlots(
            1,
            LocalDate.of(2026, 10, 5));
    }
}