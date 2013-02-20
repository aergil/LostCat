package org.lostcat.models.repositories;

import org.lostcat.models.repositories.SessionManager;

public class FakeSessionManager extends SessionManager {

	public FakeSessionManager() {
		super("mongodb://:@127.0.0.1:27017/LostCatDbTest");
	}
}
