import cmath
from .utils import get_color

def iterate(z: complex, c: complex, limit: int, grayscale: bool, r: float, g: float, b: float) -> tuple[int, int, int]:
    """
    Iterate a number according to Gaston Julia's formula: Z = Z^2 + C (iterating Z with constant C).
    """
    # z is the starting point in the plane, c is the constant
    
    for counter in range(limit + 1):
        try:
            magn = abs(z)
        except OverflowError:
            magn = float('inf')

        if magn >= 2:
            return get_color(counter, magn, grayscale, r, g, b)
        
        z = z**2 + c
        
    return (0, 0, 0)

def get_famous_values() -> list[complex]:
    """
    Famous and tested Julia numbers.
    """
    return [
        complex(-1, 0),
        complex(0.687, 0.312),
        complex(0.6, 0.55),
        complex(0.8, 0.6),
        complex(0.3, 0.6),
        complex(0.25, 0),
        complex(0.4, 0.6),
        complex(0.282, 0),
        complex(0.285, 0.01),
        complex(0.45, 0.1428),
        complex(-0.70176, -0.3842),
        complex(-0.835, -0.2321),
        complex(-0.8, 0.156),
    ]
