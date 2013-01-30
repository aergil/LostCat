package tests;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.util.List;

import models.Chat;
import models.Statut;
import models.Repositories.Entrepots;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

public class ChatTests {

	@Before
	public void before() {
		Entrepots.setEntrepotImage(new FakeEntrepotImage());
		Entrepots.initialise("LostCatDbTests");
		Entrepots.start();
	}

	@After
	public void after() {
		Entrepots.setEntrepotImage(null);
		Entrepots.flushAndStop();
	}

	@Test
	public void UnChatPeutAvoirUneImage() {
		String nom = "Lilou";
		Chat chat = Chat.créer(nom, Statut.TROUVE);

		chat.setPhoto(new File("/home/zorg/workspace/LostCat/test/tests/ressources/logo-test.jpg"));

		assertThat(chat.getId() + ".png").isEqualTo(chat.getImageFileName());
		assertThat(chat.getId() + "-little.png").isEqualTo(chat.getIconFileName());
	}

	@Test
	public void ChatPeutEtreConvertiEnJSON() {
		Chat chat = Chat.créer("name1", Statut.PERDU);
		chat.setCouleur("color1");
		String id = chat.getId();
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
		String id1 = chat1.getId();
		String id2 = chat2.getId();

		List<Chat> chats = Lists.newArrayList(chat1, chat2);
		Gson gson = new Gson();
		String cats = gson.toJson(chats);

		assertThat(cats).contains("\"id\":\"" + id1 + "\"");
		assertThat(cats).contains("\"id\":\"" + id2 + "\"");
	}
}
