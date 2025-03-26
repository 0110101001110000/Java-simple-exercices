from idlelib.colorizer import prog_group_name_to_tag

import requests
from tkinter import messagebox
import ttkbootstrap as ttkb
from PIL import Image, ImageTk


# Init ---------------------------------------------------------------------- #


class Gui:
    def __init__(self, root):

        # Constants
        self.API_URL      = "http://localhost:8080/task"
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
        self.WHITE_2      = "#d7dade"


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
        self.background_img = Image.open("assets/images/background.jpg")
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
        self.tasks_scrollbar = ttkb.Scrollbar(self.tasks_frame, bootstyle="round.default", command=self.tasks_canvas.yview)
        self.tasks_scrollbar.grid(column=1, row=0, sticky="ns")

        self.tasks_canvas.configure(yscrollcommand=self.tasks_scrollbar.set)

        self.tasks_inner_frame = ttkb.Frame(self.tasks_canvas, style="tasks_frame.TFrame")
        self.tasks_canvas.create_window((450/2, 0), window=self.tasks_inner_frame, anchor='n')

        # Tasks list
        self.task_entry_status = "normal"
        self.displayed_tasks: [{"finish": ttkb.Button, "task": ttkb.Entry, "edit": ttkb.Button, "delete": ttkb.Button}] = []
        self.display_all_tasks()

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
        self.new_task_btn = ttkb.Button(self.new_task_frame, text="+", style="new_task.TButton", command=self.add_task)
        self.new_task_btn.grid(column=1, row=0)


    # Main methods

    def add_task(self):
        data = {
            "id": None,
            "taskName": self.new_task_entry.get()
        }
        try:
            response = requests.post(self.API_URL, json=data)
            if response.status_code == 200:
                self.new_task_entry.delete(0, "end")
                self.display_all_tasks()
            else:
                messagebox.showerror(
                    "Erro",
                    f"Falha ao adicionar tarefa. Status Code: {response.status_code}. Conteúdo: {response.content}."
                )
        except Exception as e:
            messagebox.showerror("Erro", f"Ocorreu um erro: {e}")

    def display_all_tasks(self):
        tasks: [{"id": int, "taskName": str}] or [] = self.get_all_tasks()

        # Button styles
        btn_style = ttkb.Style()
        btn_style.configure("TButton", font=(self.FONT_NAME, 10), borderwidth=0)
        finish_btn_style = ttkb.Style()
        finish_btn_style.configure("finish.TButton", foreground="#7dbc65")
        finish_btn_style.map("finish.TButton", background=[("!active", self.WHITE), ("active", self.WHITE_2)])
        edit_btn_style = ttkb.Style()
        edit_btn_style.configure("edit.TButton", foreground="gray")
        edit_btn_style.map("edit.TButton", background=[("!active", self.WHITE), ("active", self.WHITE_2)])
        delete_btn_style = ttkb.Style()
        delete_btn_style.configure("delete.TButton", foreground="#ff8484")
        delete_btn_style.map("delete.TButton", background=[("!active", self.WHITE), ("active", self.WHITE_2)])
        row = 0
        self.displayed_tasks = []
        for task in tasks:
            # Finish task button
            finish_btn = ttkb.Button(self.tasks_inner_frame, text="✓", style="finish.TButton")
            finish_btn.config(width=1)
            finish_btn.grid(column=0, row=row, padx=5, pady=5, ipadx=1, ipady=1)

            # Task entry
            task_entry_style = ttkb.Style()
            task_entry_style.configure("task_entry.TEntry", borderwidth=0)
            task_entry_style.map("task_entry.TEntry", background=[("!active", self.WHITE), ("active", self.WHITE)])
            task_entry = ttkb.Entry(self.tasks_inner_frame, style="light.TEntry", font=(self.FONT_NAME, 10))
            task_entry.insert(0, task["taskName"])
            task_entry.config(width=40, state="readonly")
            task_entry.__setattr__("id", task["id"])
            task_entry.grid(column=1, row=row)

            # Task editor button
            edit_btn = ttkb.Button(self.tasks_inner_frame, text="✎", style="edit.TButton")
            edit_btn.config(width=1)
            edit_btn.grid(column=2, row=row, padx=5, pady=5, ipadx=1, ipady=1)
            edit_btn.__setattr__("active", False)
            edit_btn.bind("<1>", self.update_task)

            # Task delete button
            delete_btn = ttkb.Button(self.tasks_inner_frame, text="x", style="delete.TButton")
            delete_btn.config(width=1.25)
            delete_btn.grid(column=3, row=row, padx=0, pady=0, ipadx=1, ipady=1)
            delete_btn.bind("<1>", self.delete_task)

            self.displayed_tasks.append({
                "finish": finish_btn,
                "task": task_entry,
                "edit": edit_btn,
                "delete": delete_btn
            })

            row += 1

    def update_task(self, event):
        button = event.widget
        index  = None
        for i in range(len(self.displayed_tasks)):
            if self.displayed_tasks[i]["edit"] == button:
                index = i
                break

        task_row   = self.displayed_tasks[index]
        task_entry = task_row["task"]
        task_id    = task_entry.__getattribute__("id")
        edit_btn   = task_row["edit"]

        edit_btn.__setattr__("active", True if (edit_btn.__getattribute__("active") is False) else False)

        if button.__getattribute__("active") is True:
            task_entry.config(state="normal")
            edit_btn.config(text="✓")
            task_entry.focus()
        elif button.__getattribute__("active") is False:
            data = {
                "id": None,
                "taskName": task_entry.get()
            }
            try:
                response = requests.put(self.API_URL + f"/{task_id}", json=data)
                if response.status_code == 200:
                    task_entry.config(state="readonly")
                    edit_btn.config(text="✎")
                    self.display_all_tasks()
                else:
                    messagebox.showerror(
                        "Erro",
                        f"Falha ao editar tarefa. Status Code: {response.status_code}. Conteúdo: {response.json()}."
                    )
            except Exception as e:
                messagebox.showerror("Erro", f"Falha ao editar tarefa. Info: {e}.")

    def delete_task(self, event):
        button = event.widget
        index  = None
        for i in range(len(self.displayed_tasks)):
            if self.displayed_tasks[i]["delete"] == button:
                index = i
                break

        task_row   = self.displayed_tasks[index]
        task_entry = task_row["task"]
        task_id    = task_entry.__getattribute__("id")

        try:
            response = requests.delete(self.API_URL + f"/{task_id}")
            if response.status_code == 200:
                self.display_all_tasks()
            else:
                messagebox.showerror(
                    "Erro",
                    f"Falha ao remover tarefa. Status Code: {response.status_code}. Conteúdo: {response.json()}."
                )
        except Exception as e:
            messagebox.showerror("Erro", f"Falha ao remover tarefa. Info: {e}.")


    # Other methods

    def clicked_widget(self, event):
        widget_clicado = event.widget
        print(f"Clicou em: {widget_clicado}")
        widget_clicado.config(text="AU")

    def update_scrollregion(self):
        self.tasks_inner_frame.update_idletasks()
        self.tasks_canvas.configure(scrollregion=self.tasks_canvas.bbox("all"))

    def get_all_tasks(self):
        try:
            response = requests.get(self.API_URL)
            if response.status_code == 200:
                return response.json()
            else:
                messagebox.showerror(
                    "Erro",
                    f"Falha ao listar tarefas. Status Code: {response.status_code}. Conteúdo: {response.json()}."
                )
        except Exception as e:
            messagebox.showerror("Erro", f"Falha ao listar tarefas. Info: {e}.")
        return []

