from Tkinter import *
from array import array

class Model():
    playerIsX=True
    def __init__(self):
        self.game_board = array('I', [1, 1, 1, 1, 1, 1, 1, 1, 1])
        self.game_state = 4
        
    def get_game_board(self):
        return self.game_board
    
    def __check_cat(self):
        for x in range(9):
            if self.game_board[x]==1:
                return False

        return True

    #TODO: Remove
    #def __change_win(self, player):
    #    if player:
    #        self.game_state=1
    #        #verify these constants later
    #    else:
    #        self.game_state=2

    def check_end_game(self):
        ##will return 1 for x win, 2 for y win and 3 for cat. Otherwise, it will return 4 for continue
        #checking for x win
        if self.check_win(2):
            return 1
        #checking for y win
        elif self.check_win(3):
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
    def __init__(self, model):
        self.model = model

    def change_state(self, board_space):
        validMove = False
        #if X
        if self.model.playerIsX and self.model.game_board[board_space]==1:
            self.model.game_board[board_space]=2
            self.model.playerIsX=not self.model.playerIsX
            validMove = True
        elif self.model.game_board[board_space]==1:
            self.model.game_board[board_space]=3
            self.model.playerIsX=not self.model.playerIsX
            validMove = True

        self.model.game_state = self.model.check_end_game()
        
        return validMove
    
    def on_click(self, board_space):
        self.change_state(board_space)
        self.view.create_widgets()
        
    def determine_game_piece(self, game_board_integer):
        if game_board_integer == 2:
            return 'X'
        elif game_board_integer == 3:
            return 'Y'
        else:
            return '-'
    #move to controller
    def determine_winner_text(self):
        game_state = self.model.get_game_state()
        message=""
        if self.model.game_state==1:
            message ="X Wins"
        elif self.model.game_state==2:
            message= "Y Wins"
        elif self.model.game_state==3:
            message= "Cat"
        else:
            message = ""
        return message

#this is the view
class GUIView(Frame):
    #View
    def __init__(self, master, controller):
        Frame.__init__(self, master)
        self.grid()
        self.controller = controller
        self.create_widgets()

    def create_widgets(self):
        vertical_line=0
        for rowSpace in range(9):
                button_text = self.model.get_game_board()[rowSpace]
                #get the text from the board and make sure that you convert it for the GUI
                button_text = self.controller.determine_game_piece(button_text)
                button = Button(self, text=button_text, height=10, width=10, command=lambda x=rowSpace: self.controller.on_click(x))
                button.grid(row = rowSpace/3, column = vertical_line)
                vertical_line+=1
                if vertical_line==3:
                    vertical_line=0
        
        game_state_label = Label(self)
        game_state_label.config(text = self.controller.determine_winner_text())
        game_state_label.grid(row = 4, column = 1)
        

#TODO: Implement the text based controller.
class TextView:

    def __init__(self, model, controller):
        self.model = model
        self.controller = controller

    def printGameBoard(self):
        currentBoard = self.model.get_game_board()

        print self.controller.determine_game_piece(currentBoard[0]) + " " + self.controller.determine_game_piece(currentBoard[1]) + " " + self.controller.determine_game_piece(currentBoard[2])
        print self.controller.determine_game_piece(currentBoard[3]) + " " + self.controller.determine_game_piece(currentBoard[4]) + " " + self.controller.determine_game_piece(currentBoard[5])
        print self.controller.determine_game_piece(currentBoard[6]) + " " + self.controller.determine_game_piece(currentBoard[7]) + " " + self.controller.determine_game_piece(currentBoard[8])

    def game_loop(self):
        print
        print
        print "\n==============================================\n"
        print "X's Turn" if self.model.playerIsX else "Y's Turn"
        print "\n==============================================\n"
        print "0 1 2\n3 4 5\n6 7 8"

        print "\n======\n"

        self.printGameBoard()

        print "\n======\n"

        needInput = True
        while needInput:

            moveInput = raw_input("Where do you want to play?")

            try:
                move = int(moveInput)
                
                if self.controller.change_state(move):
                    needInput = False
                    print "INPUT TAKEN"
                    #move successfl
                else:
                    print "INPUT NOT TAKEN. TRY AGAIN"
                    needInput = True

            except ValueError:
                print "INPUT NOT TAKEN. TRY AGAIN"
                needInput = True

        #check for win
        if self.model.game_state == 1:
            print "\n\n!!!!!!!!!!"
            self.printGameBoard()
            print "\nX wins"
            print "!!!!!!!!!!"
            return False
        elif self.model.game_state == 2:
            print "\n\n!!!!!!!!!!"
            self.printGameBoard()
            print "\nY wins"
            print "!!!!!!!!!!"
            return False
        elif self.model.game_state == 3:
            print "\n\n!!!!!!!!!!"
            self.printGameBoard()
            print "\nDraw"
            print "!!!!!!!!!!"
            return False
        else:
            print "Continue..."
            return True

m = Model()
c = Controller(m)

choice = raw_input("Do you want to use GUI (1) or Text (2):")

view = None

if choice == "1":
    mainWindow = Tk()
    view = GUIView(mainWindow, c)
    mainWindow.mainloop()
elif choice == "2":
    view = TextView(m, c)

    gameIsActive = True
    while gameIsActive:
        gameIsActive = view.game_loop()
else:
    print "Please enter one of the listed options. Bye."

print "Game over"



#TODO: Implement testing methods in a seperate class.
