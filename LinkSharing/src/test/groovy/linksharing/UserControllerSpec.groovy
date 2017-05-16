package linksharing

import com.ttn.util.Constants
import grails.test.mixin.*
import spock.lang.*

@TestFor(UserController)
@Mock(User)
class UserControllerSpec extends Specification {
    def "testUserIndex"() {
        given: "user is set in session"
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "arpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.PASSWORD, isAdmin: false,
                isActive: true, photo: "abc".bytes, confirmPassword: Constants.PASSWORD)
        user.save(flush: true)
        session["user"] = user

        when:
        controller.index()

        then:
        response.text == "User dashboard User{userName='arpitSohar'}"
    }
}
