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
//    static responseFormat = ["json"]
//  static namespace = "test1"
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
		} else {
			redirect(controller: 'login', action: 'index')
		}
	}

	def inbox(SearchCO searchCO) {

		User user = session.getAttribute('user')
		render user.getUnReadResources(searchCO)
	}

	def changeIsRead(Long id) {

		/*
			According to Ques
		if( ReadingItem.executeUpdate('Update ReadingItem set isRead=:read where id=:i',[read:isRead,i:id]))
			render "Success"
		else
			render "Failure"*/
		//According to requirement in project
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
//                    render "success for ${topic} whose visibility is ${topic.visibility}"
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

	def download() {
//        byte[] orderPDF = ... // create the bytes from some source
		byte[] bytes = new File("/home/mayank/Desktop/test.json").bytes
		response.setHeader("Content-disposition", "attachment; filename= abc.json")
		response.contentLength = bytes.length
		response.outputStream << bytes
	}

	def editProfile() {
		User user = session.getAttribute('user')

		UserVO userVO = userService.getUserVO(user)
		render view: 'editProfile', model: [userVO: userVO]
	}

	/* def updateProfile(UserCO userCO) {
		 Long id = session.user.id
		 User user = User.get(id)
		 user.firstName = userCO.firstName
		 user.lastName = userCO.lastName
		 user.userName = userCO.userName
		 def file = request.getFile('image')
		 user.image = file.getBytes()
		 *//*user.save(flush:true)

		 String FILE_PATH = "${Constants.IMAGES_PATH}/${file}"
		 File file2 = new File(FILE_PATH)
		 file.transferTo(file2)*//*
		 if (user.save(flush: true, failOnError: true)) {
			 flash.message = "${user.userName} values updated"
			 redirect(controller: 'user', action: 'editProfile')
		 } else {
			 flash.error = "Failed to update the values"
			 redirect(controller: 'user', action: 'editProfile')
		 }
	 }*/

	def updateProfile(UserCO co) {
		def msg = flash.message ?: ""
/*        User user = User.findByUsername(session.user.username)*/
		User user = User.findByUserName(session.user.userName)
		if (user) {
			if (co.firstName)
				user.firstName = co.firstName
			if (co.lastName)
				user.lastName = co.lastName
			if (co.userName)
/*                user.username = co.username*/
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
// msg = flash.message
			}
		} else {
			flash.error = "User Not Found"
// msg = flash.message
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
	/* def viewImage = {

		*//* def photo = Photo.get( params.id )*//**//*
        User user = session.user
        def photo = user.image
        byte[] image = photo.file
        response.outputStream << image*//*
        //def product = Product.findByProductName(params.productName)
        def user = User.findByUserName(params.userName)
        byte[] imageInByte = user.image
        response.contentType = 'image/png' // or the appropriate image content type
        response.outputStream << imageInByte
        response.outputStream.flush()

    }*/

	/*def image(Long id) {
		User user = User.get(id)
		byte[] photo

		if (user.image) {
			photo = user.image
		}
		response.contentType = 'image/png'
		OutputStream out = response.getOutputStream()
		out.write(photo)
		out.flush()
		out.close()
	}*/

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
	/* def adminPanel(SearchCO co) {
		 if(!co.offset)
			 co.offset=0

		 User user = User.findByUserName(session.user)
		 if (user.isAdmin) {
			 List<User> userList = User.createCriteria().list(sort:co.sort,order:co.order,offset:co.offset,max:5){eq("admin",false)}
			 render(view: 'admin', model: [userList: userList])
		 } else {
			 redirect(controller: "user", action: "dashboard")
		 }
	 }*/
}
