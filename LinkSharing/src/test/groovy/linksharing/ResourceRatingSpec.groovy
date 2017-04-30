package linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ResourceRating)
@Mock(User)
class ResourceRatingSpec extends Specification {

    def "Checking uniqueness of Resource"() {
        given:
        User user = new User()
        Topic topic = new Topic()
        Resource resource = new DocumentResource(filePath: "home/arpit", description: "Description",
                createdBy: user, topic: topic)
        ResourceRating resourceRating = new ResourceRating(resource: resource, user: user, score: 2, dateCreated: new Date(),
                dateUpdated: new Date())

        when:
        resourceRating.save()

        then:
        ResourceRating.count == 1

        when:
        ResourceRating resourceRating2 = new ResourceRating(resource: resource, user: user, score: 2, dateCreated: new Date(),
                dateUpdated: new Date())
        resourceRating2.save()

        then:
        ResourceRating.count == 1
        resourceRating2.errors.errorCount == 1

    }

    def "Validating Score "() {
        setup:
        ResourceRating resourceRating = new ResourceRating(resource: resource, user: user, score: score,
                dateCreated: dateCreated, dateUpdated: dateUpdated)


        when:
        boolean result = resourceRating.validate()

        then:
        result == valid

        where:

        resource               | user       | score | dateCreated | dateUpdated | valid
        new DocumentResource() | new User() | 4     | new Date()  | new Date()  | true
        new DocumentResource() | new User() | 10     | new Date()  | new Date()  | false


    }


}
