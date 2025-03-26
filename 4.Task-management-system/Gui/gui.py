
import ttkbootstrap as ttkb
from PIL import Image, ImageTk
from PIL.ImageOps import expand


# Init ---------------------------------------------------------------------- #


class Gui:
    def __init__(self, root):

        # Constants
        self.WINDOW_TITLE = "Task Management System"
        self.FONT_NAME    = "Arial"
        self.WIDTH        = 800
        self.HEIGHT       = 600
        self.MIN_WIDTH    = 800
        self.MIN_HEIGHT   = 600
        self.PURPLE       = "#7a82ff"
        self.LIGHT_PURPLE = "#a2b4ff"
        self.WHITE        = "#ffffff"
        self.WHITE_1      = "#f1f3f6"
        self.WHITE_3      = "#d7dade"


        # Window
        self.root = root
        self.root.config(padx=0, pady=0)
        self.root.title(self.WINDOW_TITLE)
        self.root.geometry(f"{self.WIDTH}x{self.HEIGHT}")
        self.root.minsize(self.MIN_WIDTH, self.MIN_HEIGHT)
        self.root.maxsize(None, None)
        self.root.resizable(True, True)

        # Background
        self.canvas = ttkb.Canvas(self.root)
        self.canvas.config(width=self.WIDTH, height=self.HEIGHT)
        self.background_img = Image.open("assets/images/background.png")
        self.background_img = self.background_img.resize((self.WIDTH, self.HEIGHT), Image.Resampling.LANCZOS)
        self.background_img = ImageTk.PhotoImage(self.background_img)
        self.canvas.create_image(0, 0, image=self.background_img, anchor="nw")
        self.canvas.pack()

        # Main frame
        self.main_frame_style = ttkb.Style()
        self.main_frame_style.configure("main_frame.TFrame", background=self.WHITE_1)
        self.main_frame = ttkb.Frame(self.canvas, style="main_frame.TFrame")
        self.main_frame.place(x=self.WIDTH/2, y=self.HEIGHT/2, width=450, height=500, anchor="center")
        self.main_frame.grid_columnconfigure(0, weight=1)
        self.main_frame.grid_rowconfigure(1, weight=1   )

        # Title
        self.title_style = ttkb.Style()
        self.title_style.configure("title_style.TLabel", font=(self.FONT_NAME, 22, "bold"), foreground=self.PURPLE, background=self.WHITE_1)
        self.title_label = ttkb.Label(self.main_frame, text="To Do List", style="title_style.TLabel")
        self.title_label.grid(column=0, row=0, pady=32)

        # Tasks frame
        self.tasks_frame_style = ttkb.Style()
        self.tasks_frame_style.configure("tasks_frame.TFrame", background=self.WHITE_1)
        self.tasks_frame = ttkb.Frame(self.main_frame, style="tasks_frame.TFrame")
        self.tasks_frame.grid(column=0, row=1, sticky="nsew")
        self.tasks_frame.grid_columnconfigure(0, weight=1)
        self.tasks_frame.grid_rowconfigure(0, weight=1)

        # Tasks canvas
        self.tasks_canvas = ttkb.Canvas(self.tasks_frame)
        self.tasks_canvas.configure(background=self.WHITE_1)
        self.tasks_canvas.grid(column=0, row=0, sticky="nsew")

        # Tasks scrollbar
        self.tasks_scrollbar = ttkb.Scrollbar(self.tasks_frame, command=self.tasks_canvas.yview)
        self.tasks_scrollbar.grid(column=1, row=0, sticky="ns")

        self.tasks_canvas.configure(yscrollcommand=self.tasks_scrollbar.set)

        self.tasks_inner_frame = ttkb.Frame(self.tasks_canvas, style="tasks_frame.TFrame")
        self.tasks_canvas.create_window((450/2, 0), window=self.tasks_inner_frame, anchor='n')

        # Button styles
        self.btn_style = ttkb.Style()
        self.btn_style.configure("TButton", font=(self.FONT_NAME, 10), borderwidth=0)
        self.finish_btn_style = ttkb.Style()
        self.finish_btn_style.configure("finish.TButton", foreground="#7dbc65")
        self.finish_btn_style.map("finish.TButton", background=[("!active", self.WHITE), ("active", self.WHITE_3)])
        self.edit_btn_style = ttkb.Style()
        self.edit_btn_style.configure("edit.TButton", foreground="gray")
        self.edit_btn_style.map("edit.TButton", background=[("!active", self.WHITE), ("active", self.WHITE_3)])
        self.delete_btn_style = ttkb.Style()
        self.delete_btn_style.configure("delete.TButton", foreground="#ff8484")
        self.delete_btn_style.map("delete.TButton", background=[("!active", self.WHITE), ("active", self.WHITE_3)])

        # Tasks list
        for i in range(15):
            # Finish task button
            self.finish_btn = ttkb.Button(self.tasks_inner_frame, text="✓", style="finish.TButton")
            self.finish_btn.config(width=1)
            self.finish_btn.grid(column=0, row=i, padx=5, pady=5, ipadx=1, ipady=1)

            # Task entry
            self.task_entry = ttkb.Entry(self.tasks_inner_frame, style="light.TEntry", font=(self.FONT_NAME, 10))
            self.task_entry.config(width=40)
            self.task_entry.grid(column=1, row=i)

            # Task editor button
            self.edit_btn = ttkb.Button(self.tasks_inner_frame, text="✎", style="edit.TButton")
            self.edit_btn.config(width=1)
            self.edit_btn.grid(column=2, row=i, padx=5, pady=5, ipadx=1, ipady=1)

            # Task delete button
            self.delete_btn = ttkb.Button(self.tasks_inner_frame, text="x", style="delete.TButton")
            self.delete_btn.config(width=1.25)
            self.delete_btn.grid(column=3, row=i, padx=0, pady=0, ipadx=1, ipady=1)

        self.update_scrollregion()

        # New task frame
        self.new_task_frame_style = ttkb.Style()
        self.new_task_frame_style.configure("new_task.TFrame", background=self.WHITE_1)
        self.new_task_frame = ttkb.Frame(self.main_frame, style="new_task.TFrame")
        self.new_task_frame.grid(column=0, row=2, pady=16)

        # New task entry
        self.new_task_entry = ttkb.Entry(self.new_task_frame, style="light.TEntry", font=(self.FONT_NAME, 10))
        self.new_task_entry.config(width=40)
        self.new_task_entry.focus()
        self.new_task_entry.insert(0, "Nova Task")
        self.new_task_entry.grid(column=0, row=0, padx=5, pady=5)

        # New task button
        self.new_task_btn_style = ttkb.Style()
        self.new_task_btn_style.configure("new_task.TButton", borderwidth=0)
        self.new_task_btn_style.map(
            "new_task.TButton", background=[("!active", self.LIGHT_PURPLE), ("active", self.PURPLE)]
        )
        self.new_task_btn = ttkb.Button(self.new_task_frame, text="+", style="new_task.TButton")
        self.new_task_btn.grid(column=1, row=0)

    def update_scrollregion(self):
        self.tasks_inner_frame.update_idletasks()
        self.tasks_canvas.configure(scrollregion=self.tasks_canvas.bbox("all"))

