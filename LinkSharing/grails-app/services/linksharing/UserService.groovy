package linksharing

import com.ttn.vo.ResourceVO
import com.ttn.vo.TopicVO
import com.ttn.vo.UserVO
import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
class UserService {
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

}
