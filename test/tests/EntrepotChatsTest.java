package tests;

import static com.google.common.collect.Iterables.get;
import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.util.List;

import models.Chat;
import models.Statut;
import models.Repositories.Entrepots;
import models.Repositories.SessionManager;

import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class EntrepotChatsTest {

	String adresse = "[44.833,-0.5670000000000073]";
	String nom = "Lilou";
	String couleur = "rousse";
	String taille = "Chaton";

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

		Chat cat = Chat.créer(nom, Statut.TROUVE);
		cat.setCouleur(couleur);
		cat.setTaille(taille);
		cat.setAdresse(adresse);
		cat.setPhoto(new File(""));
		Entrepots.chats().ajouter(cat);
		SessionManager.getInstance().flushAndStop();
		SessionManager.getInstance().start();
		List<Chat> cats = Entrepots.chats().tous();

		assertThat(cats.size()).isEqualTo(1);
		assertThat(get(cats, 0).getNom()).isEqualTo(cat.getNom());
		assertThat(get(cats, 0).getCouleur()).isEqualTo(cat.getCouleur());
		assertThat(get(cats, 0).getTaille()).isEqualTo(cat.getTaille());
		assertThat(get(cats, 0).getAdresse()).isEqualTo(cat.getAdresse());
		assertThat(get(cats, 0).getStatut()).isEqualTo(cat.getStatut());
		assertThat(get(cats, 0).getIconFileName()).isEqualTo(cat.getIconFileName());
		assertThat(get(cats, 0).getImageFileName()).isEqualTo(cat.getImageFileName());

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

		Chat cat = Chat.créer(nom, Statut.TROUVE);
		String id = cat.getId().toString();
		Entrepots.chats().ajouter(cat);
		Chat cat2 = Entrepots.chats().parId(id);

		assertThat(cat2.getId()).isEqualTo(cat.getId());
		assertThat(cat2.getNom()).isEqualTo(cat.getNom());
	}

	@Test
	public void UnChatPeutEtreTrouvéParCriteria() {

		Chat chat1 = Chat.créer(nom, Statut.TROUVE);
		Entrepots.chats().ajouter(chat1);
		Chat chat2 = Entrepots.chats().parNom(nom);

		assertThat(chat2.getId()).isEqualTo(chat1.getId());
		assertThat(chat2.getNom()).isEqualTo(chat1.getNom());
	}
}