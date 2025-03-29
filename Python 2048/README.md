2048 Game
2048 Game in VSCode Terminal
I made a 2048 Game that runs in the VSCode terminal. This project contains 11 methods (excluding main).

Method Descriptions:
    move(board, direction)
        Moves all tiles in the specified direction ("w", "a", "s", or "d") until they hit the edge or another tile. Empty spaces are ignored.

    combine(board, direction, score)
        After moving, this method merges adjacent tiles of the same value in the specified direction, doubling their value and updating the score.

    board_logic(board, direction, score)
        Handles the core game mechanics, including moving tiles, merging them, and spawning a new tile if the board state has changed. It also checks if the game should continue.

    random_space(board)
        Chooses a random empty space on the board and places a new tile (90% chance of 2, 10% chance of 4).

    first_two_nums(board)
        Initializes the board by placing two random 2s or 4s at the start of the game.

    check_2048(board, board_num_2048, total_num_2048)
        Checks if any tile has reached 2048. If a new 2048 tile is created, it prints a congratulatory message.

    print_board(board, score)
        Displays the current board in a formatted grid along with the player's score.

    initialize()
        Sets up the game by asking for board dimensions, initializing an empty board, placing the first two tiles, and displaying the initial state.

    game()
        Runs the game loop, taking player input for movement, updating the board, checking for game-over conditions, and handling restarting.

    start_text()
        Displays instructions and asks the player for the board size, allowing for custom dimensions or the default 4x4 board.

    cheat_code(board)
        If the player enters the Konami Code (↑↑↓↓←→←→BA), it instantly fills the board with 2048 tiles.
