package linksharing

import com.ttn.util.Constants

class BootStrap {

	def init = { servletContext ->

		/*  Properties properties = new Properties()
		  File propertiesFile = new File("CONSTANTS.properties")
		  propertiesFile.withInputStream {
			  properties.load(it)
		  }
  */

		createUsers()
		createTopics()
		createResources()
		subscribeTopics()
		createReadingItems()
		createResourceRatings()

	}
	def destroy = {
	}

	def createUsers() {
		Integer counter = User.count()
		if (!counter) {
			User admin = new User(id: 0, firstName: "Admin", lastName: "TTN", userName: "adminTTN",
					email: "admin@ttn.com", isActive: true, password: "admin", confirmPassword: "admin", isAdmin: true)
			User user = new User(id: 1, firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
					email: "arpit.sohar@tothenew.com", isAdmin: false, isActive: true, password: Constants.PASSWORD, confirmPassword: Constants.PASSWORD)
			admin.save(flush: true, failOnError: true)
			user.save(flush: true, failOnError: true)
			log.info("Admin & First User saved")
			User user1 = new User(id: 2,
					firstName: "Mayank",
					lastName: "Agnihotri",
					userName: "agni",
					email: "agni4912@gmail.com",
					isAdmin: false,
					isActive: true,
					password: Constants.PASSWORD_AGNI,
					confirmPassword: Constants.PASSWORD_AGNI
			)
			admin.save(flush: true, failOnError: true)
			user1.save(flush: true, failOnError: true)
		} else {
			log.info("Error invoked")
		}


	}

	def createTopics() {
		List users = User.all

		users.each { user ->
			int topicCountOfUser = Topic.countByCreatedBy(user)
			if (topicCountOfUser <= 5) {

				(5 - topicCountOfUser).times {
					Topic topic = new Topic(topicName: "Topic ${it}", createdBy: user, visibility: Visibility.PUBLIC)
					if (topic.save(flush: true, failOnError: true)) {
						user.addToTopics(topic)
						log.info("Topic ${topic} saved and add to user ${user}")
						Subscription subscription = new Subscription(topic: topic, user: user, seriousness: Seriousness.VERY_SERIOUS)
						if (subscription.save(flush: true, failOnError: true)) {
							log.info("Created user ${user} also subscribed to its created topic ${topic}")
						}
						else {
							log.error("Subscription not saved due to some errors : ${subscription.errors.allErrors}")
						}
					} else {
						log.error("error on save of topic ${topic}")
					}
				}
			} else {
				log.info("5 topics saved for user ${user}")
			}


		}
	}


	def createResources() {
		String descriptionContent = """This is a panel consist of recent shares which will be used thoughout the applic This is a panel consist 
                                            of recent shares which will be used thoughout the applic This is a panel consist of recent shares 
                                            which will be used thoughout the application """
		List topics = Topic.all
		topics.each { topic ->
			Integer countResources = Resource.countByTopic(topic)
			if (countResources < 1) {
				2.times {
					Resource linkResource = new LinkResource(topic: topic, createdBy: topic.createdBy, description: "${descriptionContent}", url: "https://www.google.co.in")
					ReadingItem readingLinkResource = new ReadingItem(user: topic.createdBy, isRead: true, resource: linkResource)
					Resource documentResource = new DocumentResource(topic: topic, createdBy: topic.createdBy, description: "${descriptionContent}", filePath: "/home/mayank/")
					ReadingItem readingDocumentResource = new ReadingItem(user: topic.createdBy, isRead: true, resource: documentResource)
					if (linkResource.save(flush: true, failOnError: true)) {
						readingLinkResource.save(flush: true)
						log.info("Link Resource save")
					} else {
						log.error("Error in link Resource ${linkResource.errors.allErrors}")
					}
					if (documentResource.save(flush: true, failOnError: true)) {
						readingDocumentResource.save(flush: true)
						log.info("Document Resource save")
					} else {
						log.error("Error in document Resource ${documentResource.errors.allErrors}")
					}
				}
			}
		}
	}


	def subscribeTopics() {
		List topics = Topic.all
		List users = User.all
		topics.each { topic ->

			users.each { user ->
				if (!(Subscription.findByUserAndTopic(user, topic))) {
					Subscription subscription = new Subscription(topic: topic, user: user, seriousness: Seriousness.VERY_SERIOUS)
					if (subscription.save(flush: true, failOnError: true)) {
						log.info("${user} subscribed ${topic}")
					}
					else {
						log.error("${user} not subscribed due to errors ${subscription.errors}")
					}
				}
			}
		}
	}

	/* def createReadingItems() {

		 def topics = Topic.all

		 topics.each {
			 topic->
			 def subscriptions = Subscription.findAllByTopic(topic)

				 subscriptions.each {
					 subscription->
					 def userSubscribed = subscription.user
					 def resources = Resource.findByTopic(topic)

						 resources.each { resource->
							 if (!(ReadingItem.findByUserAndResource(userSubscribed,resource))) {
								 ReadingItem readingItem = new ReadingItem(user: userSubscribed,resource: resource,isRead: true)
								 if (readingItem.save(flush:true,failOnError:true))
									 log.info("${resource} read by ${userSubscribed} for topic ${topic}")
								 else
									 log.error("error occured during reading item ${resource} by ${userSubscribed}")
							 }
							 else{
								 log.info("${resource} is already read by ${userSubscribed}")
							 }
						 }

				 }

		 }

	 }
 */

	def createReadingItems() {
		boolean toggleIsRead = true
		def resources = Resource.all
		def users = User.all
		resources.each { resource ->
			users.each { user ->
				if (!(ReadingItem.findByUserAndResource(user, resource)) && (user != resource.createdBy)) {
					ReadingItem readingItem = new ReadingItem(user: user, resource: resource, isRead: toggleIsRead)
					if (toggleIsRead) {
						readingItem.isRead = false
						toggleIsRead = false
					} else {
						readingItem.isRead = true
						toggleIsRead = true
					}
					if (readingItem.save(flush: true, failOnError: true)) {
						log.info("${resource} read by ${user}")
					} else {
						log.error("error occured during reading item ${resource}")
					}
				} else {
					log.info("${resource} is already read by ${user}")
				}

			}
		}

	}

	def createResourceRatings() {
		def readingItems = ReadingItem.all
		readingItems.each {
			readingItem ->
				if (readingItem.isRead) {
					ResourceRating resourceRating = new ResourceRating(user: readingItem.user,
							resource: readingItem.resource, score: 5)
					if (resourceRating.save(flush: true, failOnError: true)) {
						log.info("${readingItem.user} rate ${readingItem.resource}")

					} else {
						log.info("${resource} is already read by ${userSubscribed}")
					}
				}
		}
	}
}
