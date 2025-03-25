

# Init ---------------------------------------------------------------------- #


class Gui:
    def __init__(self, root):

        # Constants
        self.WINDOW_TITLE = "Task Management System"
        self.FONT_NAME    = "Arial"
        self.X_MIN_SIZE   = 800
        self.Y_MIN_SIZE   = 600
        self.COLOR_1      = "#7a82ff"
        self.COLOR_2      = "#a2b4ff"

        # Window
        self.root = root
        self.root.config(padx=0, pady=0)
        self.root.title(self.WINDOW_TITLE)
        self.root.geometry(None)
        self.root.minsize(self.X_MIN_SIZE, self.Y_MIN_SIZE)
        self.root.maxsize(self.X_MIN_SIZE, self.Y_MIN_SIZE)
        self.root.resizable(False, False)

