package linksharing

class Subscription {
	Topic topic
	User user
	Seriousness seriousness
	Date dateCreated
	Date lastUpdated

	static belongsTo = [user: User, topic: Topic]

	static constraints = {
		user nullable: false
		topic nullable: false
		seriousness nullable: false
		//user(unique: ['topic'])
	}
	static mapping = {
		seriousness defaultValue: Seriousness.SERIOUS


	}


	@Override
	 String toString() {
		return "Subscription{" +
				"id=" + id +
				", topic=" + topic +
				", user=" + user +
				", seriousness=" + seriousness +
				", dateCreated=" + dateCreated +
				", lastUpdated=" + lastUpdated +
				'}'
	}
}

enum Seriousness {
	SERIOUS,
	VERY_SERIOUS,
	CASUAL

	static Seriousness convertIntoEnum(String value) {
		if (value == 'SERIOUS')
			return Seriousness.SERIOUS
		else if (value == 'VERY_SERIOUS')
			return Seriousness.VERY_SERIOUS
		else if (value == 'CASUAL')
			return Seriousness.CASUAL
	}
}
