package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Topic)
class TopicSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }
//
//    void "test something"() {
//        expect:"fix me"
//            true == false
//    }
    @Unroll
    void "Testing Topic"() {
        setup:
        Topic topic = new Topic(topicName: topicName,createdBy: createdBy,dateCreated: dateCreated,lastUpdated: lastUpdated,
                        visibility: visibility)
        boolean result = topic.validate()

        expect:
        result == validate



        where:
        topicName   |   createdBy   |   dateCreated     |       lastUpdated     |       visibility          |   validate
        "Grails"    |  new User()   |   new Date()      |   new Date()          |       Visibility.PUBLIC   |   true
        ""          |   new User()  |   new Date()      |   new Date()          |       null                |   false

    }


}
