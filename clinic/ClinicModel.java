package clinic;

import java.util.ArrayList;
import java.util.List;

class Clinic {

  String name;
  String type;
  Manager manager;
  List<Section> sections;
  List<Receptionist> receptionists;
  List<Doctor> doctors;
  List<Patient> patients;
  List<String> histories;

  String infoHolder = "\n|{0}|{1}|{2}|{3}|{4}|{5}|";

  public Clinic() {
    manager = new Manager();
    sections = new ArrayList<Section>();
    histories = new ArrayList<String>();
    receptionists = new ArrayList<Receptionist>();
    doctors = new ArrayList<Doctor>();
    patients = new ArrayList<Patient>();
  }

  public void print() {
    System.out.println(
      String.format(
        "%s - %s - %s - %s",
        this.name,
        this.manager.name,
        this.sections.get(0).name,
        this.histories
      )
    );
  }

  @Override
  public String toString() {
    return ClinicInfo.fillWithSpaces(
      this.infoHolder,
      new String[] {
        this.name,
        this.type,
        this.manager.name,
        this.manager.salary,
        this.manager.user,
        this.manager.pass,
      }
    );
  }

  public String getAllSectionDetails() {
    String allSections = "";
    for (int i = 0; i < sections.size(); i++) {
      allSections += sections.get(i);
    }
    return allSections;
  }

  public String getAllReceptionistDetails() {
    String allReceptionists = "";
    for (int i = 0; i < receptionists.size(); i++) {
      allReceptionists += receptionists.get(i);
    }
    return allReceptionists;
  }

  public String getAllDoctorDetails() {
    String allDoctors = "";
    for (int i = 0; i < doctors.size(); i++) {
      allDoctors += doctors.get(i);
    }
    return allDoctors;
  }

  public String getAllPatientDetails() {
    String allPatients = "";
    for (int i = 0; i < patients.size(); i++) {
      allPatients += patients.get(i);
    }
    return allPatients;
  }

  public String getAllHistories() {
    String allHistories = "";
    for (int i = 0; i < histories.size(); i++) {
      allHistories += histories.get(i);
    }
    return allHistories;
  }
}

class Manager {

  String name;
  String salary;
  String user;
  String pass;
}

class Section {

  String name;
  String type;
  String recepName;
  List<String> doctorNames;
  List<String> patientNames;

  String infoHolder =
    "SectionName: %s\nSectionType: %s\nRecepName: %s\nDoctorNames: %s\nPatientNames: %s\n\n   ***  ***  ***   \n\n";

  public Section() {
    doctorNames = new ArrayList<String>();
    patientNames = new ArrayList<String>();
  }

  @Override
  public String toString() {
    String doctors = "";
    for (String doctor : doctorNames) {
      doctors += doctor + ", ";
    }
    String patients = "";
    for (String patient : patientNames) {
      patients += patient + ", ";
    }
    return String.format(
      this.infoHolder,
      (Object[]) new String[] {
        this.name,
        this.type,
        this.recepName,
        doctors,
        patients,
      }
    );
  }
}

class Receptionist {

  String name;
  String section;
  String salary;
  String user;
  String pass;

  String infoHolder = "\n|{0}|{1}|{2}|{3}|{4}|";

  @Override
  public String toString() {
    return ClinicInfo.fillWithSpaces(
      this.infoHolder,
      new String[] {
        this.name,
        this.section,
        this.salary,
        this.user,
        this.pass,
      }
    );
  }
}

class Doctor {

  String name;
  String type;
  String section;
  String salary;
  String visit;
  String user;
  String pass;

  String infoHolder = "\n|{0}|{1}|{2}|{3}|{4}|{5}|{6}|";

  @Override
  public String toString() {
    return ClinicInfo.fillWithSpaces(
      this.infoHolder,
      new String[] {
        this.name,
        this.type,
        this.section,
        this.salary,
        this.visit,
        this.user,
        this.pass,
      }
    );
  }
}

class Patient {

  String name;
  String sicknessType;
  String wallet;
  String user;
  String pass;

  String infoHolder = "\n|{0}|{1}|{2}|{3}|{4}|";

  @Override
  public String toString() {
    return ClinicInfo.fillWithSpaces(
      this.infoHolder,
      new String[] {
        this.name,
        this.sicknessType,
        this.wallet,
        this.user,
        this.pass,
      }
    );
  }
}
