package ar.utn.cuentas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CuentaTest {

  @Test
  void depositar_montoValido_aumentaSaldo() {
    // Arrange
    Cuenta cuenta = new Cuenta("Ana", 1000);
    // Act
    cuenta.depositar(500);
    // Assert
    assertEquals(1500, cuenta.getSaldo(), 0.001);
  }

  @Test
  void transferir_montoValido_mueveElSaldo() {
    Cuenta origen = new Cuenta("Ana", 1000);
    Cuenta destino = new Cuenta("Luis", 0);

    origen.transferir(400, destino);

    assertEquals(600, origen.getSaldo(), 0.001);
    assertEquals(400, destino.getSaldo(), 0.001);
  }

  @Test
  void transferir_montoNegativo_lanzaExcepcion() {
    Cuenta origen = new Cuenta("Ana", 1000);
    Cuenta destino = new Cuenta("Luis", 0);

    assertThrows(IllegalArgumentException.class, () -> origen.transferir(-50, destino));
  }

  @Test
  void transferir_saldoInsuficiente_lanzaExcepcion() {
    Cuenta origen = new Cuenta("Ana", 100);
    Cuenta destino = new Cuenta("Luis", 0);

    assertThrows(IllegalStateException.class, () -> origen.transferir(500, destino));
  }

  @Test
  void transferir_destinoNulo_lanzaExcepcion() {
    Cuenta cuenta = new Cuenta("Ana", 1000);

    assertThrows(IllegalArgumentException.class, () -> cuenta.transferir(10, null));
  }

  @Test
  void crear_titularVacio_lanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> new Cuenta(" ", 0));
  }

  @Test
  void crear_titularNulo_lanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> new Cuenta(null, 0));
  }

  @Test
  void crear_saldoNegativo_lanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> new Cuenta("Ana", -1));
  }

  @Test
  void crear_datosValidos_guardaTitular() {
    Cuenta cuenta = new Cuenta("Ana", 0);

    assertEquals("Ana", cuenta.getTitular());
  }
}
