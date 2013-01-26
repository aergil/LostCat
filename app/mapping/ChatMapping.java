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

		// subclass(new SubclassMap<ChildComment>(ChildComment.class) {
		// @Override
		// protected void map() {
		// property(element().getName());
		// }
		// });
	}
}
