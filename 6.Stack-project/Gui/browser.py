
import ttkbootstrap as ttkb
from ttkbootstrap.constants import *
from ttkbootstrap.scrolled import ScrolledText
from ttkbootstrap.dialogs.dialogs import Messagebox


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

        # Browser start page
        self.START_PAGE = """

Navegador - Gerenciamento de Dados


Acesse seus dados com facilidade e eficiência!

O Navegador é um navegador personalizado desenvolvido para gerenciar dados em uma estrutura de pilha. Com essa ferramenta, você pode criar, editar e excluir pilhas, além de adicionar e remover elementos delas.

A estrutura de pilha é uma forma eficiente de organizar dados, pois permite a inserção e remoção de elementos em qualquer posição da pilha. Com o Navegador, você pode aproveitar essa vantagem para gerenciar seus dados de forma mais eficaz.

O Navegador oferece uma interface simples e intuitiva, permitindo que você navegue facilmente pelas suas pilhas e execute as operações necessárias. Além disso, a ferramenta é segura e confiável, garantindo a integridade dos seus dados.

Seja um profissional ou um usuário leigo, o Navegador é uma ferramenta essencial para qualquer pessoa que precisa gerenciar dados em uma estrutura de pilha. Experimente agora mesmo e descubra como podemos ajudar! 
"""


        # Window

        self.root = root
        self.root.title(self.WINDOW_TITLE)
        self.root.iconbitmap(self.WINDOW_ICON)
        self.root.minsize(*self.WINDOW_MIN_SIZE)
        self.root.maxsize(*self.WINDOW_MAX_SIZE)
        self.root.resizable(*self.WINDOW_RESIZABLE)


        # Root frame

        self.root.grid_columnconfigure(0, weight=1)
        self.root.grid_rowconfigure(1, weight=1)


        # Header frame

        self.header_background_style = SECONDARY

        self.header_frame = ttkb.Frame(self.root, style=self.header_background_style)
        self.header_frame.grid(row=0, column=0, sticky=EW)
        self.header_frame.grid_columnconfigure(1, weight=1)

        # History buttons frame
        self.history_frame = ttkb.Frame(self.header_frame, style=self.header_background_style)
        self.history_frame.grid(row=0, column=0, padx=24, pady=14)

        # History buttons style
        self.history_btn_style = ttkb.Style()
        self.history_btn_style.configure("history.light.TButton", font=(self.ARIAL_FONT, 14, "bold"), borderwidth=0)

        # History back ward button
        self.back_ward_btn = ttkb.Button(self.history_frame, text="❮", style="history.light.TButton")
        self.back_ward_btn.grid(row=0, column=0, padx=5)

        # History for ward button
        self.for_ward_btn = ttkb.Button(self.history_frame, text="❯", style="history.light.TButton")
        self.for_ward_btn.grid(row=0, column=1, padx=5)

        # Search bar frame
        self.search_bar_frame = ttkb.Frame(self.header_frame, style=self.header_background_style)
        self.search_bar_frame.grid(row=0, column=1, padx=24, pady=14, sticky=EW)
        self.search_bar_frame.grid_columnconfigure(0, weight=1)

        # Search bar entry
        self.search_bar_entry = ttkb.Entry(self.search_bar_frame, style="light.TEntry", font=(self.ARIAL_FONT, 12))
        self.search_bar_entry.focus()
        self.search_bar_entry.insert(0, "")
        self.search_bar_entry.grid(row=0, column=0, sticky=EW)

        # Search bar button style
        self.history_btn_style = ttkb.Style()
        self.history_btn_style.configure("search_bar.light.TButton", font=(self.ARIAL_FONT, 14, "bold"), borderwidth=0)

        # Search bar button
        self.search_bar_btn = ttkb.Button(self.search_bar_frame, text="🡢", style="search_bar.light.TButton")
        self.search_bar_btn.grid(row=0, column=1)

        # Header empty frame
        self.header_empty_frame = ttkb.Frame(self.header_frame, style=self.header_background_style)
        self.header_empty_frame.grid(row=0, column=2, pady=14, ipadx=(self.back_ward_btn.winfo_reqwidth() + self.for_ward_btn.winfo_reqwidth()))


        # Separator

        # ttkb.Separator(self.root, style=WARNING).grid(row=1, column=0, pady=10, sticky=EW)


        # Main frame

        self.main_frame = ttkb.Frame(self.root, style=DEFAULT)
        self.main_frame.grid(row=1, column=0, padx=10, pady=10, sticky=NSEW)
        self.main_frame.grid_rowconfigure(0, weight=1)
        self.main_frame.grid_columnconfigure(0, weight=1)

        self.page_text = ScrolledText(self.main_frame, wrap=WORD, autohide=YES, hbar=NO, bootstyle="round", font=(self.ARIAL_FONT, 16))
        self.page_text.insert(END, self.START_PAGE)
        self.page_text._text.configure(state='disabled')
        self.page_text.grid(row=0, column=0, sticky=NSEW)


        Messagebox.ok("", title="Ok message")

