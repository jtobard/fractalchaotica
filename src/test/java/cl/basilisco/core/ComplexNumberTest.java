package cl.basilisco.core;

import org.junit.Assert;
import org.junit.Test;

public class ComplexNumberTest {
    private static final double DELTA = 0.01;

    @Test
    public void newObjectTest() {
        ComplexNumber zero = new ComplexNumber();
        Assert.assertEquals(0, zero.getReal(), DELTA);
        Assert.assertEquals(0, zero.getImaginary(), DELTA);
    }

    @Test
    public void setters() {
        ComplexNumber a = new ComplexNumber();
        a.setReal(1);
        a.setImaginary(2);
        Assert.assertEquals(1, a.getReal(), DELTA);
        Assert.assertEquals(2, a.getImaginary(), DELTA);
    }

    @Test
    public void multiplication() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber b = new ComplexNumber(1, 1);
        ComplexNumber result = a.multiply(b);
        ComplexNumber expectedResult = new ComplexNumber(5, -1);
        Assert.assertEquals(expectedResult.getReal(), result.getReal(), DELTA);
        Assert.assertEquals(expectedResult.getImaginary(), result.getImaginary(), DELTA);
    }

    @Test
    public void abs() {
        ComplexNumber a = new ComplexNumber(2, -3);
        double res = a.getModulus();
        double expRes = 3.605;
        Assert.assertEquals(expRes, res, DELTA);
    }

    @Test
    public void arg() {
        ComplexNumber a = new ComplexNumber(2, -3);
        double res = a.getArgument();
        double expRes = -0.982;
        Assert.assertEquals(expRes, res, DELTA);
    }

    @Test
    public void conjugate() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber res = a.getConjugate();
        ComplexNumber expRes = new ComplexNumber(2, 3);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void add() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber b = new ComplexNumber(1, 1);
        ComplexNumber res = a.add(b);
        ComplexNumber expRes = new ComplexNumber(3, -2);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void subtract() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber b = new ComplexNumber(1, 1);
        ComplexNumber res = a.subtract(b);
        ComplexNumber expRes = new ComplexNumber(1, -4);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void divide() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber b = new ComplexNumber(1, 1);
        ComplexNumber res = a.divide(b);
        ComplexNumber expRes = new ComplexNumber(-0.5, -2.5);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void exp() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber res = a.exp();
        ComplexNumber expRes = new ComplexNumber(-7.31, -1.04);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void log() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber res = a.log();
        ComplexNumber expRes = new ComplexNumber(1.282, -0.982);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void sqrt() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber res = a.sqrt();
        ComplexNumber expRes = new ComplexNumber(1.67, -0.89);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void sin() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber res = a.sin();
        ComplexNumber expRes = new ComplexNumber(9.15, 4.16);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void cos() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber res = a.cos();
        ComplexNumber expRes = new ComplexNumber(-4.18, 9.10);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void sinh() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber res = a.sinh();
        ComplexNumber expRes = new ComplexNumber(-3.59, -0.53);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void cosh() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber res = a.cosh();
        ComplexNumber expRes = new ComplexNumber(-3.72, -0.51);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void tan() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber res = a.tan();
        ComplexNumber expRes = new ComplexNumber(-0.003, -1.003);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void pow() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber res = a.pow(2);
        ComplexNumber expRes = new ComplexNumber(-5, -12);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void powDouble() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber res = a.pow(2d);
        ComplexNumber expRes = new ComplexNumber(-5, -12);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void powDoubleObject() {
        ComplexNumber a = new ComplexNumber(2, -3);
        Double d = 2d;
        ComplexNumber res = a.pow(d);
        ComplexNumber expRes = new ComplexNumber(-5, -12);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }

    @Test
    public void testToString() {
        ComplexNumber a = new ComplexNumber(2, -3);
        String expRes = "2.0 + -3.0i";
        Assert.assertEquals(expRes, a.toString());
    }

    @Test
    public void testEquals() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber b = new ComplexNumber(2, -3);
        boolean res = a.equals(b);
        boolean expRes = true;
        Assert.assertEquals(expRes, res);
    }

    @Test
    public void compare() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber b = new ComplexNumber(2, -3);
        int res = a.compareTo(b);
        int expRes = 0;
        Assert.assertEquals(expRes, res);
    }

    @Test
    public void negative() {
        ComplexNumber a = new ComplexNumber(2, -3);
        ComplexNumber res = a.negative();
        ComplexNumber expRes = new ComplexNumber(-2, 3);
        Assert.assertEquals(expRes.getReal(), res.getReal(), DELTA);
        Assert.assertEquals(expRes.getImaginary(), res.getImaginary(), DELTA);
    }
}
