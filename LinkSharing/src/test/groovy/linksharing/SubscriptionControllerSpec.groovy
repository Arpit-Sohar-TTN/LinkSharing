package linksharing

import com.ttn.util.Constants
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SubscriptionController)
@Mock([User,Topic,Subscription])
class SubscriptionControllerSpec extends Specification {

   def "Testing PUBLIC topic for save action of subscription"() {
       given:
       int topicId = 2
       User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
               email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
               active: true, photo: "abc".bytes, confirmPassword: Constants.password)
       session.setAttribute('user',user)
       Topic topic1  =  new Topic(id: 1, topicName: 'G1',createdBy: user,visibility: Visibility.PUBLIC)
       Topic topic2  =  new Topic(id: 2, topicName: 'G2',createdBy: new User(),visibility: Visibility.PUBLIC)
        user.addToTopics(topic1)
        user.addToTopics(topic2)
       topic1.save(flush:true)
       topic2.save(flush:true)
       user.save(flush:true)


       when:
       controller.save(topicId)

       then:
       Subscription.count==1

   }

    def "Testing PRIVATE topic for save action of subscription"() {
        given:
        int topicId = 2
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
                active: true, photo: "abc".bytes, confirmPassword: Constants.password)
        session.setAttribute('user',user)
        Topic topic1  =  new Topic(id: 1, topicName: 'G1',createdBy: user,visibility: Visibility.PUBLIC)
        Topic topic2  =  new Topic(id: 2, topicName: 'G2',createdBy: new User(),visibility: Visibility.PRIVATE)
        user.addToTopics(topic1)
        user.addToTopics(topic2)
        topic1.save(flush:true)
        topic2.save(flush:true)
        user.save(flush:true)


        when:
        controller.save(topicId)

        then:
        response.text == "${topic2} is private topic"

    }

    def "Testing  uniqueness between topic and createdBy for save action of subscription"() {
        given:
        int topicId = 1
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
                active: true, photo: "abc".bytes, confirmPassword: Constants.password)
        session.setAttribute('user',user)
        Topic topic1  =  new Topic(id: 1, topicName: 'G1',createdBy: user,visibility: Visibility.PUBLIC)
        Topic topic2  =  new Topic(id: 2, topicName: 'G2',createdBy: new User(),visibility: Visibility.PRIVATE)
        user.addToTopics(topic1)
        user.addToTopics(topic2)
        topic1.save(flush:true)
        topic2.save(flush:true)
        user.save(flush:true)


        when:
        controller.save(topicId)


        then:
//        response.text == "${user} can not able to subscribe ${topic1} due to "
        Subscription.count == 1

        when:
        controller.save(topicId)

        then:
       Subscription.count ==1

    }

    def "Testing delete topic action of subscription"() {
        given:
        int topicId = 1
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
                active: true, photo: "abc".bytes, confirmPassword: Constants.password)
        session.setAttribute('user',user)
        Topic topic  =  new Topic(id: 1, topicName: 'G1',createdBy: user,visibility: Visibility.PUBLIC)
        user.addToTopics(topic)
        topic.save(flush:true)
        user.save(flush:true)
        Subscription subscription = new Subscription(topic: topic,user:user,seriousness: Seriousness.CASUAL)
        subscription.save(flush:true)

        when:
        controller.delete(topicId)

        then:
        response.text == "${user} no longer subscribed to ${topic}"

    }

    def "Testing delete topic action when there is no subscription"() {
        given:
        int topicId = 1
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
                active: true, photo: "abc".bytes, confirmPassword: Constants.password)
        session.setAttribute('user',user)
        Topic topic  =  new Topic(id: 1, topicName: 'G1',createdBy: user,visibility: Visibility.PUBLIC)
        user.addToTopics(topic)
        topic.save(flush:true)
        user.save(flush:true)
        Subscription subscription = new Subscription(topic: topic,user:user,seriousness: Seriousness.CASUAL)


        when:
        controller.delete(topicId)

        then:
        response.text == "${user} already not subscribed to ${topic}"

    }

    def "Testing update action of subscription"() {
        given:
        int topicId = 2
        String seriousness = Seriousness.CASUAL
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
                active: true, photo: "abc".bytes, confirmPassword: Constants.password)
        session.setAttribute('user',user)
        Topic topic1  =  new Topic(id: 1, topicName: 'G1',createdBy: user,visibility: Visibility.PUBLIC)
        Topic topic2  =  new Topic(id: 2, topicName: 'G2',createdBy: new User(),visibility: Visibility.PUBLIC)
        user.addToTopics(topic1)
        user.addToTopics(topic2)
        topic1.save(flush:true)
        topic2.save(flush:true)
        user.save(flush:true)


        when:
        controller.save(topicId)
        controller.update(topicId,seriousness)

        then:
        response.text =="User{userName='ArpitSohar'} subscribe Topic{topicName='G2'}User{userName='ArpitSohar'} now subscribed Topic{topicName='G2'} by CASUAL"

    }


    def "Testing update action when there is no subscription exist"() {
        given:
        int topicId = 2
        String seriousness = Seriousness.CASUAL
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
                active: true, photo: "abc".bytes, confirmPassword: Constants.password)
        session.setAttribute('user',user)
        Topic topic1  =  new Topic(id: 1, topicName: 'G1',createdBy: user,visibility: Visibility.PUBLIC)
        Topic topic2  =  new Topic(id: 2, topicName: 'G2',createdBy: new User(),visibility: Visibility.PUBLIC)
        user.addToTopics(topic1)
        user.addToTopics(topic2)
        topic1.save(flush:true)
        topic2.save(flush:true)
        user.save(flush:true)


        when:
        controller.update(topicId,seriousness)

        then:
        response.text =="${user} not subscribed to ${topic2}"

    }
    def "Testing update action of subscription with same seriousness"() {
        given:
        int topicId = 2
        String seriousness = Seriousness.SERIOUS
        User user = new User(firstName: "Arpit", lastName: "Sohar", userName: "ArpitSohar",
                email: "arpit.sohar@ttn.com", password: Constants.password, admin: false,
                active: true, photo: "abc".bytes, confirmPassword: Constants.password)
        session.setAttribute('user',user)
        Topic topic1  =  new Topic(id: 1, topicName: 'G1',createdBy: user,visibility: Visibility.PUBLIC)
        Topic topic2  =  new Topic(id: 2, topicName: 'G2',createdBy: new User(),visibility: Visibility.PUBLIC)
        user.addToTopics(topic1)
        user.addToTopics(topic2)
        topic1.save(flush:true)
        topic2.save(flush:true)
        user.save(flush:true)


        when:
        controller.save(topicId)
        controller.update(topicId,seriousness)

        then:
        response.text =="User{userName='ArpitSohar'} subscribe Topic{topicName='G2'}User{userName='ArpitSohar'} already subscribed Topic{topicName='G2'} by SERIOUS"

    }
}
