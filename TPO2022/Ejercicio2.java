package TPO2022;
import apis.*;

import java.util.Scanner;

public class Ejercicio2 {
  // TEST:
  // 6
  // O, P+3, P, O, P, P+3, P+3, P+3, P3, O, O, O, P, P+3, P3, P1, O, P, P3, O, P1, P1, P3, O, P, P1, P3, O, P3, P, P+3, P1, P1, P, P, P, O, P3, O, P

  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    AdministradorDeColasTDA admin = new AdministradorDeColasTDA(); 
    admin.Inicializar(Common.InputInt(scanner, "Ingrese cantidad de puestos de atención"));

    for (String s : Common.InputStringArray(scanner, "Ingrese las solicitudes (separadas por coma)")) 
      admin.Acolar(GetDemoraPorNomenclatura(s));

    Common.ClearConsole();
    PrintTurno(admin.Programacion(), admin);

    scanner.close();
  }

  private static int GetDemoraPorNomenclatura(String nomenclatura) throws Exception {
    switch(nomenclatura.toUpperCase()) {
      case "P+3": return 50;
      case "O": case "P3": return 30;
      case "P": return 20;
      case "P1": return 10;
      default: throw new Exception("Fallo al leer una solicitud. Nomenclatura utilizada: " + nomenclatura);
    }
  }

  private static void PrintTurno(ColaPrioridadTDA colaPrioridadTDA, AdministradorDeColasTDA admin) {
    while(!colaPrioridadTDA.colaVacia()) {
      Common.PrintSeparator();
      System.out.println("Ticket: " + colaPrioridadTDA.primero() + " | Demora: " + colaPrioridadTDA.prioridad() + " | Puesto: " + admin.PuestoDelElemento(colaPrioridadTDA.primero()));
      colaPrioridadTDA.desacolar();

      // NOTA: El puesto está devolviendo -1... por algún motivo, al utilizar el método "colaVacia" (línea 82 del archivo AdministradorDeColasTDA.java), en una cola con contenido, se está devolivendo true, como si estuviera vacía... Probamos debuggear y realizar varias pruebas y no logramos comprender porqué.
    }
  }
}
