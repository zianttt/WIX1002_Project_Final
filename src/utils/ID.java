package utils;

// Define ID for game objects to compare
public enum ID {
	
	// Minigames
	MiniPlayer(),
	BasicEnemy(),
	FastEnemy(),
	SmartEnemy(),
	
	// Main
	Player(),
	Citizen(),
	Dragon(),
	EventGen(),
	Chest(),
	Trail(),
	Tower(),
	Wall(),
	ShopDoor(),
	WallDoor(),
	MiniGameDoor(),
	StatusDoor(),
	InfoBoard(),
	Block(); // for other objects that cannot be passed thru
	
}
