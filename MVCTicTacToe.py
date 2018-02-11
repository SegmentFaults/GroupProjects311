from Tkinter import *
from enum import Enum
from array import array


class ButtonState(Enum):
        NONE = 1
        X = 2
        O = 3


class GameState(Enum):
    NONE =1
    WINX = 2
    WINY = 3
    CAT = 4

#this is the controller
class Logic(object):
    player=True
    def __init__(self):
        # it's going to be a lot easier just to not use a double sided
        self.gameBoard = array('I', [1, 1, 1, 1, 1, 1, 1, 1, 1])
        self.gameState = 4
    def getGameBoard(self):
        return self.gameBoard

    def changeState(self, board_space):
        if self.player:
            self.gameBoard[board_space]=2
        else:
            self.gameBoard[board_space]=3
        self.player=not self.player
        winStatus = self.checkWin()
        if not winStatus == 4:
            self.gameState=winStatus
    def checkWin(self):
        ##will return 1 for x win, 2 for y win and 3 for cat. Otherwise, it will return 4 for continue
        #checking for x win
        if self.__checkWin(2):
            return 1
        #checking for y win
        elif self.__checkWin(3):
            return 2
        #checking for cat
        elif self.__checkCat():
            return 3
        #else send back 4 for nothing
        return 4
        
    def __checkWin(self, playerToCheck):
        #check vertical
        #check horizontal
        #check diagonal
        
        for x in range(3):
            if self.gameBoard[x]==playerToCheck and self.gameBoard[x+3]==playerToCheck and self.gameBoard[x+6]==playerToCheck:
                return True
            elif self.gameBoard[x*3]==playerToCheck and self.gameBoard[x*3+1]==playerToCheck and self.gameBoard[x*3+2]==playerToCheck:
                return True
        if (x[0]==playerToCheck and x[4]==playerToCheck  and x[8]==playerToCheck ) or (x[2]==playerToCheck  and x[4]==playerToCheck  and x[6]==playerToCheck):
            return True
        return False
    
    def __checkCat(self):
        for x in range(9):
            if not self.gameBoard[x]==2 or not self.gameBoard[x]==3:
                return False
            
        return True
    
#this is the view
class Application(Frame, Logic):

    def __init__(self, master):
        Frame.__init__(self, master)
        self.grid()
        self.logic = Logic()
        self.create_widgets()

    def on_click(self, board_space):
        self.logic.changeState(board_space)
        self.create_widgets()

    def create_widgets(self):
        verticalLine=0
        for rowSpace in range(9):
                buttonText = self.logic.getGameBoard()[rowSpace]
                #get the text from the board and make sure that you convert it for the GUI
                buttonText = self.determine_button_text(buttonText)
                button = Button(self, text=buttonText, height=10, width=10, command=lambda x=rowSpace: self.on_click(x))
                button.grid(row = rowSpace/3, column = verticalLine)
                verticalLine+=1
                if verticalLine==3:
                    verticalLine=0
        gameStateLabel = Label(self, text = self.determine_button_text(self.logic.gameState))
        gameStateLabel.grid(row = 4, column = 1)
    def determine_button_text(self, gameBoardInteger):
        if gameBoardInteger == 2:
            return 'X'
        elif gameBoardInteger == 3:
            return 'Y'
        else:
            return '-'
    
    def determine_label_text(self, gameState):
        if self.logic.gameState==1:
            return "X Wins"
        elif self.logic.gameState==2:
            return "Y Wins"
        elif self.logic.gameState==3:
            return "Cat"
        else:
            return "No Winner"
mainWindow = Tk()
obj = Application(mainWindow)
mainWindow.mainloop()