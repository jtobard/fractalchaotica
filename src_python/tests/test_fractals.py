import unittest
from fractal_chaotica import mandelbrot, julia, biomorph, lambda_fractal, cubic_mandelbrot

class TestFractals(unittest.TestCase):
    
    def test_mandelbrot_bn(self):
        # z in input is treated as C (starting point), internal Z starts at 0.
        # If C=0, Z remains 0. Should not escape. Return Black (0,0,0).
        c = 0j
        result = mandelbrot.iterate(c, 10, True, 1, 1, 1)
        self.assertEqual(result, (0, 0, 0))

    def test_mandelbrot_color(self):
        # Z=(10,10). Internal Z starts 0. Step 1: Z=C=(10,10). Magn=14.14 >= 2. Escape at counter=1.
        # Grayscale=False.
        c = complex(10, 10)
        result = mandelbrot.iterate(c, 10, False, 1, 1, 1)
        # Expected from analysis: (241, 241, 241)
        self.assertEqual(result, (241, 241, 241))

    def test_mandelbrot_bnb(self):
        # Grayscale=True. Counter=1. n=255-(1*5)=250.
        c = complex(10, 10)
        result = mandelbrot.iterate(c, 10, True, 1, 1, 1)
        self.assertEqual(result, (250, 250, 250))

    def test_julia_bn(self):
        # Julia takes Z and C.
        z = 0j
        c = 0j
        result = julia.iterate(z, c, 10, True, 1, 1, 1)
        self.assertEqual(result, (0, 0, 0))

    def test_julia_color(self):
        # Z=(10,10). Escape immediately at counter=0?
        # Java logic: check magn first.
        # calculate(Z, C, 0, ...) -> magn(Z) >= 2? 14.14 >= 2. YES.
        # Return color at counter=0.
        # Grayscale=False.
        # r = 255 - (0 * ...) = 255.
        z = complex(10, 10)
        c = 0j
        result = julia.iterate(z, c, 10, False, 1, 1, 1)
        self.assertEqual(result, (255, 255, 255))

    def test_julia_famous_values(self):
        vals = julia.get_famous_values()
        self.assertTrue(len(vals) > 0)
        self.assertIsInstance(vals[0], complex)

    def test_biomorph_bn(self):
        z = 0j
        # Biomorph Z=Z^1.5 + 0.2
        # Start 0. z -> 0.2. magn=0.2.
        # Next z -> 0.2^1.5 + 0.2 ~ 0.089 + 0.2 = 0.289.
        # Seems strictly bounded for 10 steps?
        # Java test expected Black.
        result = biomorph.iterate(z, 10, True, 1, 1, 1)
        self.assertEqual(result, (0, 0, 0))

    def test_biomorph_color(self):
        # Z=(10,10). Escape immediately at counter=0.
        # Expect White.
        z = complex(10, 10)
        result = biomorph.iterate(z, 10, False, 1, 1, 1)
        # Java test expected Color.white -> (255, 255, 255)
        self.assertEqual(result, (255, 255, 255))

    def test_lambda_bn(self):
        z = 0j
        c = 0j
        result = lambda_fractal.iterate(z, c, 10, True, 1, 1, 1)
        self.assertEqual(result, (0, 0, 0))

    def test_lambda_color(self):
        z = complex(10, 10)
        c = 0j
        # Escape immediately at counter=0. White.
        result = lambda_fractal.iterate(z, c, 10, False, 1, 1, 1)
        self.assertEqual(result, (255, 255, 255))

    def test_cubic_mandelbrot(self):
        # Z = Z^3 + C. Starts Z=0.
        # behave like Mandelbrot
        c = complex(10, 10)
        # Step 0: Z=0. Step 1: Z=C=(10,10). Escape at 1.
        # Expect (250, 250, 250) for grayscale
        result = cubic_mandelbrot.iterate(c, 10, True, 1, 1, 1)
        self.assertEqual(result, (250, 250, 250))
