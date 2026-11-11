package ar.utn.cuentas;

/** Cuenta bancaria simple con depósitos y transferencias. */
public class Cuenta {
  private final String titular;
  private double saldo;

  public Cuenta(String titular, double saldoInicial) {
    if (titular == null || titular.isBlank()) {
      throw new IllegalArgumentException("El titular es obligatorio");
    }
    if (saldoInicial < 0) {
      throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
    }
    this.titular = titular;
    this.saldo = saldoInicial;
  }

  public void depositar(double monto) {
    validarMonto(monto);
    saldo += monto;
  }

  public void transferir(double monto, Cuenta destino) {
    validarMonto(monto);
    if (destino == null) {
      throw new IllegalArgumentException("La cuenta destino no es válida");
    }
    if (monto > saldo) {
      throw new IllegalStateException("Saldo insuficiente");
    }
    saldo -= monto;
    destino.saldo += monto;
  }

  private void validarMonto(double monto) {
    if (monto <= 0) {
      throw new IllegalArgumentException("El monto debe ser mayor a cero");
    }
  }

  public String getTitular() {
    return titular;
  }

  public double getSaldo() {
    return saldo;
  }
}
