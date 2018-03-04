import unittest
import MVCTicTacToe


# MVCTicTacToe Project
# CSE 311 Section A
# Professor: Dr. Kiper
# Written by: Travis Hawks, Michael Gentile, Connor Schmidt, Nick Hutchison

# model
# Test game board starts empty (1), test changes for x / y on gameboard
# Test win conditions: vertical, horizontal, diagnol, no winner

# controller
# change state
# determine game piece
# winner text

# View
# No tests for either view class.
# Cant test GUI
# text class only does print statement and calls to other class methods (tested previously)

class TestMVCTicTacToe(unittest.TestCase):

    # MODEL class block start
    # Testing the initial board is empty of moves
    def test_get_game_board_initial(self):
        # Java version of the statement I want
        # Model m = new MVCTicTacToe.Model()
        m = MVCTicTacToe.Model()
        for x in m.game_board:
            self.assertEqual(x, 1)
        print("Test 1 complete.")

    # Testing the get_game_board. Should return current state of game.
    def test_get_game_board_midState(self):
        m = MVCTicTacToe.Model()
        m.game_board = [1, 2, 3, 1, 2, 3, 1, 2, 3]
        v = m.get_game_board()
        for x in range(0, 9):
            self.assertEqual(v[x], m.game_board[x])
        print("Test 2 complete.")

    # checking check_win for horizontal, vertical, diagonal cases
    def test_check_win_horizontal(self):
        m = MVCTicTacToe.Model()
        m.game_board = [2, 2, 1, 1, 1, 1, 1, 1, 1]
        self.assertFalse(m.check_win(2))
        m.game_board[2] = 2
        self.assertTrue(m.check_win(2))
        print("Test 3 complete.")

    def test_check_win_vertical(self):
        m = MVCTicTacToe.Model()
        m.game_board = [2, 1, 1, 2, 1, 1, 1, 1, 1]
        self.assertFalse(m.check_win(2))
        m.game_board[6] = 2
        self.assertTrue(m.check_win(2))
        print("Test 4 complete.")

    def test_check_win_diagnol(self):
        m = MVCTicTacToe.Model()
        m.game_board = [2, 1, 1, 1, 2, 1, 1, 1, 1]
        self.assertFalse(m.check_win(2))
        m.game_board[8] = 2
        self.assertTrue(m.check_win(2))
        print("Test 5 complete.")

    # End of MODEL class test block
    # Start of CONTROLLER class test block

    # allow change state, not allow change state
    def test_change_state_allow(self):
        m = MVCTicTacToe.Model()
        v = MVCTicTacToe.Controller(m)
        self.assertTrue(v.change_state(0))
        print("Test 6 complete.")

    def test_change_state_not_allowed(self):
        m = MVCTicTacToe.Model()
        v = MVCTicTacToe.Controller(m)
        m.game_board[0] = 2
        self.assertFalse(v.change_state(0))
        print("Test 7 complete.")

    # Testing determine_game_piece. test: 2== 'X', 3 == 'Y', 1 == '-'
    def test_determine_game_piece_(self):
        m = MVCTicTacToe.Model()
        v = MVCTicTacToe.Controller(m)
        self.assertEqual(v.determine_game_piece(1), '-')
        self.assertEqual(v.determine_game_piece(2), 'X')
        self.assertEqual(v.determine_game_piece(3), 'Y')
        print("Test 8 complete.")

    # Testing determine winner text
    def test_determine_winner_text(self):
        m = MVCTicTacToe.Model()
        v = MVCTicTacToe.Controller(m)
        m.game_state = 1
        self.assertEqual(v.determine_winner_text(), "X Wins")
        m.game_state = 2
        self.assertEqual(v.determine_winner_text(), "Y Wins")
        m.game_state = 3
        self.assertEqual(v.determine_winner_text(), "Cat")
        print("Test 9 complete.")

        # END test CONTROLLER block


if __name__ == "__main__":
    unittest.main()
