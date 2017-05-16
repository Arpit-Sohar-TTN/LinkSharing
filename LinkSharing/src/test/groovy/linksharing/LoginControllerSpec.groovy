package linksharing

import com.ttn.util.Constants
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
@Mock([User,Resource])
class LoginControllerSpec extends Specification {

  /* def "Checking User Existence and Activeness"() {
       given:
       User user = Mock(User)

       when:
       session.setAttribute("user",user)
       controller.index()

       then:
      session.user == user
       response.redirectedUrl == '/login/index'


       when:
       session.setAttribute("user",null)
       controller.index()

       then:
       session.user == null
       response.redirectedUrl == '/login/index'


   }*/

    def "whenUserIsLoggedOut"() {
        when:
        controller.logout()
        then:
        !session.user
        response.forwardedUrl == '/login/index'
    }
    def "checkIfUserDoesn'tExist"() {
        given:
        String userName = "default"
        String password = "default"

        when:
        controller.loginHandler(userName, password)

        then:
        flash.error == "User not exist in database"
    }

    def "checkIfUserExistsButNotActive"() {
        given:
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "arpitSohar",
                email: "arpit.sohar@gmail.com", password: Constants.PASSWORD, admin: true,
                active: false, photo: "abc".bytes, confirmPassword: Constants.PASSWORD)
        user.save(flush: true)

        when:
        controller.loginHandler(user.userName, user.password)

        then:
        flash.error == 'User is not active'
    }

    def "checkIfUserExistsButIsActive"() {
        given:
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.PASSWORD, isAdmin: false,
                isActive: true, photo: "abc".bytes, confirmPassword: Constants.PASSWORD)
        user.save(flush: true)

        when:
        controller.loginHandler(user.userName, user.password)

        then:
//        session['user'] == user
        response.redirectedUrl == '/user/index'
    }
}
