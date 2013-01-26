package models.Repositories;

public class Entrepots {

	private static EntrepotImages entrepotImages;
	private static EntrepotChats entrepotChats;

	public static void initialise(String name) {
		EntrepotSessionManager.Initialize(name);
	}

	public static void start() {
		EntrepotSessionManager.start();
	}

	public static void flushAndStop() {
		EntrepotSessionManager.flushAndStop();
	}

	public static EntrepotChats chats() {
		if (entrepotChats == null)
			entrepotChats = new EntrepotChats();

		return entrepotChats;
	}

	public static EntrepotImages images() {
		if (entrepotImages == null)
			entrepotImages = new EntrepotImages();

		return entrepotImages;
	}

	public static void setEntrepotImage(EntrepotImages entrepot) {
		entrepotImages = entrepot;
	}
}
