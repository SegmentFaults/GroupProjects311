from Tkinter import *
from enum import Enum
from array import array

class Model():
    player=True
    def __init__(self):
        self.game_board = array('I', [1, 1, 1, 1, 1, 1, 1, 1, 1])
        self.game_state = 4
        
    def get_game_board(self):
        return self.game_board
    
    def __check_cat(self):
        for x in range(9):
            if not self.game_board[x]==2 or not self.game_board[x]==3:
                return False

        return True
    #model
    def __change_win(self, player):
        if player:
            self.game_state=1
            #verify these constants later
        else:
            self.game_state=2
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
    def check_win(self, playerToCheck):
        #check vertical
        #check horizontal
        #check diagonal
        if playerToCheck:
            playerToCheck=3
        else:
            playerToCheck=2
        for x in range(3):
            if self.game_board[x]==playerToCheck and self.game_board[x+3]==playerToCheck and self.game_board[x+6]==playerToCheck:
                print "works 1"
                return True
            elif self.game_board[x*3]==playerToCheck and self.game_board[x*3+1]==playerToCheck and self.game_board[x*3+2]==playerToCheck:
                print "works 2"
                return True
        if (self.game_board[0]==playerToCheck and self.game_board[4]==playerToCheck and self.game_board[8]==playerToCheck ) or (self.game_board[2]==playerToCheck and self.game_board[4]==playerToCheck and self.game_board[6]==playerToCheck):
            print "works 3"
            return True
        return False
    def get_game_state(self):
        return self.game_state
#controller mediates between the model and the view
class Controller(object):
    #translates user action into changes in the model
    def __init__(self, model,view):
        self.view=view
        self.model = model
        # it's going to be a lot easier just to not use a double sided
        print "Filler"
    def change_state(self, board_space):
        if self.model.player and self.model.game_board[board_space]==1:
            self.model.game_board[board_space]=2
            self.model.player=not self.model.player
        elif self.model.game_board[board_space]==1:
            self.model.game_board[board_space]=3
            self.model.player=not self.model.player
        winStatus = self.model.check_win(self.model.player)
        if not winStatus == 4:
            self.game_state=winStatus
    
    def on_click(self, board_space):
        self.change_state(board_space)
        self.view.create_widgets()
        
    def switch_ui(self, currentLogic):
        #TODO create method that swaps them
        print "Filler"

    def determine_button_text(self, game_board_integer):
        if game_board_integer == 2:
            return 'X'
        elif game_board_integer == 3:
            return 'Y'
        else:
            return '-'
    #move to controller
    def determine_label_text(self, game_state):
        if self.model.game_state==1:
            return "X Wins"
        elif self.model.game_state==2:
            return "Y Wins"
        elif self.model.game_state==3:
            return "Cat"
        else:
            return ""

#this is the view
class GUIView(Frame):
    #View
    def __init__(self, master, Controlle):
        Frame.__init__(self, master)
        self.grid()
        self.model = Model()
        self.controller = Controller(self.model, self)
        self.create_widgets()
    def create_widgets(self):
        vertical_line=0
        for rowSpace in range(9):
                button_text = self.model.get_game_board()[rowSpace]
                #get the text from the board and make sure that you convert it for the GUI
                button_text = self.controller.determine_button_text(button_text)
                button = Button(self, text=button_text, height=10, width=10, command=lambda x=rowSpace: self.controller.on_click(x))
                button.grid(row = rowSpace/3, column = vertical_line)
                vertical_line+=1
                if vertical_line==3:
                    vertical_line=0
        
        game_state_label = Label(self)
        game_state_label.config(text = self.controller.determine_label_text(self.model.get_game_state()))
        game_state_label.grid(row = 4, column = 1)
        switch_interface_button = Button(self, text="Switch UI", width =10, height =10, command=lambda model_instance=self.model: self.controller.switch_ui(model_instance))
        switch_interface_button.grid(row = 4, column = 2)

   
#TODO: Implement the text based controller.
class TextView(Controller):
    print "We need to seperate the model and the controller first"


mainWindow = Tk()
m = Model()
obj = GUIView(mainWindow, m)
mainWindow.mainloop()



#TODO: Implement testing methods in a seperate class.
