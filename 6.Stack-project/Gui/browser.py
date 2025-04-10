
import requests
import ttkbootstrap as ttkb
from ttkbootstrap.constants import *
from ttkbootstrap.scrolled import ScrolledText
from ttkbootstrap.dialogs.dialogs import Messagebox


# Init ---------------------------------------------------------------------- #


class Browser:

    def __init__(self, root: ttkb.Window):


        # Constants

        # Api
        self.API_URL = "http://localhost:8080/api"

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

        # Browser default pages
        self.START_PAGE = "http://home.com"
        self.NOT_FOUND_PAGE = "http://notfound.com"

        # Window
        self.root = root
        self.root.title(self.WINDOW_TITLE)
        self.root.iconbitmap(self.WINDOW_ICON)
        self.root.minsize(*self.WINDOW_MIN_SIZE)
        self.root.maxsize(*self.WINDOW_MAX_SIZE)
        self.root.resizable(*self.WINDOW_RESIZABLE)


        # Variables

        self.current_page = "http://home.com"


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
        self.back_ward_btn = ttkb.Button(self.history_frame, text="❮", style="history.light.TButton", command=self.get_backward_history)
        self.back_ward_btn.grid(row=0, column=0, padx=5)

        # History for ward button
        self.for_ward_btn = ttkb.Button(self.history_frame, text="❯", style="history.light.TButton", command=self.get_forward_history)
        self.for_ward_btn.grid(row=0, column=1, padx=5)

        # Search bar frame
        self.search_bar_frame = ttkb.Frame(self.header_frame, style=self.header_background_style)
        self.search_bar_frame.grid(row=0, column=1, padx=24, pady=14, sticky=EW)
        self.search_bar_frame.grid_columnconfigure(0, weight=1)

        # Search bar entry
        self.search_bar_entry = ttkb.Entry(self.search_bar_frame, style="light.TEntry", font=(self.ARIAL_FONT, 12))
        self.search_bar_entry.focus()
        self.search_bar_entry.grid(row=0, column=0, sticky=EW)

        # Search bar button style
        self.history_btn_style = ttkb.Style()
        self.history_btn_style.configure("search_bar.light.TButton", font=(self.ARIAL_FONT, 14, "bold"), borderwidth=0)

        # Search bar button
        self.search_bar_btn = ttkb.Button(self.search_bar_frame, text="🡢", style="search_bar.light.TButton", command=self.get_page)
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
        self.page_text.insert(END, self.get_page_html(self.START_PAGE))
        self.update_search_bar_entry(self.START_PAGE)
        self.page_text._text.configure(state='disabled')
        self.page_text.grid(row=0, column=0, sticky=NSEW)


    # Main methods

    def get_page_html(self, url: str):
        data = {
            "url": url
        }
        try:
            response = requests.get(self.API_URL + "/page", json=data)
            if response.status_code == 200:
                return response.json()["htmlPage"]
            elif response.status_code == 404:
                return self.get_page_html(self.NOT_FOUND_PAGE)
            else:
                Messagebox.show_error(
                    f"Status Code: {response.status_code}.",
                    "Erro"
                )
        except Exception as e:
            Messagebox.show_error(f"Ocorreu um erro: {e}", "Erro")

    def get_page(self):
        data = {
            "url": self.search_bar_entry.get()
        }
        try:
            response = requests.get(self.API_URL + "/page", json=data)
            if response.status_code == 200:
                self.add_backward_history()
                print("A página foi listada")
                self.page_text._text.configure(state='normal')
                self.page_text.delete("1.0", END)
                self.page_text.insert(END, response.json()["htmlPage"])
                self.page_text._text.configure(state='disabled')
                self.current_page = response.json()["url"]
                self.update_search_bar_entry(data.get("url"))

            elif response.status_code == 404:
                self.add_backward_history()
                print("A página não foi listada, pois, ela não existe")
                self.page_text._text.configure(state='normal')
                self.page_text.delete("1.0", END)
                self.page_text.insert(END, self.get_page_html(self.NOT_FOUND_PAGE))
                self.page_text._text.configure(state='disabled')
                self.current_page = response.json()["url"]
                self.update_search_bar_entry(self.NOT_FOUND_PAGE)
            else:
                Messagebox.show_error(
                    f"Status Code: {response.status_code}.",
                    "Erro"
                )
        except Exception as e:
            Messagebox.show_error(f"Ocorreu um erro: {e}", "Erro")

    def add_backward_history(self):
        data = {
            "url": self.current_page
        }
        try:
            response = requests.post(self.API_URL + "/backward", json=data)
            if (response.status_code == 201) | (response.status_code == 200):
                print("Foi adicionado um novo histórico de backward")
            else:
                Messagebox.show_error(
                    f"Status Code: {response.status_code}.",
                    "Erro"
                )
        except Exception as e:
            Messagebox.show_error(f"Ocorreu um erro: {e}", "Erro")

    def get_backward_history(self):
        try:
            response = requests.get(self.API_URL + "/backward")
            if response.status_code == 200:
                print("O Histórico de backward foi listado")
                backward_page = response.json()["url"]
                self.delete_backward_history()

                data = {
                    "url": backward_page
                }
                response = requests.get(self.API_URL + "/page", json=data)

                self.add_forward_history()
                print("A página foi listada")
                self.page_text._text.configure(state='normal')
                self.page_text.delete("1.0", END)
                self.page_text.insert(END, response.json()["htmlPage"])
                self.page_text._text.configure(state='disabled')
                self.current_page = response.json()["url"]
                self.update_search_bar_entry(data.get("url"))
            else:
                Messagebox.show_error(
                    f"Status Code: {response.status_code}.",
                    "Erro"
                )
        except Exception as e:
            Messagebox.show_error(f"Ocorreu um erro: {e}", "Erro")

    def delete_backward_history(self):
        try:
            response = requests.delete(self.API_URL + "/backward")
            if response.status_code == 200:
                print("O Histórico de backward foi removido")
            else:
                Messagebox.show_error(
                    f"Status Code: {response.status_code}.",
                    "Erro"
                )
        except Exception as e:
            Messagebox.show_error(f"Ocorreu um erro: {e}", "Erro")

    def add_forward_history(self):
        data = {
            "url": self.current_page
        }
        try:
            response = requests.post(self.API_URL + "/forward", json=data)
            if (response.status_code == 201) | (response.status_code == 200):
                print("Foi adicionado um novo histórico de forward")
            else:
                Messagebox.show_error(
                    f"Status Code: {response.status_code}.",
                    "Erro"
                )
        except Exception as e:
            Messagebox.show_error(f"Ocorreu um erro: {e}", "Erro")

    def get_forward_history(self):
        try:
            response = requests.get(self.API_URL + "/forward")
            if response.status_code == 200:
                print("O Histórico de forward foi listado")
                forward_page = response.json()["url"]
                self.delete_forward_history()

                data = {
                    "url": forward_page
                }
                response = requests.get(self.API_URL + "/page", json=data)

                self.add_backward_history()
                print("A página foi listada")
                self.page_text._text.configure(state='normal')
                self.page_text.delete("1.0", END)
                self.page_text.insert(END, response.json()["htmlPage"])
                self.page_text._text.configure(state='disabled')
                self.current_page = response.json()["url"]
                self.update_search_bar_entry(data.get("url"))
            else:
                Messagebox.show_error(
                    f"Status Code: {response.status_code}.",
                    "Erro"
                )
        except Exception as e:
            Messagebox.show_error(f"Ocorreu um erro: {e}", "Erro")

    def delete_forward_history(self):
        try:
            response = requests.delete(self.API_URL + "/forward")
            if response.status_code == 200:
                print("O Histórico de forward foi removido")
            else:
                Messagebox.show_error(
                    f"Status Code: {response.status_code}.",
                    "Erro"
                )
        except Exception as e:
            Messagebox.show_error(f"Ocorreu um erro: {e}", "Erro")


    # Other methods

    def update_search_bar_entry(self, url: str):
        self.search_bar_entry.delete(0, END)
        self.search_bar_entry.insert(END, url)
