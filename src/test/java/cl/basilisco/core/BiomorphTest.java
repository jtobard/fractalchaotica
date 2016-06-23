package cl.basilisco.core;


import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class BiomorphTest {

    @Test
    public void toTestBN(){
        ComplexNumber z = new ComplexNumber();
        int tope = 10;
        boolean bn = true;
        float r = 1;
        float g = 1;
        float b = 1;

        Color resp = Biomorph.itera(z,tope,bn,r,g,b);
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

        Color resp = Biomorph.itera(z,tope,bn,r,g,b);
        Color exp = Color.white;
        Assert.assertEquals(exp,resp);
    }
}
