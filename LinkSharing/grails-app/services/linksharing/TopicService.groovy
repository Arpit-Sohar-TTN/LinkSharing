package linksharing

class TopicService {

	def topicSave(Topic topic, User user, Seriousness seriousness) {

/*TransactionAspectSupport.currentTransactionStatus().rollbackOnly
        if (user.userName == 'agni')*/

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
}
