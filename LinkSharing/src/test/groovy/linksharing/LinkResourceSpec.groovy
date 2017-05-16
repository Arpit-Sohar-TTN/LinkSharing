package linksharing

import com.ttn.util.Constants
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LinkResource)
class LinkResourceSpec extends Specification {
    User user = Mock(User)
    Topic topic = Mock(Topic)

    def setup() {
    }

    def cleanup() {
    }

    def "validateLinkResource"() {

        given:
         user = new User(firstName: "fname", lastName: "lname", email: "1234@gmail.com", password: Constants.PASSWORD,
                userName: "Arpit", isActive: true, isAdmin: false, dateCreated: new Date(), lastUpdated: new Date())
         topic = new Topic(topicName: "grails", createdBy: user,dateCreated: new Date(),
                lastUpdated: new Date(), visibility: Visibility.PRIVATE)
        LinkResource linkResource = new LinkResource(url: url, description: description, createdBy: user, topic: topic)

        when:
        Boolean result = linkResource.validate()

        then:
        result == valid
        println linkResource.errors

        where:
        description   | url                        | valid
        "description" | 'http://www.tothenew.com/' | true
        " "           | 'http://www.tothenew.com/' | false
        null          | 'http://www.tothenew.com/' | false
        "description" | '://www.tothenew.com/'     | false
        "description" | ' '                        | false
        "description" | null                       | false
    }




    def "tostringCheck"() {

        setup:
        LinkResource linkResource = new LinkResource(url: url)

        when:
        result == linkResource.toString()

        then:
        noExceptionThrown()

        where:
        url                               | result
        "http://www.testLinkResource.com" | "LinkResource{url='http://www.testLinkResource.com'}"
        ""                                | "LinkResource{url=''}"
        null                              | "LinkResource{url=null}"
    }




}
