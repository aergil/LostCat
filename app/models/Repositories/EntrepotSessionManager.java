package models.Repositories;

import java.util.Arrays;
import java.util.List;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.domain.mapper.ContextBuilder;

import com.google.common.collect.Iterables;
import com.mongodb.MongoURI;

class EntrepotSessionManager {

	static String dbName;
	private static MongoSession mongoSession;
	private static MongoSessionManager sessionManager;

	protected static MongoSession getMongoSession() {
		return mongoSession;
	}

	protected static void Initialize() {
		MongoURI mongoUri = new MongoURI(System.getenv("MONGOLAB_URI"));
		// MongoURI mongoUri = new
		// MongoURI("mongodb://LostCatDbUser:iiyama@ds049467.mongolab.com:49467/heroku_app11314799");

		List<String> strings = Arrays.asList(mongoUri.getHosts().get(0).split(":"));
		String host = Iterables.getFirst(strings, "");
		Integer port = Integer.parseInt(Iterables.getLast(strings, ""));
		char[] password2 = mongoUri.getPassword();
		String password = "";
		for (char c : password2) {
			password = password.concat(Character.toString(c));
		}
		// String password = password2.toString();
		String username = mongoUri.getUsername();
		if (dbName == null) {
			dbName = mongoUri.getDatabase();
		}

		Settings settings = Settings.defaultInstance().withHost(host).withPort(port).withDbName(dbName).withAuthentication(username, password);
		ContextBuilder builder = new ContextBuilder("mapping");
		sessionManager = MongoSessionManager.create(builder, settings);
	}

	public static void Initialize(String name) {
		dbName = name;
		Initialize();
	}

	public static void start() {
		if (sessionManager == null)
			Initialize();

		mongoSession = sessionManager.createSession();
		mongoSession.start();
	}

	public static void flushAndStop() {
		getMongoSession().flush();
		getMongoSession().stop();
	}
}
