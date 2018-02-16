from Tkinter import *
from enum import Enum
from array import array
#TODO: Break controller and model into two from the "Logic class"
#this is the controller
class Logic(object):
    player=True
    def __init__(self):
        # it's going to be a lot easier just to not use a double sided
        self.game_board = array('I', [1, 1, 1, 1, 1, 1, 1, 1, 1])
        self.game_state = 4
    #model
    def get_game_board(self):
        return self.game_board
    #controller
    def change_state(self, board_space):
        if self.player:
            self.game_board[board_space]=2
        else:
            self.game_board[board_space]=3
        self.player=not self.player
        winStatus = self.check_win()
        if not winStatus == 4:
            self.game_state=winStatus
    #model
    def check_end_game(self):
        ##will return 1 for x win, 2 for y win and 3 for cat. Otherwise, it will return 4 for continue
        #checking for x win
        if self.__check_win(2):
            return 1
        #checking for y win
        elif self.__check_win(3):
            return 2
        #checking for cat
        elif self.__check_cat():
            return 3
        #else send back 4 for nothing
        return 4
    #model
    def __check_win(self, playerToCheck):
        #check vertical
        #check horizontal
        #check diagonal

        for x in range(3):
            if self.game_board[x]==playerToCheck and self.game_board[x+3]==playerToCheck and self.game_board[x+6]==playerToCheck:
                return True
            elif self.game_board[x*3]==playerToCheck and self.game_board[x*3+1]==playerToCheck and self.game_board[x*3+2]==playerToCheck:
                return True
        if (x[0]==playerToCheck and x[4]==playerToCheck  and x[8]==playerToCheck ) or (x[2]==playerToCheck  and x[4]==playerToCheck  and x[6]==playerToCheck):
            return True
        return False
    #model
    def __check_cat(self):
        for x in range(9):
            if not self.game_board[x]==2 or not self.game_board[x]==3:
                return False

        return True

#this is the view
class GUIView(Frame, Logic):
    def __init__(self, master):
        Frame.__init__(self, master)
        self.grid()
        self.logic = Logic()
        self.create_widgets()
    #move to controller
    def on_click(self, board_space):
        self.logic.change_state(board_space)
        self.create_widgets()
    #move to controller
    def switch_ui(self, currentLogic):
    #TODO create method that swaps them

    def create_widgets(self):
        vertical_line=0
        for rowSpace in range(9):
                button_text = self.logic.get_game_board()[rowSpace]
                #get the text from the board and make sure that you convert it for the GUI
                button_text = self.determine_button_text(button_text)
                button = Button(self, text=button_text, height=10, width=10, command=lambda x=rowSpace: self.on_click(x))
                button.grid(row = rowSpace/3, column = vertical_line)
                vertical_line+=1
                if vertical_line==3:
                    vertical_line=0
        game_state_label = Label(self, text = self.determine_button_text(self.logic.game_state))
        game_state_label.grid(row = 4, column = 1)
        #TODO: Create a button to swap interfaces
        #switch_interface_button = (self, text = "Switch UI", command=lambda log=self.logic: self.switch_ui(logic))
        #switch_interface_button.grid(row = 4, column = 2)

    #move to controller
    def determine_button_text(self, game_board_integer):
        if game_board_integer == 2:
            return 'X'
        elif game_board_integer == 3:
            return 'Y'
        else:
            return '-'
    #move to controller
    def determine_label_text(self, game_state):
        if self.logic.game_state==1:
            return "X Wins"
        elif self.logic.game_state==2:
            return "Y Wins"
        elif self.logic.game_state==3:
            return "Cat"
        else:
            return "No Winner"
#TODO: Implement the text based logic.
class TextView(Logic):
    #TODO
mainWindow = Tk()
obj = GUIView(mainWindow)
mainWindow.mainloop()



#TODO: Implement testing methods in a seperate class.
