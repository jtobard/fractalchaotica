
class BadFormulaException(Exception):
    pass

def get_color(c: int, magn: float, grayscale: bool, r2: float, g2: float, b2: float) -> tuple[int, int, int]:
    """
    Calculates the color for a given iteration count and magnitude.
    
    Args:
        c: Iteration counter.
        magn: Magnitude of the complex number (Z).
        grayscale: Whether to use grayscale.
        r2, g2, b2: Color multipliers.
        
    Returns:
        tuple[int, int, int]: RGB color values (0-255).
    """
    if grayscale:
        n = 255 - (c * 5) % 255
        return (n, n, n)
    else:
        if r2 == 0: r2 = magn
        if g2 == 0: g2 = magn
        if b2 == 0: b2 = magn
        
        r = 255 - (c * int(magn * r2)) % 250
        g = 255 - (c * int(magn * g2)) % 255
        b = 255 - (c * int(magn * b2)) % 245
        return (r, g, b)
