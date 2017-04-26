package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

//    void "test something"() {
//        expect:"fix me"
//            true == false
//    }

    @Unroll
    void "validating user"() {

        setup:
        User user = new User(firstName: firstName, lastName: lastName,
                email: email, userName: userName, password: password,
                isActive: isActive, image: image, isAdmin: isAdmin,
                dateCreated: dateCreated,dateUpdated: dateUpdated)
        boolean result = user.validate()

        expect:

        result == validate

        where:
        firstName  | lastName | email             | userName   | password    | isActive | image | isAdmin | dateCreated | dateUpdated | validate
        "Arpit"    | "Sohar"  | "arpit@ttn.com"   | "arpit"    | "abcdefghi" | true     | []    | true    | new Date()  | new Date()  | true
// checking for email
        "prashant" | "gupta"  | "prashantttn.com" | "prashant" | "123456"    | true     | []    | true    | new Date()  | new Date()  | false

    }


}
