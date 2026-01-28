import cmath
from .utils import get_color

def iterate(c: complex, limit: int, grayscale: bool, r: float, g: float, b: float) -> tuple[int, int, int]:
    """
    Cubic Mandelbrot: Z = Z^3 + C
    """
    z = 0j
    
    for counter in range(limit + 1):
        try:
            magn = abs(z)
        except OverflowError:
            magn = float('inf')
            
        if magn >= 2:
            return get_color(counter, magn, grayscale, r, g, b)
        
        z = z**3 + c
        
    return (0, 0, 0)
