import sys
import os

# Ensure src_python is in path
current_dir = os.path.dirname(os.path.abspath(__file__))
parent_dir = os.path.dirname(current_dir)
sys.path.append(parent_dir)

from fractal_chaotica.gui import FractalApp

def main():
    app = FractalApp()
    # Initial paint a bit later to ensuring window is ready
    app.after(100, app.paint_fractal)
    app.mainloop()

if __name__ == "__main__":
    main()
