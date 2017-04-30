package linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.IgnoreRest
import spock.lang.Specification
import spock.lang.Unroll
import java.util.Date

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
// checking for email format
        "prashant" | "gupta"  | "prashantttn.com" | "prashant" | "123456"    | true     | []    | true    | new Date()  | new Date()  | false

    }

    def "validating uniqueness of username"() {
        setup:
//        String email = "arpit.sohar@tothenew.com"
        String username = "Arpit"
        User user = new User(firstName: "fname",
                lastName: "lname",
                email: "1234@gmail.com",
                password: "password",
                userName: username,
                isActive: true,
                isAdmin: false,
                dateCreated: new Date(),
                dateUpdated: new Date())
        when:
             user.save()
        then:
            User.count()==1

        when:
        User newUser = new User(
                firstName: "fname",
                lastName: "lname",
                email: "123@gmail.com",
                password: "password",
                userName: username,
                dateCreated: new Date(),
                dateUpdated: new Date())
        newUser.save()

        then:
        User.count() == 1
        newUser.errors.allErrors.size() == 1
        newUser.errors.getFieldErrorCount('userName') == 1
    }
    def "validating uniqueness of email"() {
        setup:
        String email = "arpit.sohar@tothenew.com"
//        String username = "Arpit"
        User user = new User(firstName: "fname",
                lastName: "lname",
                email: email,
                password: "password",
                userName: "Arpit",
                isActive: true,
                isAdmin: false,
                dateCreated: new Date(),
                dateUpdated: new Date())
        when:
        user.save(failOnError:true)
        then:
        User.count()==1

        when:
        User newUser = new User(
                firstName: "fname",
                lastName: "lname",
                email: email,
                password: "password",
                userName: "Arpit Sohar",
                dateCreated: new Date(),
                dateUpdated: new Date())
        newUser.save()

        then:
        User.count() == 1
        newUser.errors.allErrors.size() == 1
        newUser.errors.getFieldErrorCount('email') == 1
    }

}
