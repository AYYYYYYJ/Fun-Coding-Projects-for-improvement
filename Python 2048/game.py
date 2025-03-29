import sys
import random
import copy

def main():
    game()


def game():
    score, game_running, board, board_num_2048, total_num_2048 = initialize()
    while game_running:
        if len(board) == 1 and len(board[0]) == 1:
            game_running = False
        if len(board) > 1 or len(board[0]) > 1:
            print("                         w")
            print("What direction?        a   d")
            print("                         s")
            direction = input("").lower()
            if direction == "↑↑↓↓←→←→ba" or direction == "up up down down left right left right ba":
                board = cheat_code(board)
            elif direction == "q" or direction == "quit":
                sys.exit("\nGoodbye!")
            elif direction != "w" and direction != "a" and direction != "s" and direction != "d":
                print("Please input either \"w\", \"a\", \"s\", or \"d\" (case insensitively)\n")
                continue

            board, score, game_running = board_logic(board, direction, score)
            check_2048(board, board_num_2048, total_num_2048)
            print_board(board, score)
        if not game_running:
            print("*"*38)
            for _ in range(5):
                print(" RIP You have no more available moves")
            print("*"*38)
            print(f"Final score: {score}")

            game_run_again = input("\nDo you want to play again? (y/n) ").lower()
            if game_run_again == "y" or game_run_again == "yes":
                score, game_running, board, board_num_2048, total_num_2048 = initialize()
    print("\nokay bye")
    
def initialize():
    score = 0
    board_num_2048 = 0
    total_num_2048 = 0
    game_running = True
    height, width = start_text()

    board = [[""]*width for _ in range(height)]
    board = first_two_nums(board)
    print_board(board, score)
    return score, game_running, board, board_num_2048, total_num_2048

def first_two_nums(board):
    i = 0
    while i < 2:
        #find a random index of a random row in board
        rand_x = random.randrange(len(board))
        rand_y = random.randrange(len(board[0]))
        if board[rand_x][rand_y] == "":
            rand_num = random.randrange(0, 10)
            if rand_num == 0:
                num = "4"
            else:
                num = "2"
            i += 1
            board[rand_x][rand_y] = num
            if len(board) == 1 and len(board[0]) == 1:
                return board
    return board

def print_board(board, score):
    print("-" + "-"*9*len(board[0]))
    for row in range(len(board)):
        print(("|" + " "*8)*len(board[0]) + "|")
        for col in range(len(board[row])):
            board_len = len(board[row][col])
            num = board[row][col]
            if board_len == 1:
                board_segment = "|   " + num + "    "
            elif board_len == 2:
                board_segment = "|   " + num + "   "
            elif board_len == 3:
                board_segment = "|  " + num + "   "
            elif board_len == 4:
                board_segment = "|  " + num + "  "
            elif board_len == 5:
                board_segment = "| " + num + "  "
            elif board_len == 6:
                board_segment = "| " + num + " "
            elif board_len == 7:
                board_segment = "|" + num + " "
            elif board_len == 8:
                board_segment = "|" + num + ""
            else:
                board_segment = "|        "
            print(board_segment, end="")
        print("|")
        print(("|" + " "*8)*len(board[0]) + "|")
        print("-" + "-"*9*len(board[0]))
    print("Score:", score)



def start_text():
    #maybe allow diff amount of rows and columns later idk
    print("\n********************************************************************************************************************")
    print("HELLO WORLD... This is the popular game 2048 made in python and run in VSCode's terminal.")
    print("The game is simple. When promted for a direction, input either")
    print("\"w\", \"a\", \"s\", or \"d\" (case insensitively), corresponding to up, left, down, and right respectively.")
    print("When numbers collide, if they're the same, then they'll add together, but if not, they won't.")
    print("Additionally, a new number with a 90% probability of being a 2 and a 10% probability of being a 4")
    print("will spawn after every new input.")
    print("The goal of this game is to get a block that says \"2048\" before there are no valid moves remaining.")
    print("Additionally, my version of 2048 allows for different sized boards than usual.")
    print("The usual size of a 2048 board is 4x4, but when prompted, you can make the side lengths any size (1-14 recommended)")
    print("p.s. To make a 4x4 board, you can also just type in \"default\" or \"d\" into the height when asked")
    print("Anyway, good luck and have fun!")
    print("*********************************************************************************************************************\n")
    while True:
        try:
            height = input("What height do you want the board to be? ").lower()
            if height == "default" or height == "d":
                return 4, 4
            else:
                width = input("What width do you want the board to be? ")
                print()
                if (int(height) < 0 or int(width) < 0):
                    print("The number has to be greater than 0 bruh")
                    continue
                return int(height), int(width)
        except ValueError:
            print("Please insert actual numbers")


def board_logic(board, direction, score):
    copy_board = copy.deepcopy(board)
    board = move(board, direction)
    board, score = combine(board, direction, score)
    board = move(board, direction)
    #if the board changed state, make a new num appear
    if copy_board != board:
        board = random_space(board)

    for row in range(len(board)):
        for col in range(len(board[row])):
            #checking if there are still open spaces left
            if board[row][col] == "":
                return board, score, True

    #if there are 2 of the same nums next to each other, continue
    for row in range(len(board)):
        for col in range(len(board[row]) - 1):
            if board[row][col] == board[row][col + 1]:
                return board, score, True
    #if there are 2 of the same nums above/below each other, continue
    for row in range(1, len(board)):
        for col in range(len(board[row])):
            if board[row - 1][col] == board[row][col]:
                return board, score, True

    return board, score, False

def random_space(board):
    empty_spaces = []
    for row in range(len(board)):
        for col in range(len(board[row])):
            if board[row][col] == "":
                empty_spaces.append([row, col])
    if len(empty_spaces) == 0:
        return board
    rand_x, rand_y = random.choice(empty_spaces)
    rand_num = random.randrange(0, 10)
    if rand_num == 0:
        board[rand_x][rand_y] = "4"
    else:
        board[rand_x][rand_y] = "2"
    return board

def move(board, direction):
    match direction:
        case "w":
            for col in range(len(board[0])):
                #look at rows 1, 2, 3
                for row in range(1, len(board)):
                    if board[row][col] != "":
                        #while the row is greater than 0 and there is an empty space above the num
                        while row > 0 and board[row - 1][col] == "":
                            board[row - 1][col] = board[row][col]
                            board[row][col] = ""
                            row -= 1
        case "s":
            for col in range(len(board[0])):
                #look at rows 2, 1, 0
                for row in range(len(board) - 2, -1, -1):
                    if board[row][col] != "":
                        #while the row is at least 1 above the last one and there is an empty space below the num
                        while row < len(board) - 1 and board[row + 1][col] == "":
                            board[row + 1][col] = board[row][col]
                            board[row][col] = ""
                            row += 1
        case "a":
             for row in range(len(board)):
                for col in range(1, len(board[row])):
                    if board[row][col] != "":
                        while col > 0 and board[row][col - 1] == "":
                            board[row][col - 1] = board[row][col]
                            board[row][col] = ""
                            col -= 1
        case "d":
            for row in range(len(board)):
                for col in range(len(board[row]) - 2, -1, -1):
                    if board[row][col] != "":
                        while col < len(board[row]) - 1 and board[row][col + 1] == "":
                            board[row][col + 1] = board[row][col]
                            board[row][col] = ""
                            col += 1
    return board

def combine(board, direction, score):
    match direction:
        case "w":
            #look at rows 1, 2, 3
            for row in range(1, len(board)):
                for col in range(len(board[row])):
                    if board[row][col] == board[row-1][col] and board[row][col] != "":
                        score += int(board[row][col])*2
                        board[row-1][col] = str(int(board[row][col])*2)
                        board[row][col] = ""
        case "s":
            for row in range(len(board) - 2, -1, -1):
                for col in range(len(board[row])):
                    if board[row][col] == board[row+1][col] and board[row][col] != "":
                        score += int(board[row][col])*2
                        board[row+1][col] = str(int(board[row][col])*2)
                        board[row][col] = ""
        case "a":
            for row in range(len(board)):
                for col in range(1, len(board[row])):
                    if board[row][col] == board[row][col - 1] and board[row][col] != "":
                        score += int(board[row][col])*2
                        board[row][col-1] = str(int(board[row][col])*2)
                        board[row][col] = ""
        case "d":
            for row in range(len(board)):
                for col in range(len(board[row]) - 2, -1, -1):
                    if board[row][col] == board[row][col + 1] and board[row][col] != "":
                        score += int(board[row][col])*2
                        board[row][col+1] = str(int(board[row][col])*2)
                        board[row][col] = ""
    return board, score

def cheat_code(board):
    #↑↑↓↓←→←→BA
    for row in range(len(board)):
        for col in range(len(board[row])):
            board[row][col] = "2048"
    return board

def check_2048(board, board_num_2048, total_num_2048):

    for row in range(len(board)):
        for col in range(len(board[row])):
            if board[row][col] == "2048":
                board_num_2048 += 1
                #on the next iteration, num_wins and num_2048 should be equal, so the msg wont play after every turn
                #only plays if it counts a greater amount than was saved
                if board_num_2048 > total_num_2048:
                    for _ in range(board_num_2048 - total_num_2048):
                        print("*"*40)
                        for _ in range(3):
                            print("CONGRATS!!!!! YOU GOT A TWENTY FORTY EIGHT!!!!")
                        print("*"*40)
                    total_num_2048 = board_num_2048

if __name__ == "__main__":
    main()
