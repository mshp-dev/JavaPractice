package clinic;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

class Clinic {

  String name;
  String type;
  String managerName;
  String managerSalary;
  String managerUser;
  String managerPass;
  List<Section> sections;
  List<String> histories;

  String infoHolder = "\n|{0}|{1}|{2}|{3}|{4}|{5}|";

  public Clinic() {
    sections = new ArrayList<Section>();
    histories = new ArrayList<String>();
  }

  @Override
  public String toString() {
    return ClinicInfo.fillWithSpaces(this.infoHolder, new String[] {this.name, this.type, this.managerName, this.managerSalary, this.managerUser, this.managerPass});
  }

  public String getAllSectionDetails() {
    String allSections = "";
    for (int i = 0; i < sections.size(); i++) {
      allSections += sections.get(i);
    }
    return allSections;
  }
}

class Section {

  String name;
  String type;
  String recepName;
  List<String> doctorNames;
  List<String> patientNames;

  String infoHolder = "SectionName: {0}\nSectionType: {1}\nRecepName: {2}\nDoctorNames: {3}\nPatientNames: {4}\n\n   ***  ***  ***   \n\n";

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
    return MessageFormat.format(this.infoHolder, (Object[]) new String[] {this.name, this.type, this.recepName, doctors, patients});
  }
}

class Receptionist {

  String name;
  String section;
  String salary;
  String user;
  String pass;
}

class Doctor {

  String name;
  String type;
  String section;
  String salary;
  String visit;
  String user;
  String pass;
}

class Patient {

  String name;
  String sicknessType;
  String wallet;
  String user;
  String pass;
}
