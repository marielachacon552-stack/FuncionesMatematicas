package com.uth.funcionesmatematicas;

/** Encapsula dos operandos y las cuatro operaciones básicas. */
public class OperacionesMatematicas {
    private final double numero1;
    private final double numero2;

    public OperacionesMatematicas(double numero1, double numero2) {
        if (!Double.isFinite(numero1) || !Double.isFinite(numero2)) {
            throw new IllegalArgumentException("Los números deben ser finitos.");
        }
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public double sumar() { return numero1 + numero2; }
    public double restar() { return numero1 - numero2; }
    public double multiplicar() { return numero1 * numero2; }
    public double dividir() {
        if (numero2 == 0) {
            throw new ArithmeticException("No se puede dividir entre cero.");
        }
        return numero1 / numero2;
    }
}
