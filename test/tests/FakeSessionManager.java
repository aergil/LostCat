package tests;

import models.Repositories.SessionManager;

public class FakeSessionManager extends SessionManager {

	public FakeSessionManager() {
		super("mongodb://:@127.0.0.1:27017/LostCatDbTest");
	}
}
