package cl.basilisco.core;

import org.junit.Assert;
import org.junit.Test;


public class ComplexNumberTest {
    private static final double DELTA = 0.01;

    @Test
    public void newObjectTest(){
        ComplexNumber zero = new ComplexNumber();
        Assert.assertEquals(0,zero.getReal(),DELTA);
        Assert.assertEquals(0,zero.getImaginario(),DELTA);
    }

    @Test
    public void setters(){
        ComplexNumber a = new ComplexNumber();
        a.setReal(1);
        a.setImaginario(2);
        Assert.assertEquals(1,a.getReal(),DELTA);
        Assert.assertEquals(2,a.getImaginario(),DELTA);
    }

    @Test
    public void multiplication(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber b = new ComplexNumber(1,1);
        ComplexNumber result = a.multiplica(b);
        ComplexNumber expectedResult = new ComplexNumber(5,-1);
        Assert.assertEquals(expectedResult.getReal(),result.getReal(), DELTA);
        Assert.assertEquals(expectedResult.getImaginario(),result.getImaginario(), DELTA);
    }

    @Test
    public void abs(){
        ComplexNumber a = new ComplexNumber(2,-3);
        double res = a.getModulo();
        double expRes = 3.605;
        Assert.assertEquals(expRes,res,DELTA);
    }

    @Test
    public void arg(){
        ComplexNumber a = new ComplexNumber(2,-3);
        double res = a.getArgumento();
        double expRes = -0.982;
        Assert.assertEquals(expRes,res,DELTA);
    }

    @Test
    public void conjugado(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber res = a.getConjugado();
        ComplexNumber expRes = new ComplexNumber(2,3);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void suma(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber b = new ComplexNumber(1,1);
        ComplexNumber res = a.suma(b);
        ComplexNumber expRes = new ComplexNumber(3,-2);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void resta(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber b = new ComplexNumber(1,1);
        ComplexNumber res = a.resta(b);
        ComplexNumber expRes = new ComplexNumber(1,-4);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void divide(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber b = new ComplexNumber(1,1);
        ComplexNumber res = a.divide(b);
        ComplexNumber expRes = new ComplexNumber(-0.5,-2.5);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void exp(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber res = a.exp();
        ComplexNumber expRes = new ComplexNumber(-7.31,-1.04);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void log(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber res = a.log();
        ComplexNumber expRes = new ComplexNumber(1.282,-0.982);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }
    @Test
    public void sqrt(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber res = a.sqrt();
        ComplexNumber expRes = new ComplexNumber(1.67,-0.89);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void sin(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber res = a.sin();
        ComplexNumber expRes = new ComplexNumber(9.15,4.16);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void cos(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber res = a.cos();
        ComplexNumber expRes = new ComplexNumber(-4.18,9.10);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void sinh(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber res = a.sinh();
        ComplexNumber expRes = new ComplexNumber(-3.59,-0.53);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void cosh(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber res = a.cosh();
        ComplexNumber expRes = new ComplexNumber(-3.72,-0.51);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void tan(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber res = a.tan();
        ComplexNumber expRes = new ComplexNumber(-0.003,-1.003);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void eleva(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber res = a.eleva(2);
        ComplexNumber expRes = new ComplexNumber(-5,-12);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void elevadouble(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber res = a.eleva(2d);
        ComplexNumber expRes = new ComplexNumber(-5,-12);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void elevaDouble(){
        ComplexNumber a = new ComplexNumber(2,-3);
        Double d = new Double(2d);
        ComplexNumber res = a.eleva(d);
        ComplexNumber expRes = new ComplexNumber(-5,-12);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }

    @Test
    public void tostring(){
        ComplexNumber a = new ComplexNumber(2,-3);
        String expRes = "2.0 + -3.0i";
        Assert.assertEquals(expRes, a.toString());
    }

    @Test
    public void equalsM(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber b = new ComplexNumber(2,-3);
        boolean res = a.equalsM(b);
        boolean expRes = true;
        Assert.assertEquals(expRes,res);
    }

    @Test
    public void compare(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber b = new ComplexNumber(2,-3);
        int res = a.compareTo(b);
        int expRes = 0;
        Assert.assertEquals(expRes,res);
    }

    @Test
    public void negativo(){
        ComplexNumber a = new ComplexNumber(2,-3);
        ComplexNumber res = a.negativo();
        ComplexNumber expRes = new ComplexNumber(-2,3);
        Assert.assertEquals(expRes.getReal(),res.getReal(),DELTA);
        Assert.assertEquals(expRes.getImaginario(),res.getImaginario(),DELTA);
    }
}
