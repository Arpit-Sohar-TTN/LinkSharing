package linksharing

import com.ttn.co.TopicCO
import com.ttn.co.UserCO
import com.ttn.util.Constants
import com.ttn.vo.ResourceVO
import com.ttn.vo.TopicVO
import com.ttn.vo.UserVO
import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
class UserService {
    def assetResourceLocator

    List<TopicVO> getSubscriptions(User user) {
        List<Subscription> subscriptionList = Subscription.findAllByUser(user)
        List<Topic> subscribedTopicList = subscriptionList*.topic
        List<TopicVO> topicVOList = []
        subscribedTopicList.each { topic ->
            topicVOList.add(new TopicVO(id: topic.id, name: topic, visibility: topic.visibility,
                    count: Resource.countByTopic(topic), noOfSubscribedUsers: Subscription.countByTopic(topic), createdBy: topic.createdBy))
        }
        return topicVOList
    }

    List<TopicVO> getCreatedTopics(User user) {
        List<Topic> topicsList = Topic.findAllByCreatedBy(user)
        List<TopicVO> topicVOList = []
        topicsList.each { topic ->
            topicVOList.add(new TopicVO(id: topic.id, name: topic, visibility: topic.visibility,
                    count: Resource.countByTopic(topic), noOfSubscribedUsers: Subscription.countByTopic(topic), createdBy: topic.createdBy))

        }
        return topicVOList
    }

    List<ResourceVO> getResources(User user) {
        List<ResourceVO> resourceVOList = []
        List<Topic> subscribedTopics = Subscription.findAllByUser(user).topic

        subscribedTopics.each { topic ->
            List<Resource> resourceList = Resource.findAllByTopic(topic)

            resourceList.each { resource ->
                ReadingItem readingItem = ReadingItem.findByUserAndResource(user, resource)
                Boolean isRead
                if (readingItem) {
                    isRead = readingItem.isRead
                }
                if (resource instanceof LinkResource)
                    resourceVOList.add(new ResourceVO(id: resource.id, description: resource.description, topic: topic,
                            createdBy: resource.createdBy, path: resource.url, isRead: isRead))
                else if (resource instanceof DocumentResource)
                    resourceVOList.add(new ResourceVO(id: resource.id, description: resource.description, topic: topic,
                            createdBy: resource.createdBy, path: resource.filePath, isRead: isRead))


            }
        }
        resourceVOList

    }

    UserVO getUserVO(User user) {
        UserVO userVO = new UserVO()
        userVO.userName = user.userName
        userVO.firstName = user.firstName
        userVO.lastName = user.lastName
        userVO.noOfSubscriptions = Subscription.countByUser(user)
        userVO.noOfCreatedTopics = Topic.countByCreatedBy(user)
        userVO.topicSubscriptionList = getSubscriptions(user)
        userVO.createdTopics = getCreatedTopics(user)
        userVO.id = user.id
//        userVO.resourceVOList = getFilteredList(user,params.max,params.offset)
        userVO.resourceVOList = getResources(user)
        return userVO
    }

    List<TopicVO> checkLoggedInUserSubscription(User user, List<TopicVO> topicVOList) {

        topicVOList.each {

            Topic topic = Topic.findByTopicName(it.getAt(1))

            if (Subscription.findByUserAndTopic(user, topic)) {
                it.setIsLoggedInUserSubscribed(true)
            } else {
                it.setIsLoggedInUserSubscribed(true)

            }
        }
        return topicVOList
    }

    List getFilteredList(User user, int max, int offset) {
        max = Math.min(max ?: 25, 100)
        offset = (offset && offset > 0) ?: 0
        List resourceVoList = getResources(user) //Loads the complete list
        int total = resourceVoList.size()
        int upperLimit = findUpperIndex(offset, max, total)
        List filteredNames = resourceVoList.getAt(offset..upperLimit)
        return filteredNames
    }

    static int findUpperIndex(int offset, int max, int total) {
        max = offset + max - 1
        if (max >= total) {
            max -= max - total + 1
        }
        return max
    }

    def upload(MultipartFile file, String rootDir) {
        if (file.empty) {
            flash.message = 'file cannot be empty'
            render("error")
            return
        }
        File fileDest = new File(rootDir, "/imageAsset/${file.originalFilename}")
        file.transferTo(fileDest)

        return "${rootDir}/imageAsset/${file.originalFilename}"

    }

    List<TopicVO> getTrendingTopics() {
        return Topic.getTrendingTopics()
    }

    List getSubscribedTopic(User user) {
        return User.getSubscribedTopic(user)
    }
//	boolean updateProfile(UserCO co) {
//		User user = User.findByUserName(session.user.userName)
//		if (user) {
//			if (co.firstName)
//				user.firstName = co.firstName
//			if (co.lastName)
//				user.lastName = co.lastName
//			if (co.userName)
//				user.userName = co.userName
//			user.confirmPassword = user.password
//			def file = request.getFile('image')
//			if (file) {
//				user.photo = file.getBytes()
//			}
//			if (user.save(failOnError: true, flush: true)) {
//				flash.message = message(code: "profile.updated")
//				msg = flash.message
//				session.user = user
//			} else {
////				flash.error = "Error updating profile"
//				return false
//			}
//		} else {
////			flash.error = "User Not Found"
//			return false
//		}
//		return true
//	}


    Map toggleIsSubscribe(User user, Long id) {
        Map response = [:]
        Topic topic = Topic.get(id)
        Subscription subscription = Subscription.findByUserAndTopic(user, topic)
        if (subscription == null) {
            Subscription subscription1 = new Subscription(topic: topic, user: user, seriousness: Constants.DEFAULT_SERIOUSNESS)
            subscription1.validate()
            subscription1.save(flush: true, failOnError: true)
//			redirect(controller: 'user', action: 'index')
            response.success = "Subscribed topic"
        } else {
            subscription.delete(flush: true)
            response.success = "Unsubscribed topic "

        }
        return response
    }

    Map editTopic(User user, Topic topic, String seriousness) {
        Map response = [:]
        if (topic.save(flush: true)) {
            Subscription subscription = Subscription.findByUserAndTopic(user, topic)
            if (subscription) {
                subscription.seriousness = Seriousness.convertIntoEnum(seriousness)
                if (subscription.save(flush: true)) {
                    response.success = "Topic ${topic} edited successfully"
                } else {
                    response.success = "Topic edit seriousness is default"
                }
            }
        } else {
            response.success = "Topic ${topic} cannot be edit"
        }
        return response
    }

    List<Resource> getResultResources(String q) {
        List<Resource> resourceList = Resource.all
        List<Resource> resultResources = []
        resourceList.each { resource ->
            if (resource.description.contains(q) && resource.topic.visibility == Visibility.PUBLIC) {
                resultResources.add(resource)
            }
        }
        return resultResources
    }

    List<Topic> getResultTopics(String q) {
        List<Topic> topicList = Topic.all
        List<Resource> resultTopics = []
        topicList.each { topic ->
            if (topic.topicName.contains(q) && topic.visibility == Visibility.PUBLIC) {
                resultTopics.add(topic)
            }
        }
        return resultTopics
    }

    byte[] saveImage(Long userId) {
        User user = User.load(userId)
        byte[] photo
        if (user?.photo == null) {
            photo = assetResourceLocator.findAssetForURI('iconProfile.png').byteArray
        } else {
            photo = user.photo
        }
        return photo
    }

    Map editTopicProp(Long topicId, User user, String seriousness) {
        Map response = [:]
        Topic topic = Topic.get(topicId)
        Subscription subscription = Subscription.findByUserAndTopic(user, topic)

        if (subscription) {
            subscription.seriousness = Seriousness.convertIntoEnum(seriousness)
            if (subscription.save(flush: true, failOnError: true)) {
                response.success = "${topic} seriousness changed to ${seriousness}"
            } else {
                response.success = "Seriousness cannot be altered"
            }
        } else {
            response.success = "Subscription not exist"
        }
        return response
    }
}
