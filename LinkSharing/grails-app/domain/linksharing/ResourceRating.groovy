package linksharing

class ResourceRating {

	Resource resource
	User user
	int score
	Date dateCreated
	Date lastUpdated

	static belongsTo = [resource: Resource]

	static constraints = {
		score min: 1, max: 5
		resource(unique: ['user'])
	}

	@Override
	 String toString() {
		return "ResourceRating{" +
				"id=" + id +
				", resource=" + resource +
				", user=" + user +
				", score=" + score +
				", dateCreated=" + dateCreated +
				", lastUpdated=" + lastUpdated +
				'}'
	}
}
