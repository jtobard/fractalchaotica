import cmath
from .utils import get_color

def iterate(z: complex, limit: int, grayscale: bool, r: float, g: float, b: float) -> tuple[int, int, int]:
    """
    Biomorph fractal: Z = Z^1.5 + 0.2
    """
    c_const = complex(0.2, 0)
    
    for counter in range(limit + 1):
        try:
            magn = abs(z)
        except OverflowError:
            magn = float('inf')

        if magn >= 2:
            return get_color(counter, magn, grayscale, r, g, b)
        
        # Z = Z^1.5 + 0.2
        # Using cmath for complex power
        z = z**1.5 + c_const
        
    return (0, 0, 0)
