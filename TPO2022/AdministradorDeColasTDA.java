package TPO2022;
import apis.*;
import impl.*;

import java.util.Arrays;

public class AdministradorDeColasTDA {
  public static int idCounter = 0;
  private ColaPrioridadTDA[] colas;
  private int[] esperaDePuestos;

  public AdministradorDeColasTDA() {}

  public void Inicializar(int cantPuestos) {
    colas = new ColaPrioridadDA[cantPuestos];
    esperaDePuestos = new int[cantPuestos];
    
    for (int i = 0; i < cantPuestos; i++) {
      ColaPrioridadTDA puesto = new ColaPrioridadDA();
      puesto.inicializarCola();
      colas[i] = puesto;
      esperaDePuestos[i] = 0;
    }
  }

  public int Acolar(int tiempoDeAtencion) {
    int id = ++idCounter;
    int menorDemoraId = GetColaMenorDemora();

    ColaPrioridadTDA colaDeMenorDemora = colas[menorDemoraId];
    colaDeMenorDemora.acolarPrioridad(id, tiempoDeAtencion);

    esperaDePuestos[menorDemoraId] += tiempoDeAtencion; 

    return id;
  }

  public void Desacolar() {
    int mayorDemoraId = GetColaMayorDemora();
    ColaPrioridadTDA colaDeMayorDemora = colas[mayorDemoraId];
    esperaDePuestos[mayorDemoraId] -= colaDeMayorDemora.prioridad();
    colaDeMayorDemora.desacolar();
  }

  public ColaPrioridadTDA Programacion() {
    ColaPrioridadTDA colaCompleta = new ColaPrioridadAO();
    colaCompleta.inicializarCola();

    for (ColaPrioridadTDA puesto : GetCopiaDeColas()) {
      int demora = 0;
      while(!puesto.colaVacia()) {
        colaCompleta.acolarPrioridad(puesto.primero(), demora);
        demora += puesto.prioridad();
        puesto.desacolar();
      }
    }

    return colaCompleta;
  }

  public int CantidadDeColas() {
    return colas.length;
  }

  public int Primero() {
    return colas[GetColaMayorDemora()].primero();
  }

  public int TiempoEstimado() {
    return colas[GetColaMayorDemora()].prioridad();
  }

  public int PuestoDelProximoElemento() {
    return GetColaMayorDemora() + 1;
  }

  public int PuestoDelElemento(int idElemento) {
    ColaPrioridadTDA[] copiaColas = GetCopiaDeColas();
    int idPuesto = -1;
    int index = 0;

    while(idPuesto < 0 && index < copiaColas.length) {
      while(!copiaColas[index].colaVacia() && idPuesto < 0) {
        if (copiaColas[index].primero() == idElemento) idPuesto = index + 1;
        else copiaColas[index].desacolar();
      }

      index += 1;
    }

    return idPuesto;
  }

  public ColaPrioridadTDA Elementos() {
    ColaPrioridadTDA colaCompleta = new ColaPrioridadDA();
    colaCompleta.inicializarCola();

    for (ColaPrioridadTDA puesto : GetCopiaDeColas()) {
      while(!puesto.colaVacia()) {
        colaCompleta.acolarPrioridad(puesto.primero(), puesto.prioridad());
        puesto.desacolar();
      }
    }

    return colaCompleta;
  }

  private int GetColaMayorDemora() {
    int index = 0;
    int value = Integer.MIN_VALUE;

    for (int i = 0; i < esperaDePuestos.length; i++)
      if (value > esperaDePuestos[i]) {
        value = esperaDePuestos[i];
        index = i;
      }

    return index;
  }

  private int GetColaMenorDemora() {
    int index = 0;
    int value = Integer.MAX_VALUE;

    for (int i = 0; i < esperaDePuestos.length; i++)
      if (value > esperaDePuestos[i]) {
        value = esperaDePuestos[i];
        index = i;
      }
        
    return index;
  }

  private ColaPrioridadTDA[] GetCopiaDeColas() {
    return Arrays.copyOf(colas, colas.length);
  }
}
