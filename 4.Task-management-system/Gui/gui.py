
import ttkbootstrap as ttkb
from PIL import Image, ImageTk


# Init ---------------------------------------------------------------------- #


class Gui:
    def __init__(self, root):

        # Constants
        self.WINDOW_TITLE = "Task Management System"
        self.FONT_NAME    = "Arial"
        self.width        = 800
        self.height       = 600
        self.COLOR_1      = "#7a82ff"
        self.COLOR_2      = "#a2b4ff"

        # Window
        self.root = root
        self.root.config(padx=0, pady=0)
        self.root.title(self.WINDOW_TITLE)
        self.root.geometry(f"{self.width}x{self.height}")
        self.root.minsize(self.width, self.height)
        self.root.maxsize(None, None)
        self.root.resizable(True, True)

        # Background
        self.canvas = ttkb.Canvas(self.root)
        self.canvas.config(width=self.width, height=self.height, bg="purple")
        self.background_img = Image.open("assets/images/background.png")
        self.background_img = self.background_img.resize((self.width, self.height), Image.Resampling.LANCZOS)
        self.background_img = ImageTk.PhotoImage(self.background_img)
        self.canvas.create_image(0, 0, image=self.background_img, anchor="nw")
        self.canvas.pack()

        # Main frame
        self.main_frame = ttkb.Frame(self.canvas, style="TFrame")
        self.main_frame.place(x=150, y=50, width=self.width - 300, height=self.height - 100)
        self.main_frame.grid_columnconfigure(0, weight=1)

        # Title
        self.title_style = ttkb.Style()
        self.title_style.configure("title_style.TLabel", font=(self.FONT_NAME, 22, "bold"), foreground=self.COLOR_1)
        self.title_label = ttkb.Label(self.main_frame, text="To Do List", style="title_style.TLabel")
        self.title_label.grid(column=0, row=0, pady=32)

        # Tasks frame
        self.tasks_frame = ttkb.Frame(self.main_frame, style="TFrame")
        self.tasks_frame.grid(column=0, row=1)

        # Button styles
        self.btn_style = ttkb.Style()
        self.btn_style.configure("TButton", font=(self.FONT_NAME, 10), borderwidth=0)
        self.finish_btn_style = ttkb.Style()
        self.finish_btn_style.configure("finish.TButton")
        self.finish_btn_style.map("finish.TButton", background=[("!active", "#A4CE95"), ("active", "#7dbc65")])
        self.edit_btn_style = ttkb.Style()
        self.edit_btn_style.configure("edit.TButton")
        self.edit_btn_style.map("edit.TButton", background=[("!active", "#a2b4ff"), ("active", "#7a82ff")])
        self.delete_btn_style = ttkb.Style()
        self.delete_btn_style.configure("delete.TButton")
        self.delete_btn_style.map("delete.TButton", background=[("!active", "#ffa0a0"), ("active", "#ff8484")])

        # Tasks list
        for i in range(8):
            # Finish task button
            self.finish_btn = ttkb.Button(self.tasks_frame, text="✓", style="finish.TButton")
            self.finish_btn.config(width=1)
            self.finish_btn.grid(column=0, row=i, padx=5, pady=5, ipadx=1, ipady=1)

            # Task entry
            self.task_entry = ttkb.Entry(self.tasks_frame, style="task.TEntry", font=(self.FONT_NAME, 10, "bold"))
            self.task_entry.config(width=40)
            self.task_entry.grid(column=1, row=i)

            # Task editor button
            self.edit_btn = ttkb.Button(self.tasks_frame, text="✎", style="edit.TButton")
            self.edit_btn.config(width=1)
            self.edit_btn.grid(column=2, row=i, padx=5, pady=5, ipadx=1, ipady=1)

            # Task delete button
            self.delete_btn = ttkb.Button(self.tasks_frame, text="x", style="delete.TButton")
            self.delete_btn.config(width=1.25)
            self.delete_btn.grid(column=3, row=i, padx=0, pady=0, ipadx=1, ipady=1)

