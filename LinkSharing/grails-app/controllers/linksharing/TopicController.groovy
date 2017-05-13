package linksharing

import com.ttn.co.ResourceSearchCO
import com.ttn.co.TopicCO
import com.ttn.util.Constants
import org.apache.tomcat.util.bcel.classfile.Constant

class TopicController {

    TopicService topicService = new TopicService()


    def index(String to) {
        TopicCO topicCO = new TopicCO(topicName: to,visibility: Visibility.PRIVATE)
        save(topicCO,"SERIOUS")
        render "Topic index"
    }
    def showTopic(Long id) {
        println id
        Topic topic = Topic.findById(id)
        println topic.subscriptions.size()
        List<Resource> resourceList = Resource.findAllByTopic(topic)
        User user = session.getAttribute('user')
        if (user) {

            render view:'topicShow',model: [topic:topic]

        } else {
            redirect(controller: 'login',action: 'index')
        }
        println topic



    }


/*    def save(TopicCO topicCO,String seriousness) {
        User user = session?.getAttribute('user')
        if (user) {

            topicCO.createdBy = user
            topicCO.visibility = Visibility.PUBLIC
            Topic topic =  new Topic()
            bindData(topic,topicCO,[include:['topicName','visibility']])
            User user1 = User.findByUserName(user.userName)
            user1.addToTopics(topic)
            seriousness = "SERIOUS"
            println topic
            println user1
           if( topicService.topicSave(topic,user1,Seriousness.convertIntoEnum(seriousness))){

               flash.message = "Success ! ${topic} has been saved by ${user}"
                forward(controller: 'user',action:'index')
           }

            else
               flash.error = "Failed to save the topic"
            redirect(controller: 'user')

           *//* if (topic.save(flush:true,failOnError:true)) {
                Subscription subscription = new Subscription(topic: topic,user: user1,seriousness: Seriousness.convertIntoEnum(seriousness))
                if( subscription.save(flush:true,failOnError:true))
                    log.info("Created user ${user} also subscribed to its created topic ${topic}")
                else
                    log.error("Subscription not saved due to some errors : ${subscription.errors.allErrors}")

                flash.message = "Success ! ${topic} has been saved by ${user}"
                render flash.message
            }
            else {
//                flash.error = "Failed to save topic"
                render "Failed to save topic"
            }*//*
        }
        else {
            flash.error = "Please Login First"

        }



    }*/
    def save(String topicName,String visibility) {
        println visibility+"----------------"
        Visibility visibility1 = Visibility.stringtoEnum(visibility)
        println visibility1
        User user = session.getAttribute('user')
        if (user) {
            Topic topic =  new Topic(topicName: topicName,createdBy: user,visibility: visibility1)
            String seriousness = Constants.defaultSeriousness
            Seriousness seriousness1 = Seriousness.convertIntoEnum(seriousness)
            if (topicService.topicSave(topic,user,seriousness1)) {
                flash.message = "Topic Saved Successfully"
                forward(controller:'user',action:'index')
            }
            else {
                flash.error = "Topic Not saved"
                forward(controller:'user',action:'index')
            }
        }
    }

    def show(ResourceSearchCO resourceSearchCO) {
        println resourceSearchCO
        Topic topic = Topic.findById(resourceSearchCO.topicId)
        if (topic) {
            if (topic.visibility == Visibility.PUBLIC)
                render "success for ${topic} whose visibility is ${topic.visibility}"
            else {
                User user = User.findByUserName(session.getAttribute('user').userName)
                Subscription subscription = Subscription.findByUserAndTopic(user,topic)
                if (subscription) {
                    render "success for ${topic} whose visibility is ${topic.visibility}"
                }
                else {
                    flash.error = "User not subscribed to required private topic"
                    redirect(controller:'login', action:'index')

                }

            }

        }
        else{
            flash.error = "Topic not exist in DB"
            redirect(controller:'login', action:'index')
        }

        println topic

    }


    def delete(int id) {
        Topic topic = Topic?.load(id)
        User user = session.getAttribute('user')
        println user.userName
        println topic.createdBy.userName
        if ((topic.createdBy.userName == user.userName)) {
            topic.delete(flush:true)
            render "${topic} successfully deleted by ${user.userName}"
        }
        else {
            flash.message = "You are not authenticate to delete this topic"
            render flash.message
        }
    }


    def trendingTopics() {
        render Topic.getTrendingTopics()
    }
    def subscribedUsers(int topicId) {
       render Topic.getSubscribedUsers(Topic.findById(topicId))

    }
}
