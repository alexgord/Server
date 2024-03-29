/*********************************RoomList CLASS INFO******************************************

This class stores a ArrayList of Rooms, as well as storing the current Room, and includes 
functions for detecting whether a room exists, getting and setting the currRoom, adding a 
message to a room, and generating the XML used for displaying message in a room from a 
certain time.  

*********************************************************************************************/

import java.util.ArrayList;


public class RoomList {
	private ArrayList<Room> rooms;
	private String currRoom;
	public RoomList() {
		rooms = new ArrayList<Room>();
	}

	public boolean RoomExists(String s) {

		boolean r = false;
		if (rooms.size() > 0) {
			for (int i = 0; i < rooms.size(); i++) {
				if (rooms.get(i).getName().equals(s)) {
					r = true;
					break;
				}
			}
		}
		return r;
	}

	public String getCurrRoom() {
		return currRoom;
	}

	public void setCurrRoom(String currRoom) {
		this.currRoom = currRoom;
	}

	public void AddMessageToCurrentRoom(Message m) {
		if (RoomExists(currRoom)) {
			for (int i = 0; i < rooms.size(); i++) {
				if (rooms.get(i).getName().equals(getCurrRoom())) {
					rooms.get(i).AddMessage(m);
					break;
				}
			}
		}
		else {
			Room newRoom = new Room(currRoom);
			newRoom.AddMessage(m);
			rooms.add(newRoom);
		}
	}

	public String DisplayCurrentRoom() {
		String r = "";
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getName().equals(getCurrRoom())) {
				r = rooms.get(i).toString();
				break;
			}
		}
		return r;
	}
	
	public String getXMLForCurrentRoom(long since) {
		String r = "<?xml version=\"1.0\"?><message-list></message-list>";
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getName().equals(getCurrRoom())) {
				r = rooms.get(i).toXMLString(since);
				break;
			}
		}
		return r;
	}
}
