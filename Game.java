/**
 *  This class is the main class of the Movie theater. you play as your average
 *  joe wandering the theater.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Jose Moreno
 * @version 2016.02.29
 */

public class Game {
    private Parser parser;
    private Player player;

    /**
     * Initializes a new Game instance, creates rooms, and sets up the parser.
     * The player's starting position is also initialized.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Creates all the rooms in the game, sets their exits, adds items to rooms, 
     * and initializes the player to the starting room (parking lot).
     */
    private void createRooms() {
        Room outside, ticketBooth, lobby, frontDesk, bathroom, theater, parking, arcade;

        // Create rooms
        parking = new Room("in the Parking lot");
        outside = new Room("outside of the theater");
        ticketBooth = new Room("at the ticket booth");
        lobby = new Room("in the lobby of the movie theater");
        bathroom = new Room("in the Bathroom");
        theater = new Room("at the seating for movie theater");
        frontDesk = new Room("at the Front desk, they have snacks");
        arcade = new Room("at the Old school arcade");

        // Set exits
        outside.setExit("east", ticketBooth);
        outside.setExit("south", parking);
        parking.setExit("north", outside);
        ticketBooth.setExit("north", lobby);
        ticketBooth.setExit("west", outside);
        lobby.setExit("east", bathroom);
        lobby.setExit("north", theater);
        lobby.setExit("west", frontDesk);
        bathroom.setExit("west", lobby);
        theater.setExit("south", lobby);
        frontDesk.setExit("west", arcade);
        frontDesk.setExit("east", lobby);
        arcade.setExit("east", frontDesk);

        // Add items to rooms
        parking.addItem("keys");
        parking.addItem("wallet");
        ticketBooth.addItem("ticket");
        frontDesk.addItem("dibs");
        frontDesk.addItem("icee");
        arcade.addItem("token");

        // Initialize player starting position
        player = new Player(parking);
    }

    
    /**
     * Starts the game by printing welcome message and entering main game loop
     */
    public void play() {
        printWelcome();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Goodbye.");
    }

    /**
     * welcome message
     */
    private void printWelcome() {
        System.out.println("\nWelcome to the Titan Luxe!");
        System.out.println("We have very basic movies.");
        System.out.println("Your goal is to explore and find your way to the theater.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * processes the given command
     * @param command the command to process
     * @return true if the game should be quit, false otherwise
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case BACK:
                player.goBack();
                break;
            case TAKE:
                takeItem(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    /**
     * shows commands and prints message
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You don't know what movie to watch.");
        System.out.println("\nYour command words are:");
        parser.showCommands();
    }

    /**
     * moves the player to new rooms in specified direction
     * gives message if can not move in that direction
     * 
     * @param command the command containing the direction to move
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            player.moveTo(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }
    
    /**
     * Allows the player to take an item from the current room.
     * If the item exists in the room, it is added to the player's inventory.
     * 
     * @param command the command containing the item to take
     */
    private void takeItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }

        String item = command.getSecondWord();
        Room currentRoom = player.getCurrentRoom();

        player.takeItem(item, currentRoom);  
    }

    /**
     * Ends the game if the player types "quit".
     * 
     * @param command the quit command
     * @return true if the game should end, false otherwise
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        return true;
    }
}
