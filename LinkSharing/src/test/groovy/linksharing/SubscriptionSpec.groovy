package linksharing

import com.ttn.util.Constants
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Subscription)
class SubscriptionSpec extends Specification {
//static User user1 = new User()
//static User user2 = new User()
//static Topic topic1 = new Topic()
//static Topic topic2 = new Topic()


    def setup() {
//         user1 = new User()

    }

    def cleanup() {
    }


    void "Testing Subscription Domain for null values"() {

        given:
        Subscription subscription = new Subscription(user: user,topic: topic,seriousness: seriousness,dateCreated: new Date(),
                                     lastUpdated:new Date())

        when:
        boolean  validate = subscription.validate()



        then:
        validate == result



        where:
        topic       | user       | seriousness         | result
        new Topic() | new User() | Seriousness.CASUAL  | true
        null        | new User() | Seriousness.SERIOUS | false
        new Topic() | null       | Seriousness.CASUAL  | false
        new Topic() | new User() | null                | false
    }


    def "Testing User not to subscribe to topic multiple times"() {

        given:
        User user = new User(firstName: "fname", lastName: "lname", email: "1234@gmail.com", password: Constants.PASSWORD,
                userName: "Arpit", isActive: true, isAdmin: false, dateCreated: new Date(), lastUpdated: new Date())
        Topic topic = new Topic(topicName: "grails", createdBy: user,dateCreated: new Date(),
                lastUpdated: new Date(), visibility: Visibility.PRIVATE)
//        user.addToTopics(topic)
        Subscription subscription = new Subscription(user: user,topic: topic,seriousness: Seriousness.CASUAL,
                dateCreated: new Date(),
                lastUpdated:new Date())

        when:
        subscription.save()

        then:
        Subscription.count() ==1

        when:
        Subscription subscription2 = new Subscription(user: user,topic: topic,seriousness: Seriousness.CASUAL,
                dateCreated: new Date(),
                lastUpdated:new Date())
        subscription2.save()

        then:
        Subscription.count == 1






    }

}

