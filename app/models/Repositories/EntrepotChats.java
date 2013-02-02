package models.Repositories;

import java.util.List;

import models.Chat;

import org.mongolink.MongoSession;
import org.mongolink.domain.criteria.Criteria;
import org.mongolink.domain.criteria.RestrictionEquals;

import com.google.common.collect.Iterables;

public class EntrepotChats {

	EntrepotChats() {
	}

	private MongoSession getSession() {
		return SessionManager.getInstance().getMongoSession();

	}

	public Chat parNom(String name) {
		@SuppressWarnings("unchecked")
		Criteria<Chat> criteria = getSession().createCriteria(Chat.class);
		criteria.add(new RestrictionEquals("nom", name));
		criteria.limit(1);

		List<Chat> list = criteria.list();
		return Iterables.get(list, 0);
	}

	public Chat parId(String id) {
		return getSession().get(id, Chat.class);
	}

	public List<Chat> tous() {
		return getSession().getAll(Chat.class);
	}

	public void ajouter(Chat cat) {
		getSession().save(cat);
	}

	public void reset() {
		getSession().getDb().getCollection("chat").drop();
	}
}