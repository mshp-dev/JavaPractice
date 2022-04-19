package clinic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ClinicInfo {

  public static String rawInfoFilename;
  public static File rawInfoFile;
  public static Scanner input = new Scanner(System.in);
  public static List<Clinic> allClinics = new ArrayList<Clinic>();
  public static List<Doctor> allDoctors = new ArrayList<Doctor>();
  public static List<Receptionist> allReceptionists = new ArrayList<Receptionist>();
  public static List<Patient> allPatients = new ArrayList<Patient>();

  public static final String clinicInfoHeader = "|  ClinicName   |  ClinicType   |  ManagerName  |ManagerSalarry |  ManagerUser  |  ManagerPass  |";
  
  public static final String receptionistInfoHeader = "|   RecepName   | RecepSection  | RecepSalarry  |   RecepUser   |   RecepPass   |";
  public static String receptionistInfoHolder = "\n|{0}|{1}|{2}|{3}|{4}|";

  public static final String doctorInfoHeader = "|  DoctorName   |  DoctorType   | DoctorSection | DoctorSalarry |  DoctorVisit  |  DoctorUser   |  DoctorPass   |";
  public static String doctorInfoHolder = "\n|{0}|{1}|{2}|{3}|{4}|{5}|{6}|";

  public static final String patientInfoHeader = "|  PatientName  |PatientSection | PatientSalarry  |   PatientUser   |   PatientPass   |";
  public static String patientInfoHolder = "\n|{0}|{1}|{2}|{3}|{4}|";

  public static void main(String[] args) {
    clearConsole();
    System.out.println(
      "Hello, welcome to clinic info sorting and categorizing program."
    );
    System.out.println("1) Insert raw info file name");
    System.out.println("0) Exit");
    System.out.println();
    System.out.print("Select an option: ");
    String option1 = input.nextLine();

    switch (option1) {
      case "1":
        System.out.print("Insert raw data file name (like RawInfo.txt): ");
        rawInfoFilename = input.nextLine();
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
          String option2 = input.nextLine();

          switch (option2) {
            case "1":
              printRawData(rawInfoFile);
              System.out.println("Press Enter key to continue...");
              try {
                System.in.read();
              } catch (IOException e) {
                e.printStackTrace();
              }
              break;
            case "2":
              processData(rawInfoFile);
              System.out.println("Press Enter key to continue...");
              try {
                System.in.read();
              } catch (IOException e) {
                e.printStackTrace();
              }
              while (true) {
                clearConsole();
                System.out.println("1) Print clinic info");
                System.out.println("2) Print sections info");
                System.out.println("3) Print doctors info");
                System.out.println("4) Print receptionists info");
                System.out.println("5) Print patients info");
                System.out.println("6) Print history info");
                System.out.println("0) Exit");
                System.out.println();
                System.out.print("Select an option: ");
                String option3 = input.nextLine();

                switch (option3) {
                  case "1":
                    break;
                  case "2":
                    break;
                  case "3":
                    break;
                  case "4":
                    break;
                  case "5":
                    break;
                  case "0":
                    input.close();
                    System.exit(0);
                  default:
                    System.out.println("Wrong input, try again.");
                    break;
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
    Pattern clinicPattern = Pattern.compile("\\*\\*.+"); // pattern to find clinic lines
    Pattern sectionPattern = Pattern.compile("&.+"); // pattern to find section lines
    Pattern managerPattern = Pattern.compile("M_.+"); // pattern to find manager lines
    Pattern receptionistPattern = Pattern.compile("R_.+"); // pattern to find receptionist lines
    Pattern doctorPattern = Pattern.compile("\\s\\s@.+"); // pattern to find doctor lines
    Pattern patientPattern = Pattern.compile("\\s\\s#.+"); // pattern to find patient lines
    Pattern historyPattern = Pattern.compile("\\+.+"); // pattern to find history lines

    try {
      Scanner sc = new Scanner(rawData);
      String line;
      String[] temp;
      File clinicInfo = new File(".temp");
      File sectionInfo = new File(".temp");
      File receptionistInfo = new File(".temp");
      File doctorInfo = new File(".temp");
      File patientInfo = new File(".temp");
      Clinic clinic = new Clinic();
      
      while (sc.hasNextLine()) {
        line = sc.nextLine();
        if (line.startsWith("*")) {
          line = line.replace("*", "");
          temp = separateStrings(line, "-");
          clinic.sections = new ArrayList<Section>();
          clinic.name = temp[0];
          clinic.type = temp[1];
          
          File dir = new File(clinic.name); // Clinic name -> we'll make a dir for each one
          if (!dir.exists()) {
            dir.mkdir();
          }
          
          clinicInfo = new File(String.format("%s/ClinicInfo.txt", clinic.name));
          clinicInfo.createNewFile();
          Files.write(clinicInfo.toPath(), clinicInfoHeader.getBytes());

          sectionInfo = new File(String.format("%s/SectionInfo.txt", clinic.name));
          sectionInfo.createNewFile();
          // Files.write(Paths.get(String.format("%s/SectionInfo.txt", clinic.name)), sectionInfoHolder.getBytes());

          receptionistInfo = new File(String.format("%s/ReceptionistInfo.txt", clinic.name));
          receptionistInfo.createNewFile();
          Files.write(receptionistInfo.toPath(), receptionistInfoHeader.getBytes());

          doctorInfo = new File(String.format("%s/DoctorInfo.txt", clinic.name));
          doctorInfo.createNewFile();
          Files.write(doctorInfo.toPath(), doctorInfoHeader.getBytes());

          patientInfo = new File(String.format("%s/PatientInfo.txt", clinic.name));
          patientInfo.createNewFile();
          Files.write(patientInfo.toPath(), patientInfoHeader.getBytes());

        } else if (line.startsWith(" M_")) {
          line = line.replace(" M_", "");
          temp = separateStrings(line, "-"); // manager -> name-salary-user-pass
          if (temp[0] != "") {
            clinic.managerName = temp[0];
          } else {
            clinic.managerName = null;
          }
          if (temp[1] != "") {
            clinic.managerSalary = temp[1];
          } else {
            clinic.managerSalary = null;
          }
          if (temp[2] != "") {
            clinic.managerUser = temp[2];
          } else {
            clinic.managerUser = null;
          }
          if (temp[3] != "") {
            clinic.managerPass = temp[3];
          } else {
            clinic.managerPass = null;
          }
          List<String> list = new ArrayList<String>();
          list.add(clinic.toString());
          Files.write(clinicInfo.toPath(), list, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } else if (line.startsWith(" &")) {
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
              section.doctorNames.add(temp[i]);
            } else if (temp[i].startsWith("#")) {
              section.patientNames.add(temp[i]);
            }
            clinic.sections.add(section);
          }
          List<String> list = new ArrayList<String>();
          list.add(clinic.getAllSectionDetails());
          Files.write(sectionInfo.toPath(), list, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        }/* else if (line.startsWith(" @")) {
          //
        } else if (line.startsWith(" #")) {
          //
        } else if (line.startsWith(" ++")) {
          //
        }*/
      }
      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String fillWithSpaces(String placeHolder, String[] args) {
    String[] temp = args;
    for (int i = 0; i < temp.length; i++) {
      switch (temp[i].length()) {
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
    return MessageFormat.format(placeHolder, (Object[])temp);
  }

  public static String[] separateStrings(String line, String separator) {
    StringBuilder sb;
    String[] temp = line.split(separator);
    for (int i = 0; i < temp.length; i++) {
      if (temp[i].length() > 1) {
        if (temp[i].charAt(0) == ' ' || temp[i].charAt(0) == '@' || temp[i].charAt(0) == '#') {
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

  public static void printRawData(File rawData) {
    try {
      Scanner sc = new Scanner(rawData);
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
