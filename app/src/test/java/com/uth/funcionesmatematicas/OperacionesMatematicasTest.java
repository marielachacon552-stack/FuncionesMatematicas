package com.uth.funcionesmatematicas;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class OperacionesMatematicasTest {
    @Test public void operacionesConDecimalesYNegativos() {
        OperacionesMatematicas operaciones = new OperacionesMatematicas(-7.5, 2.5);
        assertEquals(-5, operaciones.sumar(), 0.000001);
        assertEquals(-10, operaciones.restar(), 0.000001);
        assertEquals(-18.75, operaciones.multiplicar(), 0.000001);
        assertEquals(-3, operaciones.dividir(), 0.000001);
    }
    @Test public void ceroComoNumeradorEsValido() {
        assertEquals(0, new OperacionesMatematicas(0, 5).dividir(), 0);
    }
    @Test(expected = ArithmeticException.class) public void rechazaDivisorCero() {
        new OperacionesMatematicas(5, 0).dividir();
    }
    @Test(expected = ArithmeticException.class) public void rechazaDivisorCeroNegativo() {
        new OperacionesMatematicas(5, -0.0).dividir();
    }
    @Test(expected = IllegalArgumentException.class) public void rechazaNumerosNoFinitos() {
        new OperacionesMatematicas(Double.NaN, 1);
    }
}
