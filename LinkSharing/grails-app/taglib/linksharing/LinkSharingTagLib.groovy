package linksharing

import com.ttn.vo.TopicVO

class LinkSharingTagLib {
    static defaultEncodeAs = "raw"
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "ls"
    def isRead = { attrs, body ->
        User user = session.user
        if (user) {
            Long id = attrs.resource.id
            Resource resource = Resource.findById(id)
            if (resource) {
                ReadingItem readingItem = ReadingItem.findByUserAndResource(user,resource)
                if (readingItem) {
                    println readingItem
                if (readingItem.isRead == false) {
                    out << "<a href='${createLink(controller: 'readingItem', action: 'toggleIsRead', id: resource.id)}'>Mark as Read</a> "
                }
            }
            }
        }
    }
    def trendingTopics = { attrs,body ->
        User user = session.user
        List<TopicVO> topicVOList = Topic.getTrendingTopics()
        out << render(template:'/user/trendingTopic', model:[topic:topicVOList])

    }

    def isSubscribe = {attrs,body ->
        User user = session.user
        Long id = attrs.id
        Topic topic = Topic.get(id)
        Subscription subscription = Subscription.findByUserAndTopic(user,topic)
        if (subscription) {
            out << "<a href='${createLink(controller: 'user', action: 'toggleIsSubscribe', id: id)}'>Unsubscribe</a> "
        }
        else {
            out << "<a href='${createLink(controller: 'user', action: 'toggleIsSubscribe', id: id)}'>Subscribe</a> "
        }

    }



}
