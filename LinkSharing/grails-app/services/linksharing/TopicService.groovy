package linksharing

class TopicService {

	def topicSave(Topic topic, User user, Seriousness seriousness) {
		if (topic.save(flush: true)) {
			Subscription subscription = new Subscription(topic: topic, user: user, seriousness: seriousness)
			if (subscription.save(flush: true, failOnError: true)) {
				log.info("Created user ${user} also subscribed to its created topic ${topic}")
			}
			else {
				log.error("Subscription not saved due to some errors : ${subscription.errors.allErrors}")
			}

			return true

		}
	}

	Map deleteTopic(Long id, User user) {
		Map response = [:]
		Topic topic = Topic?.load(id)
		if ((topic.createdBy.userName == user.userName)) {
			topic.delete(flush: true)
			response.success = "${topic.topicName} deleted successfully"
		} else {
			response.success = "You are not authenticate to delete this topic"
		}
		return response
	}
}
