package linksharing

import com.ttn.co.ResourceSearchCO
import com.ttn.co.TopicCO
import com.ttn.util.Constants
import grails.converters.JSON

class TopicController {

	def topicService


			/*def index(String to) {
		TopicCO topicCO = new TopicCO(topicName: to, visibility: Visibility.PRIVATE)
		save(topicCO, "SERIOUS")
		render "Topic index"
	}

	def showResource(ResourceSearchCO resourceSearchCO) {
		Topic topic = Topic.findById(resourceSearchCO.topicId)
		if (topic) {
			if (topic.visibility == Visibility.PUBLIC)
				render "success for ${topic} whose visibility is ${topic.visibility}"
			else {
				User user = User.findByUserName(session.getAttribute('user').userName)
				Subscription subscription = Subscription.findByUserAndTopic(user, topic)
				if (subscription) {
					render "success for ${topic} whose visibility is ${topic.visibility}"
				} else {
					flash.error = "User not subscribed to required private topic"
					redirect(controller: 'login', action: 'index')

				}

			}

		} else {
			flash.error = "Topic not exist in DB"
			redirect(controller: 'login', action: 'index')
		}



	}*/
	def save(String topicName, String visibility) {
		Visibility visibility1 = Visibility.stringtoEnum(visibility)

		User user = session.getAttribute('user')
		if (user) {
			Topic topic = new Topic(topicName: topicName, createdBy: user, visibility: visibility1)
			String seriousness = Constants.DEFAULT_SERIOUSNESS
			Seriousness seriousness1 = Seriousness.convertIntoEnum(seriousness)
			if (topicService.topicSave(topic, user, seriousness1)) {
				flash.message = "Topic Saved Successfully"
				forward(controller: 'user', action: 'index')
			} else {
				flash.error = "Topic Not saved"
				forward(controller: 'user', action: 'index')
			}
		}
	}
	def showTopic(Long id) {
		Topic topic = Topic.findById(id)
		User user = session.getAttribute('user')
		if (user) {
			render view: 'topicShow', model: [topic: topic]
		} else {
			flash.message = "Please Signup First"
			redirect(controller: 'login', action: 'index')
		}
	}

	def delete(int id) {
		Map response = [:]
		User user = session.getAttribute('user')
		response = topicService.deleteTopic(id,user)
		render response as JSON
	}


	def trendingTopics() {
		render Topic.getTrendingTopics()
	}

	def subscribedUsers(int topicId) {
		render Topic.getSubscribedUsers(Topic.findById(topicId))

	}
}
