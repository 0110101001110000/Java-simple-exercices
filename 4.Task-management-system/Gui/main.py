
from gui import Gui

import ttkbootstrap as ttkb


# Init ---------------------------------------------------------------------- #


if __name__ == "__main__":

    root = ttkb.Window(themename="litera")
    gui  = Gui(root)

    root.mainloop()
