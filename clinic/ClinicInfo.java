package clinic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ClinicInfo {

  public static String rawInfoFilename;
  public static File rawInfoFile;
  public static Scanner input;
  public static List<Clinic> allClinics;

  public static final String clinicInfoHeader =
    "|  ClinicName   |  ClinicType   |  ManagerName  | ManagerSalary |  ManagerUser  |  ManagerPass  |";

  public static final String receptionistInfoHeader =
    "|   RecepName   | RecepSection  |  RecepSalary  |   RecepUser   |   RecepPass   |";

  public static final String doctorInfoHeader =
    "|  DoctorName   |  DoctorType   | DoctorSection | DoctorSalarry |  DoctorVisit  |  DoctorUser   |  DoctorPass   |";

  public static final String patientInfoHeader =
    "|  PatientName  |PatientSection | PatientSalary |  PatientUser  |  PatientPass  |";

  public static void main(String[] args) {
    input = new Scanner(System.in);
    allClinics = new ArrayList<Clinic>();
    clearConsole();
    System.out.println(
      "Hello, welcome to clinic info sorting and categorizing program."
    );
    System.out.println("1) Insert raw info file name");
    System.out.println("0) Exit");
    System.out.println();
    System.out.print("Select an option: ");
    String option1 = input.next();

    switch (option1) {
      case "1":
        System.out.print("Insert raw data file name (like RawInfo.txt): ");
        rawInfoFilename = input.next();
        try {
          rawInfoFile = new File(rawInfoFilename);
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("Make sure file is exist and readable!");
          System.exit(1);
        }
        while (true) {
          clearConsole();
          System.out.println("1) Print raw info file data");
          System.out.println("2) Process and categorize data");
          System.out.println("0) Exit");
          System.out.println();
          System.out.print("Select an option: ");
          String option2 = input.next();

          switch (option2) {
            case "1":
              printData(rawInfoFile);
              System.out.println("Press Enter to continue...");
              try {
                System.in.read();
              } catch (IOException e) {
                e.printStackTrace();
              }
              break;
            case "2":
              processData(rawInfoFile);
              System.out.println(
                String.format(
                  "Raw data from %s file successfully processed and saved in files.",
                  rawInfoFilename
                )
              );
              System.out.println("Press Enter to continue...");
              try {
                System.in.read();
              } catch (IOException e) {
                e.printStackTrace();
              }
              while (true) {
                String currentClinic;
                clearConsole();
                for (int i = 1; i <= allClinics.size(); i++) {
                  System.out.println(
                    String.format(
                      "%s) Go to %s info",
                      i,
                      allClinics.get(i - 1).name
                    )
                  );
                }
                System.out.println("0) Exit");
                System.out.println();
                System.out.print("Select an option: ");
                String option3 = input.next();

                if (option3 == "0") {
                  input.close();
                  System.exit(0);
                } else {
                  currentClinic =
                    allClinics.get(Integer.parseInt(option3) - 1).name;
                  loop:while (true) {
                    clearConsole();
                    System.out.println(
                      String.format("1) Print %s clinic info", currentClinic)
                    );
                    System.out.println(
                      String.format("2) Print %s sections info", currentClinic)
                    );
                    System.out.println(
                      String.format("3) Print %s doctors info", currentClinic)
                    );
                    System.out.println(
                      String.format(
                        "4) Print %s receptionists info",
                        currentClinic
                      )
                    );
                    System.out.println(
                      String.format("5) Print %s patients info", currentClinic)
                    );
                    System.out.println(
                      String.format("6) Print %s history info", currentClinic)
                    );
                    System.out.println("7) Go back");
                    System.out.println("0) Exit");
                    System.out.println();
                    System.out.print("Select an option: ");
                    String option4 = input.next();

                    switch (option4) {
                      case "0":
                        input.close();
                        System.exit(0);
                      case "1":
                        printData(
                          new File(
                            String.format("%s/ClinicInfo.txt", currentClinic)
                          )
                        );
                        System.out.println("Press Enter to continue...");
                        try {
                          System.in.read();
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                        break;
                      case "2":
                        printData(
                          new File(
                            String.format("%s/SectionInfo.txt", currentClinic)
                          )
                        );
                        System.out.println("Press Enter to continue...");
                        try {
                          System.in.read();
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                        break;
                      case "3":
                        printData(
                          new File(
                            String.format("%s/DoctorInfo.txt", currentClinic)
                          )
                        );
                        System.out.println("Press Enter to continue...");
                        try {
                          System.in.read();
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                        break;
                      case "4":
                        printData(
                          new File(
                            String.format(
                              "%s/ReceptionistInfo.txt",
                              currentClinic
                            )
                          )
                        );
                        System.out.println("Press Enter to continue...");
                        try {
                          System.in.read();
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                        break;
                      case "5":
                        printData(
                          new File(
                            String.format("%s/PatientInfo.txt", currentClinic)
                          )
                        );
                        System.out.println("Press Enter to continue...");
                        try {
                          System.in.read();
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                        break;
                      case "6":
                        printData(
                          new File(
                            String.format("%s/History.txt", currentClinic)
                          )
                        );
                        System.out.println("Press Enter to continue...");
                        try {
                          System.in.read();
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                        break;
                      case "7":
                        break loop;
                    }
                  }
                }
              }
            case "0":
              input.close();
              System.exit(0);
            default:
              System.out.println("Wrong input, try again.");
              break;
          }
        }
      default:
        input.close();
        System.exit(0);
    }
  }

  public static void processData(File rawData) {
    try {
      Scanner sc = new Scanner(rawData);
      String line;
      String[] temp;
      Clinic clinic = new Clinic();

      while (sc.hasNextLine()) {
        line = sc.nextLine();
        if (line.startsWith("*")) { // pattern to find clinic lines
          line = line.replace("*", "");
          temp = separateStrings(line, "-");
          clinic = new Clinic();
          clinic.name = temp[0];
          clinic.type = temp[1];

          File dir = new File(clinic.name); // Clinic name -> we'll make a dir for each one
          if (!dir.exists()) {
            dir.mkdir();
          }

          File clinicInfo = new File(
            String.format("%s/ClinicInfo.txt", clinic.name)
          );
          clinicInfo.createNewFile();
          Files.write(clinicInfo.toPath(), clinicInfoHeader.getBytes());

          File sectionInfo = new File(
            String.format("%s/SectionInfo.txt", clinic.name)
          );
          sectionInfo.createNewFile();
          // Files.write(Paths.get(String.format("%s/SectionInfo.txt", clinic.name)), sectionInfoHolder.getBytes());

          File receptionistInfo = new File(
            String.format("%s/ReceptionistInfo.txt", clinic.name)
          );
          receptionistInfo.createNewFile();
          Files.write(
            receptionistInfo.toPath(),
            receptionistInfoHeader.getBytes()
          );

          File doctorInfo = new File(
            String.format("%s/DoctorInfo.txt", clinic.name)
          );
          doctorInfo.createNewFile();
          Files.write(doctorInfo.toPath(), doctorInfoHeader.getBytes());

          File patientInfo = new File(
            String.format("%s/PatientInfo.txt", clinic.name)
          );
          patientInfo.createNewFile();
          Files.write(patientInfo.toPath(), patientInfoHeader.getBytes());

          File historyInfo = new File(
            String.format("%s/History.txt", clinic.name)
          );
          historyInfo.createNewFile();
          // Files.write(historyInfo.toPath(), historyInfoHeader.getBytes());
        } else if (line.startsWith(" M_")) { // pattern to find manager lines
          line = line.replace(" M_", "");
          temp = separateStrings(line, "-"); // manager -> name-salary-user-pass
          if (temp[0] != "") {
            clinic.manager.name = temp[0];
          } else {
            clinic.manager.name = null;
          }
          if (temp[1] != "") {
            clinic.manager.salary = temp[1];
          } else {
            clinic.manager.salary = null;
          }
          if (temp[2] != "") {
            clinic.manager.user = temp[2];
          } else {
            clinic.manager.user = null;
          }
          if (temp[3] != "") {
            clinic.manager.pass = temp[3];
          } else {
            clinic.manager.pass = null;
          }
        } else if (line.startsWith(" &")) { // pattern to find section lines
          Section section = new Section();
          line = line.replace(" &", "");
          temp = separateStrings(line, "-"); // section -> name-type-receptionist-@doctor...-#patient...
          for (int i = 0; i < temp.length; i++) {
            if (i == 0) {
              section.name = temp[i];
            } else if (i == 1) {
              section.type = temp[i];
            } else if (i == 2) {
              section.recepName = temp[i];
            } else if (temp[i].startsWith("@")) {
              temp[i] = temp[i].replace("@", "");
              section.doctorNames.add(temp[i]);
            } else if (temp[i].startsWith("#")) {
              temp[i] = temp[i].replace("#", "");
              section.patientNames.add(temp[i]);
            }
          }
          clinic.sections.add(section);
        } else if (line.startsWith(" R_")) { // pattern to find receptionist lines
          Receptionist recep = new Receptionist();
          line = line.replace(" R_", "");
          temp = separateStrings(line, "-"); // receptionist -> name-section-salary-user-pass
          if (temp[0] != "") {
            recep.name = temp[0];
          } else {
            recep.name = null;
          }
          if (temp[1] != "") {
            recep.section = temp[1];
          } else {
            recep.section = null;
          }
          if (temp[2] != "") {
            recep.salary = temp[2];
          } else {
            recep.salary = null;
          }
          if (temp[3] != "") {
            recep.user = temp[3];
          } else {
            recep.user = null;
          }
          if (temp[4] != "") {
            recep.pass = temp[4];
          } else {
            recep.pass = null;
          }
          clinic.receptionists.add(recep);
        } else if (line.startsWith(" @")) { // pattern to find doctor lines
          Doctor doctor = new Doctor();
          line = line.replace(" @", "");
          temp = separateStrings(line, "-"); // doctor -> name-type-section-salary-visit-user-pass
          if (temp[0] != "") {
            doctor.name = temp[0];
          } else {
            doctor.name = null;
          }
          if (temp[1] != "") {
            doctor.type = temp[1];
          } else {
            doctor.type = null;
          }
          if (temp[2] != "") {
            doctor.section = temp[2];
          } else {
            doctor.section = null;
          }
          if (temp[3] != "") {
            doctor.salary = temp[3];
          } else {
            doctor.salary = null;
          }
          if (temp[4] != "") {
            doctor.visit = temp[4];
          } else {
            doctor.visit = null;
          }
          if (temp[5] != "") {
            doctor.user = temp[5];
          } else {
            doctor.user = null;
          }
          if (temp[6] != "") {
            doctor.pass = temp[6];
          } else {
            doctor.pass = null;
          }
          clinic.doctors.add(doctor);
        } else if (line.startsWith(" #")) { // pattern to find patient lines
          Patient patient = new Patient();
          line = line.replace(" #", "");
          temp = separateStrings(line, "-"); // patient -> name-sicknessType-wallet-user-pass
          if (temp[0] != "") {
            patient.name = temp[0];
          } else {
            patient.name = null;
          }
          if (temp[1] != "") {
            patient.sicknessType = temp[1];
          } else {
            patient.sicknessType = null;
          }
          if (temp[2] != "") {
            patient.wallet = temp[2];
          } else {
            patient.wallet = null;
          }
          if (temp[3] != "") {
            patient.user = temp[3];
          } else {
            patient.user = null;
          }
          if (temp[4] != "") {
            patient.pass = temp[4];
          } else {
            patient.pass = null;
          }
          clinic.patients.add(patient);
        } else if (line.startsWith(" ++")) { // pattern to find history lines
          while (sc.hasNextLine()) {
            line = sc.nextLine();
            if (line.startsWith(" @")) {
              temp = separateStrings(line, "-"); // histories -> @doctor-#patient-date
              temp[0] = temp[0].replace("@", "");
              temp[1] = temp[1].replace("#", "");
              clinic.histories.add(
                String.format(
                  "Dr.%s visited %s on %s\n",
                  temp[0],
                  temp[1],
                  temp[2]
                )
              );
            } else if (line.length() <= 1) {
              break;
            }
          }
          allClinics.add(clinic);
          writeToFile(clinic);
        }
      }
      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Press Enter to exit...");
      try {
        System.in.read();
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
  }

  public static void writeToFile(Clinic cl) {
    try {
      File clinicInfo = new File(String.format("%s/ClinicInfo.txt", cl.name));
      File sectionInfo = new File(String.format("%s/SectionInfo.txt", cl.name));
      File receptionistInfo = new File(
        String.format("%s/ReceptionistInfo.txt", cl.name)
      );
      File doctorInfo = new File(String.format("%s/DoctorInfo.txt", cl.name));
      File patientInfo = new File(String.format("%s/PatientInfo.txt", cl.name));
      File historyInfo = new File(String.format("%s/History.txt", cl.name));
      List<String> list = new ArrayList<String>();
      list.add(cl.toString());
      Files.write(
        clinicInfo.toPath(),
        list,
        StandardCharsets.UTF_8,
        StandardOpenOption.APPEND
      );

      list = new ArrayList<String>();
      list.add(cl.getAllSectionDetails());
      Files.write(
        sectionInfo.toPath(),
        list,
        StandardCharsets.UTF_8,
        StandardOpenOption.APPEND
      );

      list = new ArrayList<String>();
      list.add(cl.getAllReceptionistDetails());
      Files.write(
        receptionistInfo.toPath(),
        list,
        StandardCharsets.UTF_8,
        StandardOpenOption.APPEND
      );

      list = new ArrayList<String>();
      list.add(cl.getAllDoctorDetails());
      Files.write(
        doctorInfo.toPath(),
        list,
        StandardCharsets.UTF_8,
        StandardOpenOption.APPEND
      );

      list = new ArrayList<String>();
      list.add(cl.getAllPatientDetails());
      Files.write(
        patientInfo.toPath(),
        list,
        StandardCharsets.UTF_8,
        StandardOpenOption.APPEND
      );

      list = new ArrayList<String>();
      list.add(cl.getAllHistories());
      Files.write(
        historyInfo.toPath(),
        list,
        StandardCharsets.UTF_8,
        StandardOpenOption.APPEND
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String fillWithSpaces(String placeHolder, String[] args) {
    String[] temp = args;
    for (int i = 0; i < temp.length; i++) {
      int len;
      if (temp[i] != null) {
        len = temp[i].length();
      } else {
        len = 4;
      }
      switch (len) {
        case 1:
          temp[i] = "       " + temp[i] + "       ";
          break;
        case 2:
          temp[i] = "      " + temp[i] + "       ";
          break;
        case 3:
          temp[i] = "      " + temp[i] + "      ";
          break;
        case 4:
          temp[i] = "     " + temp[i] + "      ";
          break;
        case 5:
          temp[i] = "     " + temp[i] + "     ";
          break;
        case 6:
          temp[i] = "    " + temp[i] + "     ";
          break;
        case 7:
          temp[i] = "    " + temp[i] + "    ";
          break;
        case 8:
          temp[i] = "   " + temp[i] + "    ";
          break;
        case 9:
          temp[i] = "   " + temp[i] + "   ";
          break;
        case 10:
          temp[i] = "  " + temp[i] + "   ";
          break;
        case 11:
          temp[i] = "  " + temp[i] + "  ";
          break;
        case 12:
          temp[i] = " " + temp[i] + "  ";
          break;
        case 13:
          temp[i] = " " + temp[i] + " ";
          break;
        case 14:
          temp[i] = "" + temp[i] + " ";
          break;
        default:
          break;
      }
    }
    return MessageFormat.format(placeHolder, (Object[]) temp);
  }

  public static String[] separateStrings(String line, String separator) {
    StringBuilder sb;
    String[] temp = line.split(separator);
    for (int i = 0; i < temp.length; i++) {
      if (temp[i].length() > 1) {
        if (
          temp[i].charAt(0) == ' ' ||
          temp[i].charAt(0) == '@' ||
          temp[i].charAt(0) == '#'
        ) {
          sb = new StringBuilder(temp[i]);
          sb.deleteCharAt(0);
          temp[i] = sb.toString();
        }
        if (temp[i].charAt(temp[i].length() - 1) == ' ') {
          sb = new StringBuilder(temp[i]);
          sb.deleteCharAt(sb.length() - 1);
          temp[i] = sb.toString();
        }
      } else {
        temp[i] = null;
      }
    }
    return temp;
  }

  public static void printData(File data) {
    try {
      Scanner sc = new Scanner(data);
      while (sc.hasNextLine()) {
        System.out.println(sc.nextLine());
      }
      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void clearConsole() {
    try {
      String operatingSystem = System.getProperty("os.name"); //Check the current operating system
      if (operatingSystem.contains("Windows")) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
        Process startProcess = pb.inheritIO().start();
        startProcess.waitFor();
      } else {
        ProcessBuilder pb = new ProcessBuilder("clear");
        Process startProcess = pb.inheritIO().start();
        startProcess.waitFor();
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void print(String text) {
    System.out.println(text);
  }
}
