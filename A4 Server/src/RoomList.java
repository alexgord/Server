import java.util.ArrayList;


public class RoomList
{
	private ArrayList<Room> rooms;
	private String currRoom;
	public RoomList()
	{
		rooms = new ArrayList<Room>();
	}

	public boolean RoomExists(String s)
	{

		boolean r = false;
		if (rooms.size() > 0)
		{
			for (int i = 0; i < rooms.size(); i++)
			{
				if (rooms.get(i).getName().equals(s))
				{
					r = true;
					break;
				}
			}
		}
		return r;
	}

	public String getCurrRoom()
	{
		return currRoom;
	}

	public void setCurrRoom(String currRoom)
	{
		this.currRoom = currRoom;
	}

	public void AddMessageToCurrentRoom(Message m)
	{
		if (RoomExists(currRoom))
		{
			for (int i = 0; i < rooms.size(); i++)
			{
				if (rooms.get(i).getName().equals(getCurrRoom()))
				{
					rooms.get(i).AddMessage(m);
					break;
				}
			}
		}
		else
		{
			Room newRoom = new Room(currRoom);
			newRoom.AddMessage(m);
			rooms.add(newRoom);
		}
	}

	public String DisplayCurrentRoom()
	{
		String r = "";
		for (int i = 0; i < rooms.size(); i++)
		{
			if (rooms.get(i).getName().equals(getCurrRoom()))
			{
				r = rooms.get(i).toString();
				break;
			}
		}
		return r;
	}
}
