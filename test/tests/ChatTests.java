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
	public void UnChatPeutEtreCréé() {
		String adresse = "[44.833,-0.5670000000000073]";
		String nom = "Lilou";
		String couleur = "rousse";
		String taille = "Chaton";

		Chat chat = Chat.créer(nom, couleur, taille, adresse, Statut.TROUVE);
		chat.setPhoto(new File("/home/zorg/workspace/LostCat/test/tests/ressources/logo-test.jpg"));

		assertThat(nom).isEqualTo(chat.getNom());
		assertThat(couleur).isEqualTo(chat.getCouleur());
		assertThat(taille).isEqualTo(chat.getTaille());
		assertThat(adresse).isEqualTo(chat.getAdresse());
		assertThat(chat.getId() + ".png").isEqualTo(chat.getImageFileName());
		assertThat(chat.getId() + "-little.png").isEqualTo(chat.getIconFileName());
		assertThat(adresse).isEqualTo(chat.getAdresse());
		assertThat(Statut.TROUVE).isEqualTo(chat.getStatut());
	}

	@Test
	public void ChatPeutEtreConvertiEnJSON() {

		Chat chat1 = Chat.créer("name1", "color1", "size1", "address1", Statut.PERDU);
		String id = chat1.getId();
		Gson gson = new Gson();
		String cat1Json = gson.toJson(chat1);

		assertThat(cat1Json).contains("\"id\":\"" + id + "\"");
		assertThat(cat1Json).contains("\"imageFileName\":\"\"");
		assertThat(cat1Json).contains("\"statut\":\"PERDU\"");
		assertThat(cat1Json).contains("\"adresse\":\"address1\"");
		assertThat(cat1Json).contains("\"couleur\":\"color1\"");
		assertThat(cat1Json).contains("\"taille\":\"size1\"");
		assertThat(cat1Json).contains("\"iconFileName\":\"\"");
		assertThat(cat1Json).contains("\"nom\":\"name1\"");
	}

	@Test
	public void ChatsPeutEtreConvertiEnJSON() {

		Chat chat1 = Chat.créer("name1", "color1", "size1", "address1", Statut.TROUVE);
		Chat chat2 = Chat.créer("name2", "color2", "size2", "address2", Statut.PERDU);
		String id1 = chat1.getId();
		String id2 = chat2.getId();

		List<Chat> chats = Lists.newArrayList(chat1, chat2);
		Gson gson = new Gson();
		String cats = gson.toJson(chats);

		assertThat(cats).contains("\"id\":\"" + id1 + "\"");
		assertThat(cats).contains("\"id\":\"" + id2 + "\"");
	}
}
