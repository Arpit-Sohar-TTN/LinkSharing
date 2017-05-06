package linksharing

import com.ttn.co.TopicCO

class TopicController {

    def index(String to) {
        TopicCO topicCO = new TopicCO(topicName: to,visibility: Visibility.PUBLIC)
        save(topicCO,"SERIOUS")
        render "Topic index"
    }

    def save(TopicCO topicCO,String seriousness) {
        User user = session?.getAttribute('user')
        if (user) {
            topicCO.createdBy = user
            Topic topic =  new Topic()
            bindData(topic,topicCO)
            println topic.createdBy
            println topic.topicName
            println user.userName
            User user1 = User.findByUserName(user.userName)
            println topic.toString() + " " + user1.toString()
            user1.addToTopics(topic)
            println user1.confirmPassword
            user1.confirmPassword = 'arpit'
            if (topic.save(flush:true)) {
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
            }
        }
        else {
            render "First login then save the topic"
        }



    }

    def show(int id) {
        Topic topic = Topic?.read(id)
        if (topic) {
            if (topic.visibility == Visibility.PUBLIC)
                render "success for ${topic} whose visibility is ${topic.visibility}"
            else {
                User user = User.findById(id)
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
}
