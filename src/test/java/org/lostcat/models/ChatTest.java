package org.lostcat.models;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lostcat.models.repositories.Entrepots;
import org.lostcat.models.repositories.FakeEntrepotImage;
import org.lostcat.models.repositories.FakeSessionManager;
import org.lostcat.models.repositories.SessionManager;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

public class ChatTest {
	@Before
	public void before() {
		Entrepots.setEntrepotImages(new FakeEntrepotImage());
		SessionManager.setInstance(new FakeSessionManager());
		SessionManager.getInstance().start();
	}

	@After
	public void after() {
		Entrepots.setEntrepotImages(null);
		Entrepots.chats().reset();
		SessionManager.getInstance().flushAndStop();
	}

	@Test
	public void ChatPeutEtreConvertiEnJSON() {
		Chat chat = Chat.créer("name1", Statut.PERDU);
		chat.setCouleur("color1");
		String id = chat.getId().toString();
		Gson gson = new Gson();

		String chatJson = gson.toJson(chat);

		assertThat(chatJson).contains("\"id\":\"" + id + "\"");
		assertThat(chatJson).contains("\"couleur\":\"color1\"");
		assertThat(chatJson).contains("\"nom\":\"name1\"");
		assertThat(chatJson).contains("\"statut\":\"PERDU\"");
	}

	@Test
	public void ChatsPeutEtreConvertiEnJSON() {

		Chat chat1 = Chat.créer("name1", Statut.TROUVE);
		Chat chat2 = Chat.créer("name2", Statut.PERDU);
		String id1 = chat1.getId().toString();
		String id2 = chat2.getId().toString();

		List<Chat> chats = Lists.newArrayList(chat1, chat2);
		Gson gson = new Gson();
		String cats = gson.toJson(chats);

		assertThat(cats).contains("\"id\":\"" + id1 + "\"");
		assertThat(cats).contains("\"id\":\"" + id2 + "\"");
	}
}
