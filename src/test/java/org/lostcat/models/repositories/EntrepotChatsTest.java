package org.lostcat.models.repositories;

import static com.google.common.collect.Iterables.get;
import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lostcat.models.Chat;
import org.lostcat.models.Statut;

public class EntrepotChatsTest {

	String adresse = "[44.833,-0.5670000000000073]";
	String nom = "Lilou";
	String couleur = "rousse";
	String age = "Chaton";

	@Before
	public void before() {
		BasicConfigurator.configure();
		Entrepots.setEntrepotImages(new FakeEntrepotImage());
		SessionManager.setInstance(new FakeSessionManager());
		SessionManager.getInstance().start();
		Entrepots.chats().reset();
	}

	@After
	public void after() {
		Entrepots.chats().reset();
		SessionManager.getInstance().flushAndStop();
		Entrepots.setEntrepotImages(null);
	}

	@Test
	public void UnChatPeutEtreSauvegardé() {

		Chat chat = Chat.créer(nom, Statut.TROUVE);
		chat.setCouleur(couleur);
		chat.setAge(age);
		chat.setAdresse(adresse);
		chat.setIconFileName("IconFileName");
		chat.setImageFileName("ImageFileName");
		Entrepots.chats().ajouter(chat);
		SessionManager.getInstance().flushAndStop();
		SessionManager.getInstance().start();
		List<Chat> chats = Entrepots.chats().tous();

		assertThat(chats.size()).isEqualTo(1);
		Chat chatSauvegardé = get(chats, 0);
		assertThat(chatSauvegardé.getNom()).isEqualTo(chat.getNom());
		assertThat(chatSauvegardé.getCouleur()).isEqualTo(chat.getCouleur());
		assertThat(chatSauvegardé.getAge()).isEqualTo(chat.getAge());
		assertThat(chatSauvegardé.getAdresse()).isEqualTo(chat.getAdresse());
		assertThat(chatSauvegardé.getStatut()).isEqualTo(chat.getStatut());
		assertThat(chatSauvegardé.getIconFileName()).isEqualTo(chat.getIconFileName());
		assertThat(chatSauvegardé.getImageFileName()).isEqualTo(chat.getImageFileName());

	}

	@Test
	public void FindAll() {
		Chat chat1 = Chat.créer(nom, Statut.TROUVE);
		Chat chat2 = Chat.créer(nom, Statut.TROUVE);
		Chat chat3 = Chat.créer(nom, Statut.TROUVE);
		Entrepots.chats().ajouter(chat1);
		Entrepots.chats().ajouter(chat2);
		Entrepots.chats().ajouter(chat3);

		List<Chat> chats = Entrepots.chats().tous();

		assertThat(chats.size()).isEqualTo(3);
	}

	@Test
	public void UnChatPeutEtreRetrouvéParSonId() {
		Chat chat = Chat.créer(nom, Statut.TROUVE);
		String id = chat.getId().toString();
		Entrepots.chats().ajouter(chat);
		Chat chatSauvegardé = Entrepots.chats().parId(id);

		assertThat(chatSauvegardé.getId()).isEqualTo(chat.getId());
		assertThat(chatSauvegardé.getNom()).isEqualTo(chat.getNom());
	}

	@Test
	public void UnChatPeutEtreTrouvéParCriteria() {
		Chat chat = Chat.créer(nom, Statut.TROUVE);
		Entrepots.chats().ajouter(chat);
		Chat chatSauvegardé = Entrepots.chats().parNom(nom);

		assertThat(chatSauvegardé.getId()).isEqualTo(chat.getId());
		assertThat(chatSauvegardé.getNom()).isEqualTo(chat.getNom());
	}
}
