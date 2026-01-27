package cl.basilisco.core;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class FractalTest {

    @Test
    public void toTestBN() {
        ComplexNumber z = new ComplexNumber();
        int limit = 10;
        boolean grayscale = true;
        float r = 1;
        float g = 1;
        float b = 1;

        Color result = Fractal.iterate(z, limit, grayscale, r, g, b);
        float re = 0f;
        float ge = 0f;
        float be = 0f;
        Color expected = new Color(re, ge, be);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void toTestColor() {
        ComplexNumber z = new ComplexNumber(10, 10);
        int limit = 10;
        boolean grayscale = false;
        float r = 1;
        float g = 1;
        float b = 1;

        Color result = Fractal.iterate(z, limit, grayscale, r, g, b);
        int re = 241;
        int ge = 241;
        int be = 241;
        Color expected = new Color(re, ge, be);
        Assert.assertEquals(expected, result);
    }
}
