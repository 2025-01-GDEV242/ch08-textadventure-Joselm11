import java.util.ArrayList;
import java.util.List;

/**
 * This represents the player in the game
 * Player class handles items and room movement
 */

public class Player {
    private Room currentRoom;
    private Room previousRoom;
    private List<String> carriedItems;
    
    /**
     * Creates a new Player in the specified starting room.
     * 
     * @param startRoom the room where the player starts
     */
    public Player(Room startRoom) {
        this.currentRoom = startRoom;
        this.previousRoom = null;
        this.carriedItems = new ArrayList<>();
    }

    /**
     * Gets the current room the player is in.
     * 
     * @return the current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Moves the player to a new room and updates the previous room.
     * 
     * @param newRoom the room to move to
     */
    public void moveTo(Room newRoom) {
        previousRoom = currentRoom;
        currentRoom = newRoom;
    }

     /**
     * Moves the player back to the previous room.
     * If no previous room exists, the player cannot go back.
     */
    public void goBack() {
        if (previousRoom != null) {
            Room temp = currentRoom;
            currentRoom = previousRoom;
            previousRoom = temp;
            System.out.println(currentRoom.getLongDescription());
        } else {
            System.out.println("You can't go back!");
        }
    }

    /**
     * Picks up an item from the current room and adds it to the player's carried items.
     * 
     * @param item the item to pick up
     * @param room the room from which the item is picked up
     */
    public void takeItem(String item, Room room) {
        if (room.hasItem(item)) {
            carriedItems.add(item);
            room.removeItem(item);
            System.out.println("You picked up: " + item);
        } else {
            System.out.println("Item is not here.");
        }
    }

}
