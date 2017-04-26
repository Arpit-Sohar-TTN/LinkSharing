package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Subscription)
class SubscriptionSpec extends Specification {
//static User user1 = new User()
User user1
static User user2 = new User()
static Topic topic1 = new Topic()
static Topic topic2 = new Topic()


    def setup() {
         user1 = new User()

    }

    def cleanup() {
    }


    void "Testing Subscription Domain"() {


        setup:
        Subscription subscription = new Subscription(user: user,topic: topic,seriousness: seriousness,dateCreated: dateCreated)


        boolean result = subscription.validate()

        expect:
        result == validate



        where:
        user        |   topic       |       dateCreated |       seriousness     | validate
        user1       | topic1        |   new Date()      |   Seriousness.CASUAL  | true
        user1       | topic1        |   new Date()      |   Seriousness.CASUAL  | true

    }
}
