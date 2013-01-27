package tests;

import static com.google.common.collect.Iterables.get;
import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.util.List;

import models.Chat;
import models.Statut;
import models.Repositories.Entrepots;

import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EntrepotChatsTests {

	String adresse = "[44.833,-0.5670000000000073]";
	String nom = "Lilou";
	String couleur = "rousse";
	String taille = "Chaton";

	@Before
	public void before() {
		BasicConfigurator.configure();
		Entrepots.setEntrepotImage(new FakeEntrepotImage());
		Entrepots.initialise("LostCatDbTest");
		Entrepots.start();
		Entrepots.chats().reset();
	}

	@After
	public void after() {
		Entrepots.chats().reset();
		Entrepots.setEntrepotImage(null);
		Entrepots.flushAndStop();
	}

	@Test
	public void UnChatPeutEtreSauvegardé() {

		Chat cat = Chat.créer(nom, couleur, taille, adresse, Statut.TROUVE);
		cat.setPhoto(new File(""));
		Entrepots.chats().ajouter(cat);
		Entrepots.flushAndStop();
		Entrepots.start();
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
		Chat chat1 = Chat.créer(nom, couleur, taille, adresse, Statut.TROUVE);
		Chat chat2 = Chat.créer(nom, couleur, taille, adresse, Statut.TROUVE);
		Chat chat3 = Chat.créer(nom, couleur, taille, adresse, Statut.TROUVE);
		Entrepots.chats().ajouter(chat1);
		Entrepots.chats().ajouter(chat2);
		Entrepots.chats().ajouter(chat3);
		List<Chat> chats = Entrepots.chats().tous();
		assertThat(chats.size()).isEqualTo(3);
	}

	@Test
	public void UnChatPeutEtreRetrouvéParSonId() {

		Chat cat = Chat.créer(nom, couleur, taille, adresse, Statut.TROUVE);
		String id = cat.getId();
		Entrepots.chats().ajouter(cat);
		Chat cat2 = Entrepots.chats().parId(id);

		assertThat(cat2.getId()).isEqualTo(cat.getId());
		assertThat(cat2.getNom()).isEqualTo(cat.getNom());
		assertThat(cat2.getCouleur()).isEqualTo(cat.getCouleur());
		assertThat(cat2.getTaille()).isEqualTo(cat.getTaille());
		assertThat(cat2.getAdresse()).isEqualTo(cat.getAdresse());
	}

	@Test
	public void UnChatPeutEtreTrouvéParCriteria() {

		Chat chat1 = Chat.créer(nom, couleur, taille, adresse, Statut.TROUVE);
		Entrepots.chats().ajouter(chat1);
		Chat chat2 = Entrepots.chats().parNom(nom);

		assertThat(chat2.getId()).isEqualTo(chat1.getId());
		assertThat(chat2.getNom()).isEqualTo(chat1.getNom());
		assertThat(chat2.getCouleur()).isEqualTo(chat1.getCouleur());
		assertThat(chat2.getTaille()).isEqualTo(chat1.getTaille());
		assertThat(chat2.getAdresse()).isEqualTo(chat1.getAdresse());
		assertThat(chat2.getStatut()).isEqualTo(Statut.TROUVE);
	}
}
