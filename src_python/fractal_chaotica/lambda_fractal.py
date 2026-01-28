import cmath
from .utils import get_color

def iterate(z: complex, c: complex, limit: int, grayscale: bool, r: float, g: float, b: float) -> tuple[int, int, int]:
    """
    Lambda fractal: Z = C * Z * (1 - Z)
    """
    
    for counter in range(limit + 1):
        try:
            magn = abs(z)
        except OverflowError:
            magn = float('inf')

        if magn >= 2:
            return get_color(counter, magn, grayscale, r, g, b)
        
        # Java implementation had manual complex math:
        # x = (x - x * x + y * y) * a - (y - 2 * x * y) * b2;
        # y = (y - 2 * tmp * y) * a + (tmp - tmp * tmp + y * y) * b2;
        # Which simplifies to Z = C * (Z - Z^2) = C * Z * (1 - Z)
        
        z = c * z * (1 - z)
        
    return (0, 0, 0)
