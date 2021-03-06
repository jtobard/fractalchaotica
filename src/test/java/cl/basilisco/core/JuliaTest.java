package cl.basilisco.core;


import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class JuliaTest {

    @Test
    public void toTestBN(){
        ComplexNumber z = new ComplexNumber();
        ComplexNumber y = new ComplexNumber();
        int tope = 10;
        boolean bn = true;
        float r = 1;
        float g = 1;
        float b = 1;

        Color resp = Julia.itera(z,y,tope,bn,r,g,b);
        float re = 0f;
        float ge = 0f;
        float be = 0f;
        Color exp = new Color(re,ge,be);
        Assert.assertEquals(exp,resp);
    }

    @Test
    public void toTestColor(){
        ComplexNumber z = new ComplexNumber(10,10);
        ComplexNumber y = new ComplexNumber();
        int tope = 10;
        boolean bn = false;
        float r = 1;
        float g = 1;
        float b = 1;

        Color resp = Julia.itera(z,y,tope,bn,r,g,b);
        int re = 255;
        int ge = 255;
        int be = 255;
        Color exp = new Color(re,ge,be);
        Assert.assertEquals(exp,resp);
    }

    @Test
    public void getNumeros(){
        ComplexNumber[] arr = Julia.valores();
        Assert.assertTrue(arr.length>0);
    }
}
