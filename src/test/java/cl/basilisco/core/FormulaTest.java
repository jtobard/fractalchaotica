package cl.basilisco.core;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * Created by jtobar on 22/06/16.
 */
public class FormulaTest {


    @Test
    public void toTestExc(){
        ComplexNumber z = new ComplexNumber();
        int tope = 10;
        boolean bn = true;
        float r = 1;
        float g = 1;
        float b = 1;

        String cabecera = ""; //M or other
        Object[] formula = {};

        Color resp = Formula.itera(cabecera, formula,z,tope,bn,r,g,b);
        float re = 0f;
        float ge = 0f;
        float be = 0f;
        Color exp = new Color(re,ge,be);
        Assert.assertEquals(exp,resp);
    }

    @Test
    public void toTest(){
        ComplexNumber z = new ComplexNumber(-3.0,3.0);
        int tope = 255;
        boolean bn = false;
        float r = 1;
        float g = 1;
        float b = 1;

        String cabecera = "Z"; //M or other
        Object[] formula = {1.0d};

        Color resp = Formula.itera(cabecera, formula,z,tope,bn,r,g,b);
        int re = 255;
        int ge = 255;
        int be = 255;
        Color exp = new Color(re,ge,be);
        Assert.assertEquals(exp,resp);
    }
}
