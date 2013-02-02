package models.Repositories;

import java.util.Arrays;
import java.util.List;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.domain.mapper.ContextBuilder;

import com.google.common.collect.Iterables;
import com.mongodb.MongoURI;

public class SessionManager {

	protected SessionManager(String dbConnectionstring) {
		MongoURI mongoUri = new MongoURI(dbConnectionstring);
		List<String> strings = Arrays.asList(mongoUri.getHosts().get(0).split(":"));
		String host = Iterables.getFirst(strings, "");
		Integer port = Integer.parseInt(Iterables.getLast(strings, ""));
		String password = new String(mongoUri.getPassword());
		String username = mongoUri.getUsername();
		String dbName = mongoUri.getDatabase();

		Settings settings = Settings.defaultInstance().withHost(host).withPort(port).withDbName(dbName).withAuthentication(username, password);
		ContextBuilder builder = new ContextBuilder("mapping");
		sessionManager = MongoSessionManager.create(builder, settings);
	}

	public static SessionManager getInstance() {
		if (instance == null)
			instance = new SessionManager(System.getenv("MONGOLAB_URI"));

		return instance;
	}

	public static void setInstance(SessionManager sessionManager) {
		instance = sessionManager;
	}

	public MongoSession getMongoSession() {
		return mongoSession;
	}

	public void start() {
		mongoSession = sessionManager.createSession();
		mongoSession.start();
	}

	public void flushAndStop() {
		mongoSession.flush();
		mongoSession.stop();
	}

	protected MongoSession mongoSession;
	protected final MongoSessionManager sessionManager;
	protected static SessionManager instance;

}
