# Fractal Chaotica: Python Web Edition
 
A powerful fractal visualizer originally written in Java (2008), now fully migrated to **Python 3**.
Due to modern macOS restrictions on legacy GUI libraries (Tkinter/Swing), this version switched to a **modern Web Interface** built with Flask.
 
## Features
 
- **Fractal Types**: Mandelbrot, Julia, Biomorph, Lambda, and Cubic Mandelbrot.
- **Parametric sets**: Adjust Real/Imaginary constants for Julia and Lambda sets.
- **Interactive Zoom**: Drag-and-select zooming on the web canvas.
- **High Performance**: Uses Python's native complex number support and optimized loops (equivalent to the original Java logic).
- **Customization**: Full RGB color multipliers and grayscale toggling.
- **Export**: Download high-resolution PNGs of your discoveries.
 
## Requirements
 
- Python 3.8+
- Flask
- Pillow
 
## Quick Start
 
1. **Clone the repository** and navigate to the folder.
 
2. **Install dependencies**:
   ```bash
   pip install -r requirements.txt
   ```
 
3. **Run the Application**:
   ```bash
   python src_python/web_app.py
   ```
 
4. **Explore**:
   Open your browser and visit: **[http://localhost:5000](http://localhost:5000)**
 
## Project Structure
 
- `src_python/fractal_chaotica/`: Core fractal algorithms (migrated from Java).
- `src_python/web_app.py`: Flask application entry point.
- `src_python/templates/`: HTML/JS frontend.
- `src_python/tests/`: Unit tests ensuring mathematical parity with the original Java code.
 
## History
 
- **2008**: Original Java/Swing implementation.
- **2026**: Migrated to Python 3 + Flask Web Interface.