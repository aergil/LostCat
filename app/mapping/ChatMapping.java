package mapping;

import models.Chat;

import org.mongolink.domain.mapper.AggregateMap;

public class ChatMapping extends AggregateMap<Chat> {

	public ChatMapping() {
		super(Chat.class);
	}

	@Override
	protected void map() {
		id(element().getId()).natural();
		property(element().getNom());
		property(element().getAdresse());
		property(element().getCouleur());
		property(element().getIconFileName());
		property(element().getStatut());
		property(element().getTaille());
		property(element().getImageFileName());
		property(element().getLatLng());
		property(element().getTatouage());
		property(element().getPuce());
		property(element().getRace());
		property(element().getSexe());
		property(element().getAge());
		property(element().getDateDisparition());
		property(element().getProprietaireNomPrenom());
		property(element().getProprietaireEmail());
		property(element().getProprietaireTelephone());
		property(element().getProprietaireAdresse());

		// subclass(new SubclassMap<ChildComment>(ChildComment.class) {
		// @Override
		// protected void map() {
		// property(element().getName());
		// }
		// });
	}
}
