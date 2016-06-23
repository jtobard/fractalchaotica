package cl.basilisco.core;


import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class MandelbrotTest {

    @Test
    public void toTestBN(){
        ComplexNumber z = new ComplexNumber();
        int tope = 10;
        boolean bn = true;
        float r = 1;
        float g = 1;
        float b = 1;

        Color resp = Mandelbrot.itera(z,tope,bn,r,g,b);
        float re = 0f;
        float ge = 0f;
        float be = 0f;
        Color exp = new Color(re,ge,be);
        Assert.assertEquals(exp,resp);
    }

    @Test
    public void toTestColor(){
        ComplexNumber z = new ComplexNumber(10,10);
        int tope = 10;
        boolean bn = false;
        float r = 1;
        float g = 1;
        float b = 1;

        Color resp = Mandelbrot.itera(z,tope,bn,r,g,b);
        int re = 241;
        int ge = 241;
        int be = 241;
        Color exp = new Color(re,ge,be);
        Assert.assertEquals(exp,resp);
    }

    @Test
    public void toTestBNB(){
        ComplexNumber z = new ComplexNumber(10,10);
        int tope = 10;
        boolean bn = true;
        float r = 1;
        float g = 1;
        float b = 1;

        Color resp = Mandelbrot.itera(z,tope,bn,r,g,b);
        int re = 250;
        int ge = 250;
        int be = 250;
        Color exp = new Color(re,ge,be);
        Assert.assertEquals(exp,resp);
    }
}
