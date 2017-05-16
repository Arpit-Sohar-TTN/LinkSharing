package linksharing

class SubscriptionController {

	def index() {}

	def save(int topicId) {
		User user = User.get(session.getAttribute('user').id)
		Topic topic = Topic.get(topicId)
		if (topic.visibility == Visibility.PUBLIC) {
			Subscription subscription = new Subscription(topic: topic, user: user, seriousness: Seriousness.SERIOUS)
			if (subscription.save(flush: true)) {
				user.addToSubscriptions(subscription)
				log.info("${user} subscribe ${topic}")
				render "${user} subscribe ${topic}"
			} else {
				log.error("${user} can not able to subscribe ${topic} due to ${subscription.errors.allErrors}")
				render("${user} can not able to subscribe ${topic} due to ${subscription.errors.allErrors}")

			}
		} else {
			log.info("${topic} is private topic")
			render("${topic} is private topic")
		}
	}


	def delete(int topicId) {
		User user = User.get(session.getAttribute('user').id)
		Topic topic = Topic.get(topicId)
		Subscription subscription = Subscription.findByUserAndTopic(user, topic)
		if (subscription) {
			subscription.delete(flush: true)
			log.info("${user} no longer subscribed to ${topic}")
			render "${user} no longer subscribed to ${topic}"
		} else {
			log.info("${user} already not subscribed to ${topic}")
			render "${user} already not subscribed to ${topic}"
		}
	}


	def update(int topicId, String seriousness) {
		User user = User.get(session.getAttribute('user').id)
		Topic topic = Topic.get(topicId)
		Subscription subscription = Subscription.findByUserAndTopic(user, topic)
		if (subscription) {
			if (subscription.seriousness == Seriousness.convertIntoEnum(seriousness)) {
				log.info("${user} already subscribed ${topic} by ${seriousness}")
				render("${user} already subscribed ${topic} by ${seriousness}")
			} else {
				subscription.seriousness = Seriousness.convertIntoEnum(seriousness)
				subscription.save(flush: true, failOnError: true)
				render("${user} now subscribed ${topic} by ${seriousness}")

			}
		} else {
			render "${user} not subscribed to ${topic}"
		}
	}
}
