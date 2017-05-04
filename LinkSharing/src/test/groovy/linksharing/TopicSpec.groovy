package linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll
import java.util.Date

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Topic)
@Mock(User)
class TopicSpec extends Specification {


    @Unroll("Topic Constraints ----- #sno")
    void  "testTopicValidations"() {

        given:
        Topic topic = new Topic(topicName: topicName, createdBy: createdBy, visibility: visibility,
                                dateCreated: dateCreated,
                                lastUpdated: lastUpdated)

        when:
        Boolean result = topic.validate()

        then:
        result == valid

        where:
        sno | topicName | createdBy  | visibility          |dateCreated    | lastUpdated  | valid
        1   | " Grails" | new User() | Visibility.PRIVATE  |new Date()     | new Date()   | true
        2   | " "       | new User() | Visibility.PRIVATE  |new Date()     | new Date()   | false
        3   | null      | new User() | Visibility.PRIVATE  |new Date()     | new Date()   | false
        4   | "grails"  | null       | Visibility.PUBLIC   |new Date()     | new Date()   | false
        5   | "grails"  | new User() | null                |new Date()     | new Date()   | false
    }

    def "topicUserUniqueness"() {

        given:
        String topicName = "grails"
        User creator = new User()
        Visibility visibility = Visibility.PRIVATE
        Topic topic = new Topic(topicName: topicName, createdBy: creator,dateCreated: new Date(),
                lastUpdated: new Date(), visibility: visibility)

        when:
        topic.save()

        then:
        println topic.errors
        Topic.count() == 1


        when:
        Topic newTopic = new Topic(topicName: topicName, createdBy: creator,dateCreated: new Date(),
                                    lastUpdated: new Date(), visibility: visibility)
        newTopic.save()

        then:
        Topic.count() == 1
        newTopic.errors.allErrors.size() == 1
        println newTopic.errors
        newTopic.errors.getFieldErrorCount('createdBy') == 1
    }

    def "Correct Topic" () {
        given:
        String topicName = "grails"
        User creator = new User()
        Visibility visibility = Visibility.PRIVATE
        Topic topic = new Topic(topicName: topicName, createdBy: creator,dateCreated: new Date(),
                lastUpdated: new Date(), visibility: visibility)


        when:
        creator.save()
        topic.save()

        then:
        Topic.count == 1

    }
    def "toStringCheck"() {

        given:
        User user = new User(userName: "Arpit Sohar")
        Topic topic = new Topic(topicName: name1, createdBy: user)

        when:
        String topicName = topic.toString()

        then:
        topicName == result

        where:
        name1    | result
        "Grails" | "Topic{topicName='Grails'}"
    }
}
