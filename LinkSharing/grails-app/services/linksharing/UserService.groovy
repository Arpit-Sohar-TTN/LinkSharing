package linksharing

import com.ttn.vo.ResourceVO
import com.ttn.vo.TopicVO
import com.ttn.vo.UserVO
import grails.transaction.Transactional
import sun.security.tools.keytool.Resources_es

@Transactional
class UserService {

    def serviceMethod() {

    }
    List<TopicVO> getSubscriptions(User user) {
        List<Subscription> subscriptionList = Subscription.findAllByUser(user)
        List<Topic> subscribedTopicList = subscriptionList.topic
        List<TopicVO> topicVOList =[]
        subscribedTopicList.each {topic->
            topicVOList.add(new TopicVO(id: topic.id,name: topic,visibility: topic.visibility,
                    count: Resource.countByTopic(topic),noOfSubscribedUsers: Subscription.countByTopic(topic),createdBy: topic.createdBy))
        }
        return topicVOList
    }
    List<ResourceVO> getResources(User user) {
        List<ResourceVO> resourceVOList = []
        List<Topic> subscribedTopics = Subscription.findAllByUser(user).topic
        println subscribedTopics
        subscribedTopics.each {
                            topic->
            List<Resource> resourceList = Resource.findAllByTopic(topic)
                                println resourceList
            resourceList.each {resource->
                if (resource instanceof LinkResource)
                    resourceVOList.add(new ResourceVO(id: resource.id,description: resource.description,topic:topic,createdBy: resource.createdBy,path: resource.url))
                else if (resource instanceof DocumentResource)
                    resourceVOList.add(new ResourceVO(id: resource.id,description: resource.description,topic:topic,createdBy: resource.createdBy,path: resource.filePath))

            }
        }
        resourceVOList
    }

    UserVO getUserVO(User user) {
        UserVO userVO = new UserVO()
        userVO.noOfSubscriptions = Subscription.countByUser(user)
        userVO.noOfCreatedTopics = Topic.countByCreatedBy(user)
        userVO.topicSubscriptionList = getSubscriptions(user)
        userVO.resourceVOList = getResources(user)
        return userVO
    }
    List<TopicVO> checkLoggedInUserSubscription(User user,List<TopicVO> topicVOList) {
        println topicVOList
        topicVOList.each {
            println it
            Topic topic = Topic.findByTopicName(it.getAt(1))
            println topic
            if (Subscription.findByUserAndTopic(user,topic)) {
                it.setIsLoggedInUserSubscribed(true)
            }
            else{
                it.setIsLoggedInUserSubscribed(true)

            }
        }
        return topicVOList
    }
}
