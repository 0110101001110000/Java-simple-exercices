
import ttkbootstrap as ttkb
import tkinter.font as tkFont


# Init ---------------------------------------------------------------------- #


class Browser:

    def __init__(self, root: ttkb.Window):


        # Constants

        # Main window
        self.WINDOW_TITLE     = "Browser"
        self.WINDOW_ICON      = "./assets/icons/logo.ico"
        self.WINDOW_MIN_SIZE  = (800, 600)
        self.WINDOW_MAX_SIZE  = (None, None)
        self.WINDOW_RESIZABLE = (True, True)

        # Fonts
        # self.ARIAL_FONT      = tkFont.Font(family="Arial", size=12, weight=tkFont.NORMAL)
        self.ARIAL_FONT      = "Arial"
        # self.SANS_SERIF_FONT = tkFont.Font(family="Microsoft Sans Serif", size=12, weight=tkFont.NORMAL)
        self.SANS_SERIF_FONT = "Microsoft Sans Serif"

        # Colors
        self.WHITE_COLOR   = "#ffffff"
        self.WHITE_COLOR_1 = "#f9f9fb"
        self.BLACK_COLOR   = "#1e1d24"
        self.BLACK_COLOR_1 = "#2b2a33"
        self.BLACK_COLOR_2 = "#42414d"


        # Main window

        self.root = root
        self.root.title(self.WINDOW_TITLE)
        self.root.iconbitmap(self.WINDOW_ICON)
        self.root.minsize(*self.WINDOW_MIN_SIZE)
        self.root.maxsize(*self.WINDOW_MAX_SIZE)
        self.root.resizable(*self.WINDOW_RESIZABLE)

