package models;

import java.util.UUID;

public class ObjectId {

	public static String getInc() {
		return UUID.randomUUID().toString();
	}

}
