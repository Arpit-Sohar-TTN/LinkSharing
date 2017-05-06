package linksharing

import com.ttn.co.TopicCO
import com.ttn.util.Constants
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TopicController)
@Mock([User,Topic,Subscription])
class TopicControllerSpec extends Specification {

   def "testing save action in topic controller "() {
        given:
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
                active: true, photo: "abc".bytes, confirmPassword: Constants.password)
        user.save(flush:true)
        session.setAttribute('user',user)
        String topicName = "Groovy"
        TopicCO topicCO = new TopicCO(topicName: topicName,createdBy: user,visibility: Visibility.PUBLIC)


       when:
       controller.save(topicCO,"CASUAL")

       then:
       session.user == user
       response.text=="Success ! Topic{topicName='Groovy'} has been saved by User{userName='ArpitSohar'}"

   }
    def "testing save action if topic is already saved "() {
        given:
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
                active: true, photo: "abc".bytes, confirmPassword: Constants.password)
        user.save(flush:true)
        session.setAttribute('user',user)
        String topicName = "Groovy"
        TopicCO topicCO = new TopicCO(topicName: topicName,createdBy: user,visibility: Visibility.PUBLIC)


      /*  when:

        then:
        session.user == user
        response.text=="Success ! Topic{topicName='Groovy'} has been saved by User{userName='ArpitSohar'}"*/

        when:
        controller.save(topicCO,"CASUAL")
        controller.save(topicCO,"CASUAL")

        then:
        session.user == user
        response.text== "Success ! Topic{topicName='Groovy'} has been saved by User{userName='ArpitSohar'}Failed to save topic"

    }




    def "Testing deletion of topic"() {
        given:
        int id=1
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
                active: true, photo: "abc".bytes, confirmPassword: Constants.password)
        session.setAttribute('user',user)
        Topic topic  =  new Topic(id: 1, topicName: 'G1',createdBy: user,visibility: Visibility.PUBLIC)
        topic.save(flush:true)
        user.save(flush:true)

        when:
        topic.createdBy.userName=user.userName

        then:
        Topic.count == 1

        when:
        controller.delete(id)

        then:
        Topic.count==0
    }

    def "Testing show for public Topic "() {
        given:
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
                active: true, photo: "abc".bytes, confirmPassword: Constants.password)
        user.save(flush:true)
        session.setAttribute('user',user)
        String topicName = "Groovy"
        Topic topic = new Topic(id: 1,createdBy:user,topicName: topicName,visibility: Visibility.PUBLIC)
        topic.save(flush:true)

        when:
        controller.show(1)

        then:
        response.text == "success for ${topic} whose visibility is ${topic.visibility}"

    }
    def "Testing show for private Topic  "() {
        given:
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
                active: true, photo: "abc".bytes, confirmPassword: Constants.password)
        user.save(flush:true)
        session.setAttribute('user',user)
        String topicName = "Groovy"
        Topic topic = new Topic(id: 1,createdBy:user,topicName: topicName,visibility: Visibility.PRIVATE)
        topic.save(flush:true)

        when:
        controller.show(1)

        then:
        response.redirectedUrl == '/login/index'



    }
}
