package linksharing

import com.ttn.vo.TopicVO

class Topic {
    String topicName;
    User createdBy
    Date dateCreated
    Date lastUpdated
    Visibility visibility


    static belongsTo = [createdBy:User]
    static hasMany = [subscriptions:Subscription , resources:Resource]

    static constraints = {
        topicName blank: false
        createdBy(unique: ['topicName'])
    }
    static mapping = {
        sort "topicName"

    }

    @Override
    public String toString() {
        return  topicName
    }


  /*  def afterInsert = {

        Topic.withNewSession {


            Subscription subscription = new Subscription(
                    user: this.createdBy,
                    topic: this,
                    seriousness: Seriousness.VERY_SERIOUS,
                    dateCreated: new Date(),
                    dateUpdated: new Date()
            )
            this.addToSubscriptions(subscription)
            if (subscription.save(flush: true)) {
                log.info "${subscription}-> ${this.createdBy} subscribed for ${this}"

            } else {
                log.error "Subscription does not occured--- ${subscription.errors.allErrors}"
            }
        }
    }*/


   /* static List<TopicVO> getTrendingTopics(User user) {

        List trendingTopics = Topic.createCriteria().list() {

            projections {
                groupProperty("id")
                property("topicName")
                property("visibility")
                property("createdBy")
                resources {
                    count "id", "count"
                }
            }

            order("count", "desc")
            order("topicName", "desc")
            maxResults 5 // This is just for pagination
            firstResult 0
        }
        List<TopicVO> topicVOList = []
        trendingTopics.each {
            Topic topic = Topic.findByTopicName(it.getAt(1))
            Subscription subscription = Subscription.findByUserAndTopic(user,topic)
            if (subscription)
                topicVOList.add(new TopicVO(id: it.getAt(0), name: it.getAt(1), visibility: it.getAt(2), createdBy: it.getAt(3), count: it.getAt(4),isLoggedInUserSubscribed: true))
            else
                topicVOList.add(new TopicVO(id: it.getAt(0), name: it.getAt(1), visibility: it.getAt(2), createdBy: it.getAt(3), count: it.getAt(4),isLoggedInUserSubscribed: true))
        }

    }*/


    static List<TopicVO> getTrendingTopics() {
        List<TopicVO> trendingTopics = []
        Resource.createCriteria().list {
            createAlias('topic', 't')
            projections {
                groupProperty("t.id")
                property("t.topicName")
                property("t.visibility")
                property("t.createdBy")
                count("t.id", "topicCount")
            }
            order("topicCount", "desc")
            order("t.topicName", "desc")
            maxResults(5)
        }.each {
            trendingTopics.add(new TopicVO(id: it[0], name: it[1], visibility: it[2],
                    createdBy: it[3], count: it[4]))
        }
        return trendingTopics
    }

    static List<User> getSubscribedUsers(Topic topic) {
        List<Subscription> subscriptions = Subscription.findAllByTopic(topic)
        List<User> subscribedUsers = []
        subscriptions.each {subscription->
            subscribedUsers.add(subscription.user)
        }
        return subscribedUsers
    }

}






enum Visibility{
    PUBLIC , PRIVATE


    static Visibility stringtoEnum(String visibility) {
        if (visibility.equals('PUBLIC'))
            return Visibility.PUBLIC
        else
            return Visibility.PRIVATE
    }
}