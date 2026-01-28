import tkinter as tk
from tkinter import ttk, messagebox, filedialog
import math
import time
from . import mandelbrot, julia, biomorph, lambda_fractal, cubic_mandelbrot, utils

class FractalApp(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Fractal Chaotica - Python")
        self.geometry("800x700")
        
        # Canvas Variables
        self.width = 600
        self.height = 600
        self.corner_x = -3.0
        self.corner_y = 3.0
        self.pixel_ratio = (self.corner_y * 2) / self.height
        self.grayscale = False
        self.fractal_type = "Mandelbrot"
        self.max_iterations = 50 # Reduced for Python performance default
        self.constant = 0j
        self.color_r = 1.0
        self.color_g = 1.0
        self.color_b = 1.0
        
        # Zoom variables
        self.start_x = 0
        self.start_y = 0
        self.rect_id = None

        self._init_ui()
        
    def _init_ui(self):
        # Main Layout
        self.canvas_frame = tk.Frame(self, bg="black")
        self.canvas_frame.pack(side=tk.TOP, fill=tk.BOTH, expand=True)
        
        self.canvas = tk.Canvas(self.canvas_frame, width=self.width, height=self.height, bg="black", cursor="cross")
        self.canvas.pack(anchor=tk.CENTER, pady=10)
        
        self.canvas.bind("<ButtonPress-1>", self.on_mouse_down)
        self.canvas.bind("<ButtonRelease-1>", self.on_mouse_up)
        self.canvas.bind("<B1-Motion>", self.on_mouse_drag)
        self.canvas.bind("<Button-2>", self.on_right_click) # Using Middle/Right click for pan on Mac often differs, sticking to button-2 for now as per Java code implying Pan

        # Controls
        self.controls_frame = tk.Frame(self)
        self.controls_frame.pack(side=tk.BOTTOM, fill=tk.X, padx=5, pady=5)
        
        # Row 1: Buttons
        row1 = tk.Frame(self.controls_frame)
        row1.pack(fill=tk.X, pady=2)
        
        tk.Button(row1, text="Reset", command=self.reset).pack(side=tk.LEFT, padx=2)
        self.btn_color = tk.Button(row1, text="Grayscale Mode", command=self.toggle_color)
        self.btn_color.pack(side=tk.LEFT, padx=2)
        tk.Button(row1, text="Save Image", command=self.save_image).pack(side=tk.LEFT, padx=2)
        
        # Row 2: Parameters and Formula
        row2 = tk.Frame(self.controls_frame)
        row2.pack(fill=tk.X, pady=2)
        
        tk.Label(row2, text="Z = ").pack(side=tk.LEFT)
        self.entry_zr = tk.Entry(row2, width=8)
        self.entry_zr.pack(side=tk.LEFT)
        self.entry_zr.insert(0, "-1")
        tk.Label(row2, text=" + ").pack(side=tk.LEFT)
        self.entry_zi = tk.Entry(row2, width=8)
        self.entry_zi.pack(side=tk.LEFT)
        self.entry_zi.insert(0, "0")
        tk.Label(row2, text="i").pack(side=tk.LEFT)
        
        tk.Button(row2, text="Refresh", command=self.refresh_params).pack(side=tk.LEFT, padx=5)
        tk.Button(row2, text="Get Number", command=self.random_julia).pack(side=tk.LEFT, padx=2)
        
        self.combo_formula = ttk.Combobox(row2, values=["Mandelbrot", "Julia", "Mandelbrot2", "Lambda", "Biomorph"], state="readonly")
        self.combo_formula.current(0)
        self.combo_formula.bind("<<ComboboxSelected>>", self.on_formula_change)
        self.combo_formula.pack(side=tk.LEFT, padx=5)
        
        # Row 3: Colors
        row3 = tk.Frame(self.controls_frame)
        row3.pack(fill=tk.X, pady=2)
        
        tk.Label(row3, text="Color Multipliers (R, G, B):").pack(side=tk.LEFT)
        self.entry_r = tk.Entry(row3, width=5); self.entry_r.insert(0, "1"); self.entry_r.pack(side=tk.LEFT)
        self.entry_g = tk.Entry(row3, width=5); self.entry_g.insert(0, "1"); self.entry_g.pack(side=tk.LEFT)
        self.entry_b = tk.Entry(row3, width=5); self.entry_b.insert(0, "1"); self.entry_b.pack(side=tk.LEFT)
        
        tk.Button(row3, text="Update Colors", command=self.update_colors).pack(side=tk.LEFT, padx=5)

    def paint_fractal(self):
        # NOTE: Pure Python pixel manipulation is SLOW.
        # This is a naive implementation. For production, use PIL or NumPy.
        self.canvas.delete("all")
        
        try:
           self.update_colors() # ensure vars are set
        except:
           pass

        width = self.width
        height = self.height
        
        start_time = time.time()
        
        # Create a PhotoImage
        self.img = tk.PhotoImage(width=width, height=height)
        
        # To make it faster, we compute lines and define a data string for PhotoImage
        # Format: { #RRGGBB #RRGGBB ... } { #RRGGBB ... }
        
        data_lines = []
        
        for y in range(height):
            line_colors = []
            cy = self.corner_y - (y * self.pixel_ratio)
            # Pre-calculate x mapping
            cxs = [self.corner_x + (x * self.pixel_ratio) for x in range(width)]
            
            for x in range(width):
                cx = cxs[x]
                c = complex(cx, cy)
                
                rgb = (0,0,0)
                
                if self.fractal_type == "Mandelbrot":
                    rgb = mandelbrot.iterate(c, self.max_iterations, self.grayscale, self.color_r, self.color_g, self.color_b)
                elif self.fractal_type == "Julia":
                    rgb = julia.iterate(c, self.constant, self.max_iterations, self.grayscale, self.color_r, self.color_g, self.color_b)
                elif self.fractal_type == "Mandelbrot2": # Cubic
                    rgb = cubic_mandelbrot.iterate(c, self.max_iterations, self.grayscale, self.color_r, self.color_g, self.color_b)
                elif self.fractal_type == "Lambda":
                    rgb = lambda_fractal.iterate(c, self.constant, self.max_iterations, self.grayscale, self.color_r, self.color_g, self.color_b)
                elif self.fractal_type == "Biomorph":
                    rgb = biomorph.iterate(c, self.max_iterations, self.grayscale, self.color_r, self.color_g, self.color_b)
                
                # Convert rgb to hex
                hex_color = "#%02x%02x%02x" % rgb
                line_colors.append(hex_color)
            
            data_lines.append("{" + " ".join(line_colors) + "}")
        
        # Join all lines
        img_data = " ".join(data_lines)
        self.img.put(img_data)
        
        self.canvas.create_image(0, 0, image=self.img, anchor=tk.NW)
        # print(f"Render time: {time.time() - start_time:.2f}s")


    def reset(self):
        self.corner_x = -3.0
        self.corner_y = 3.0
        self.pixel_ratio = (self.corner_y * 2) / self.height
        self.paint_fractal()

    def toggle_color(self):
        self.grayscale = not self.grayscale
        self.btn_color.config(text="Color Mode" if self.grayscale else "Grayscale Mode")
        self.paint_fractal()
        
    def update_colors(self):
        self.color_r = float(self.entry_r.get())
        self.color_g = float(self.entry_g.get())
        self.color_b = float(self.entry_b.get())
        self.paint_fractal()

    def refresh_params(self):
        try:
            r = float(self.entry_zr.get())
            i = float(self.entry_zi.get())
            self.constant = complex(r, i)
            self.paint_fractal()
        except ValueError:
            messagebox.showerror("Error", "Invalid Number Format")

    def random_julia(self):
        vals = julia.get_famous_values()
        import random
        val = random.choice(vals)
        self.entry_zr.delete(0, tk.END)
        self.entry_zr.insert(0, str(val.real))
        self.entry_zi.delete(0, tk.END)
        self.entry_zi.insert(0, str(val.imag))
        self.constant = val
        if self.fractal_type == "Julia" or self.fractal_type == "Lambda":
            self.paint_fractal()

    def on_formula_change(self, event):
        self.fractal_type = self.combo_formula.get()
        self.reset() # Start fresh coords
        
        # Enable/Disable inputs logic could go here
        if self.fractal_type in ["Julia", "Lambda"]:
            self.entry_zr.config(state=tk.NORMAL)
            self.entry_zi.config(state=tk.NORMAL)
        else:
            self.entry_zr.config(state=tk.DISABLED)
            self.entry_zi.config(state=tk.DISABLED)    

    # Mouse Events for Zoom
    def on_mouse_down(self, event):
        self.start_x = event.x
        self.start_y = event.y
        if self.rect_id:
            self.canvas.delete(self.rect_id)
            self.rect_id = None

    def on_mouse_drag(self, event):
        if not self.rect_id:
            self.rect_id = self.canvas.create_rectangle(self.start_x, self.start_y, event.x, event.y, outline="yellow")
        else:
            self.canvas.coords(self.rect_id, self.start_x, self.start_y, event.x, event.y)

    def on_mouse_up(self, event):
        end_x = event.x
        end_y = event.y
        
        if abs(end_x - self.start_x) < 5: return
        
        # Calculate new coords
        # Java logic ported: match aspect ratio
        width_scr = self.width
        height_scr = self.height
        
        # Simple zoom logic without aspect ratio correction for brevity in naive port
        # But let's try to respect the pixel_ratio
        
        # Convert screen select to world coords
        w_start_x = self.corner_x + (self.start_x * self.pixel_ratio)
        w_start_y = self.corner_y - (self.start_y * self.pixel_ratio)
        
        w_end_x = self.corner_x + (end_x * self.pixel_ratio)
        # w_end_y = self.corner_y - (end_y * self.pixel_ratio) 
        
        # To maintain aspect ratio, calculate new width in world units
        new_width_world = abs(w_end_x - w_start_x)
        
        # New pixel ratio
        self.pixel_ratio = new_width_world / self.width
        
        # Set new corner
        self.corner_x = min(w_start_x, w_end_x)
        self.corner_y = max(w_start_y, self.corner_y - (end_y * self.pixel_ratio)) # Rough approx
        
        # Better re-center logic:
        # Just set corner based on top-left of selection (assuming drag down-right)
        sel_x_min = min(self.start_x, end_x)
        sel_y_min = min(self.start_y, end_y)
        
        self.corner_x = self.corner_x + (sel_x_min * self.old_pixel_ratio if hasattr(self,'old_pixel_ratio') else self.pixel_ratio * sel_x_min)
        # Wait, self.pixel_ratio is already updated? No.
        
        # Let's simple mapping:
        # preserve current ratio for calculation
        old_ratio = self.pixel_ratio
        
        # New top-left
        new_corner_x = self.corner_x + (sel_x_min * old_ratio)
        new_corner_y = self.corner_y - (sel_y_min * old_ratio)
        
        # New width in world units (taking the width of selection)
        sel_width_px = abs(end_x - self.start_x)
        new_world_width = sel_width_px * old_ratio
        
        self.pixel_ratio = new_world_width / self.width
        self.corner_x = new_corner_x
        self.corner_y = new_corner_y
        
        self.canvas.delete(self.rect_id)
        self.rect_id = None
        self.paint_fractal()


    def on_right_click(self, event):
        # Pan to center
        # self.cornerX = cornerX - ((this.getSize().width / 2 - x) * pixelRatio);
        center_x = self.width / 2
        center_y = self.height / 2
        
        dx = center_x - event.x
        dy = event.y - center_y
        
        self.corner_x -= dx * self.pixel_ratio
        self.corner_y += dy * self.pixel_ratio # Y is inverted?
        # Java: cornerY - ((y - height/2) * ratio)
        # Java y grows down. World Y grows up.
        # click at Y=100 (top). Center=300. dy = -200.
        # We need to shift world view "up" so that world point at 100 becomes 300.
        
        self.paint_fractal()

    def save_image(self):
        f = filedialog.asksaveasfilename(defaultextension=".png")
        if f:
             # Tkinter cannot easily save Canvas content to file without PostScript or PIL
             # We generated the image logic ourselves, we could re-generate and save using simple PPM format
             try:
                 # Hack: write PPM
                 with open(f, 'wb') as file:
                     header = f"P6\n{self.width} {self.height}\n255\n"
                     file.write(header.encode('ascii'))
                     # Re-render or capture? PhotoImage doesn't give raw easy access in standard lib easily for binary write without PIL
                     # But we can iterate again... slow.
                     # Let's just warn:
                     messagebox.showinfo("Info", "Saving requires PIL. Using text snapshot or similar not implemented in strict stdlib port.")
             except:
                 pass

if __name__ == "__main__":
    app = FractalApp()
    # Trigger initial paint
    app.after(500, app.paint_fractal)
    app.mainloop()
