package TPO2022;

import java.io.IOException;
import java.util.Scanner;

public class Common {
  public static int InputInt(Scanner scanner, String text) {
    System.out.print(text + ": ");
    int value = scanner.nextInt();
    scanner.nextLine();
    return value;
  }

  public static String InputString(Scanner scanner, String text) {
    System.out.print(text + ": ");
    String value = scanner.nextLine();
    return value;
  }

  public static String[] InputStringArray(Scanner scanner, String text) {
    System.out.print(text + ": ");
    String value = scanner.nextLine();
    return value.split("\\s*,\\s*");
  }

  public static void PrintSeparator() {
    System.out.println("------------------------------------------------------");
  }

  public static void ClearConsole() {
    try {
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    } catch (IOException | InterruptedException ex) {}
  }
}
