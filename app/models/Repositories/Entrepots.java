package models.Repositories;

public class Entrepots {

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

	public static void setEntrepotImages(EntrepotImages entrepot) {
		entrepotImages = entrepot;
	}

	private static EntrepotImages entrepotImages;
	private static EntrepotChats entrepotChats;
}
