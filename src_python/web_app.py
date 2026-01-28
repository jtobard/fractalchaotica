from flask import Flask, render_template, request, send_file
import io
import sys
import os

# Ensure src_python is in path to import fractal modules
current_dir = os.path.dirname(os.path.abspath(__file__))
parent_dir = os.path.dirname(current_dir)
sys.path.append(parent_dir)

from fractal_chaotica import mandelbrot, julia, biomorph, lambda_fractal, cubic_mandelbrot, utils

from PIL import Image

app = Flask(__name__)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/famous_julia')
def famous_julia():
    """Returns a random famous Julia set constant"""
    vals = julia.get_famous_values()
    import random
    val = random.choice(vals)
    return {"r": val.real, "i": val.imag}

@app.route('/generate')
def generate():
    # Parameters
    width = int(request.args.get('width', 600))
    height = int(request.args.get('height', 600))
    
    fractal_type = request.args.get('type', 'Mandelbrot')
    
    # Coordinates
    corner_x = float(request.args.get('corner_x', -3.0))
    corner_y = float(request.args.get('corner_y', 3.0))
    pixel_ratio = float(request.args.get('pixel_ratio', (corner_y * 2) / height))
    
    # Fractal specific
    max_iters = int(request.args.get('iters', 50))
    grayscale = request.args.get('grayscale', 'false') == 'true'
    
    c_real = float(request.args.get('cr', -1.0))
    c_imag = float(request.args.get('ci', 0.0))
    constant = complex(c_real, c_imag)
    
    r_mult = float(request.args.get('r_mult', 1.0))
    g_mult = float(request.args.get('g_mult', 1.0))
    b_mult = float(request.args.get('b_mult', 1.0))

    # Generate Image
    img = Image.new('RGB', (width, height), "black")
    pixels = img.load()
    
    # We iterate manually to match the logic. 
    # Optimization: This is still Python loop. For production, numpy is better.
    # But we keep parity with the ported logic.
    
    for y in range(height):
        cy = corner_y - (y * pixel_ratio)
        for x in range(width):
            cx = corner_x + (x * pixel_ratio)
            c = complex(cx, cy)
            
            rgb = (0, 0, 0)
            if fractal_type == "Mandelbrot":
                rgb = mandelbrot.iterate(c, max_iters, grayscale, r_mult, g_mult, b_mult)
            elif fractal_type == "Julia":
                rgb = julia.iterate(c, constant, max_iters, grayscale, r_mult, g_mult, b_mult)
            elif fractal_type == "Mandelbrot2":
                rgb = cubic_mandelbrot.iterate(c, max_iters, grayscale, r_mult, g_mult, b_mult)
            elif fractal_type == "Lambda":
                rgb = lambda_fractal.iterate(c, constant, max_iters, grayscale, r_mult, g_mult, b_mult)
            elif fractal_type == "Biomorph":
                rgb = biomorph.iterate(c, max_iters, grayscale, r_mult, g_mult, b_mult)
            
            pixels[x, y] = rgb

    img_io = io.BytesIO()
    img.save(img_io, 'PNG')
    img_io.seek(0)
    return send_file(img_io, mimetype='image/png')

if __name__ == '__main__':
    print("Starting Fractal Chaotica Web Interface...")
    print("Go to http://localhost:5000")
    app.run(debug=True, port=5000)
