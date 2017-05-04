package linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ReadingItem)
@Mock([User,Topic])
class ReadingItemSpec extends Specification {

    def "Testing User uniqueness for per resource"() {
        given:
        User user  = new User()
        Topic topic = new Topic()
        Resource resource = new DocumentResource(filePath: "home/arpit", description: "Description",
                createdBy: user, topic: topic)
        ReadingItem readingItem = new ReadingItem(user: user,isRead: true,resource: resource,dateCreated: new Date(),
                                    lastUpdated: new Date())

        when:
        readingItem.save()

        then:
        ReadingItem.count==1

        when:
        ReadingItem readingItem2 = new ReadingItem(user: user,isRead: true,resource: resource,dateCreated: new Date(),
                lastUpdated: new Date())
        readingItem2.save()

        then:
        ReadingItem.count==1
        readingItem2.errors.errorCount==1

    }
}
