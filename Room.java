import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the Movie theater application. 
 * Movie theater is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room. it also has a list of items
 * 
 * @author Jose Moreno
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits; 
    private ArrayList<String> items; // Multiple items in a room

    /**
     * Create a room with a description. Initially, it has no exits or items.
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>(); // Initialize empty item list
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Add an item to the room.
     * @param item The item to add.
     */
    public void addItem(String item)
    {
        items.add(item);
    }

    /**
     * Remove an item from the room.
     * @param item The item to remove.
     * @return true if the item was removed, false if it was not found.
     */
    public boolean removeItem(String item)
    {
        return items.remove(item);
    }

    /**
     * @return The short description of the room.
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room, including items and exits.
     * @return A long description of this room.
     */
    public String getLongDescription()
    {
        String descriptionText = "You are " + description + ".\n" + getExitString();
        
        if (!items.isEmpty()) {
            descriptionText += "\nItems in the room: " + String.join(", ", items);
        }
        
        return descriptionText;
    }
    
     /**
     * Checks if the room contains a specific item.
     * 
     * @param item the item to check
     * @return true if the room contains the item, false otherwise
     */
    public boolean hasItem(String item) {
    return items.contains(item);
    }


    /**
     * Return a string describing the room's exits.
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        for(String exit : exits.keySet()) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * @return The list of items in this room.
     */
    public ArrayList<String> getItems()
    {
        return items;
    }
}
