package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DocumentResource)
class DocumentResourceSpec extends Specification {
    def "validateDocumentResource"() {
        given:
        DocumentResource documentResource = new DocumentResource(filePath: filePath, description: description, createdBy: user, topic: topic)

        when:
        Boolean result = documentResource.validate()
        then:
        result == valid

        where:
        description   | filePath         | user       | topic       | valid
        "description" | "/home/arpit" | new User() | new Topic() | true
        ""            | "/home/arpit" | new User() | new Topic() | false
        null          | "/home/arpit" | new User() | new Topic() | false
        "description" | ""            | new User() | new Topic() | false
        "description" | null          | new User() | new Topic() | false
        "description" | "/home/arpit" | null       | new Topic() | false
        "description" | "/home/arpit" | new User() | null        | false
    }

    def "tostringCheck"() {
        setup:
        DocumentResource documentResource = new DocumentResource(filePath: filePath)

        when:
        result == documentResource.toString()

        then:
        noExceptionThrown()

        where:
        filePath          | result
        "/some/file/path" | "/some/file/path"
        ""                | ""
        null              | null
    }

   /* def "tostringCheck"() {
        setup:
        DocumentResource documentResource = new DocumentResource(FILE_PATH: FILE_PATH)

        when:
        result == documentResource.toString()

        then:
        noExceptionThrown()

        where:
        FILE_PATH          | result
        "/some/file/path" | "/some/file/path"
        ""                | ""
        null              | null
    }*/
}
