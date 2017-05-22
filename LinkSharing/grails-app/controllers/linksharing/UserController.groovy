package linksharing

import com.ttn.co.SearchCO
import com.ttn.co.TopicCO
import com.ttn.co.UserCO
import com.ttn.util.Constants
import com.ttn.vo.ResourceVO
import com.ttn.vo.TopicVO
import com.ttn.vo.UserVO
import grails.transaction.Transactional

@Transactional(readOnly = true)

class UserController {
	def userService
	def assetResourceLocator
	def pagination() {
		params.max = 5
		params.offset = 0
		User user = session.getAttribute('user')
		UserVO userVO = userService.getUserVO(user, params)
		[resourceVOs: userVO.resourceVOList]
	}

	def index() {
		params.max = 5
		params.offset = 0
		User user = session.getAttribute('user')
		if (user) {
			List<TopicVO> trendingTopics = Topic.getTrendingTopics()
			UserVO userVO = userService.getUserVO(user)


			List subscribedTopic = User.getSubscribedTopic(user)
			render('view': 'index', model: [user: user, trendingTopics: trendingTopics, userVO: userVO, subscribedTopic: subscribedTopic])
		}
	}

	def inbox(SearchCO searchCO) {

		User user = session.getAttribute('user')
		render user.getUnReadResources(searchCO)
	}

	def changeIsRead(Long id) {
		User user = session.getAttribute('user')
		Resource resource = Resource.get(id)
		ReadingItem readingItem = ReadingItem.findByUserAndResource(user, resource)
		if (readingItem) {
			if (readingItem.isRead) {
				readingItem.isRead = false
				readingItem.save(flush: true, failOnError: true)
				render "${resource} is unread "
			} else {
				readingItem.isRead = true
				readingItem.save(flush: true, failOnError: true)
				render "${resource} is read"

			}
		} else {
			render "failure"
		}
	}


	def topicShow(Long id) {

		Topic topic = Topic.findById(id)
		if (session.getAttribute('user')) {
			if (topic) {
				if (topic.visibility == Visibility.PUBLIC)
					redirect(controller: 'topic', action: 'showTopic')
				else {
					User user = User.findById(id)
					Subscription subscription = Subscription.findByUserAndTopic(user, topic)
					if (subscription) {
						render view: 'topic/index'
					} else {
						flash.error = "User not subscribed to required private topic"
						redirect(controller: 'login', action: 'index')

					}

				}

			} else {
				flash.error = "Topic not exist in DB"
				redirect(controller: 'login', action: 'index')
			}
		} else {
			redirect(controller: 'login', action: 'index')
		}



	}

	def upload() {
		def f = params.myFile
		render f.inputStream.text
	}

	/*def download() {
//        byte[] orderPDF = ... // create the bytes from some source
		byte[] bytes = new File("/home/mayank/Desktop/test.json").bytes
		response.setHeader("Content-disposition", "attachment; filename= abc.json")
		response.contentLength = bytes.length
		response.outputStream << bytes
	}*/

	def editProfile() {
		User user = session.getAttribute('user')

		UserVO userVO = userService.getUserVO(user)
		render view: 'editProfile', model: [userVO: userVO]
	}



	def updateProfile(UserCO co) {
		def msg = flash.message ?: ""
		User user = User.findByUserName(session.user.userName)
		if (user) {
			if (co.firstName)
				user.firstName = co.firstName
			if (co.lastName)
				user.lastName = co.lastName
			if (co.userName)

				user.userName = co.userName
			user.confirmPassword = user.password
			def file = request.getFile('image')
			if (file) {
				user.photo = file.getBytes()
			}
			if (user.save(failOnError: true, flush: true)) {
				flash.message = message(code: "profile.updated")
				msg = flash.message
				session.user = user
			} else {
				flash.error = "Error updating profile"
			}
		} else {
			flash.error = "User Not Found"
		}
		redirect(controller: "user", action: "editProfile", params: [message: msg])
	}


	def updatePassword(String password, String confirmPassword) {
		Long id = session.user.id
		User user = User.get(id)
		user.password = password
		user.confirmPassword = confirmPassword
		if (user.password == user.confirmPassword) {

			if (user.save(flush: true)) {
				flash.message = "Password Updated successfully"
				redirect(controller: 'user', action: 'editProfile')
			} else {
				flash.singleError = "Failed to update Password"
				redirect(controller: 'user', action: 'editProfile')
			}

		} else {
			flash.singleError = "Failed to update Password"
			redirect(controller: 'user', action: 'editProfile')
		}
	}


	def showProfile() {
		String userName = params.userName
		User user = User.findByUserName(userName)
		UserVO userVO = userService.getUserVO(user)
		render view: 'showProfile', model: [userVO: userVO]

	}

	def toggleIsSubscribe(Long id) {
		Topic topic = Topic.get(id)
		User user = session.user
		Subscription subscription = Subscription.findByUserAndTopic(user, topic)
		if (subscription == null) {
			Subscription subscription1 = new Subscription(topic: topic, user: user, seriousness: Constants.DEFAULT_SERIOUSNESS)
			subscription1.save(flush: true, floatOnErro: true)
			redirect(controller: 'user', action: 'index')
		} else {
			subscription.delete(flush: true)
			redirect(controller: 'user', action: 'index')

		}
	}

	def editTopic(TopicCO topicCO, String seriousness) {
		User user = session.user

		topicCO.createdBy = user
		Topic topic = Topic.findByCreatedByAndId(user, topicCO.id)
		bindData(topic, topicCO)

		if (topic.save(flush: true, floatOnError: true)) {
			Subscription subscription = Subscription.findByUserAndTopic(user, topic)
			if (subscription) {
				subscription.seriousness = Seriousness.convertIntoEnum(seriousness)
				if (subscription.save(flush: true)) {
					flash.message = "Topic ${topic} edited successfully"
					redirect(controller: 'user', action: 'index')
				} else {
					flash.error = "Topic edit seriousness is default"
					redirect(controller: 'user', action: 'index')
				}
			}

		} else {
			flash.error = "Topic ${topic} cannot be edit"
			redirect(controller: 'user', action: 'index')
		}
	}

	def search(String q) {
		List<ResourceVO> topPosts = Resource.topPost()
		List<Resource> resourceList = Resource.all
		List<Topic> topicList = Topic.all
		List<Resource> resultResources = []
		List<Topic> resultTopics = []
		resourceList.each { resource ->
			if (resource.description.contains(q) && resource.topic.visibility == Visibility.PUBLIC) {
				resultResources.add(resource)
			}
		}
		topicList.each { topic ->
			if (topic.topicName.contains(q) && topic.visibility == Visibility.PUBLIC) {
				resultTopics.add(topic)
			}
		}
		render view: 'search', model: [topPosts: topPosts, resources: resultResources, topics: resultTopics, q: q]
	}


	def image(Long userId) {
		User user = User.load(userId)
		byte[] photo
		if (user?.photo == null) {
			photo = assetResourceLocator.findAssetForURI('iconProfile.png').byteArray
		} else {
			photo = user.photo
		}
		OutputStream out = response.getOutputStream()
		out.write(photo)
		out.flush()
		out.close()
	}

}
