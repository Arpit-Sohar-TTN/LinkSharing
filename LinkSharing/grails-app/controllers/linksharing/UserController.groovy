package linksharing

import com.ttn.co.SearchCO
import com.ttn.vo.TopicVO
import com.ttn.vo.UserVO
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

class UserController {
    def userService
//    static responseFormat = ["json"]

//  static namespace = "test1"

    def index() {
//       render "User dashboard ${session['user']}"
        User user = session.getAttribute('user')
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        UserVO userVO = userService.getUserVO(user)

        println userVO.resourceVOList
        List subscribedTopic = User.getSubscribedTopic(user)
//        List<TopicVO> trendingTopics2 = userService.checkLoggedInUserSubscription(user,trendingTopics)
        render ('view':'index',model: [user:user,trendingTopics:trendingTopics,userVO:userVO,subscribedTopic:subscribedTopic])
    }

    def inbox(SearchCO searchCO) {

        User user = session.getAttribute('user')
        render  user.getUnReadResources(searchCO)
    }

    def changeIsRead(Long id, Boolean isRead) {

        /*
            According to Ques
        if( ReadingItem.executeUpdate('Update ReadingItem set isRead=:read where id=:i',[read:isRead,i:id]))
            render "Success"
        else
            render "Failure"*/
        //According to requirement in project
        User user = session.getAttribute('user')
        Resource resource = Resource.get(id)
        ReadingItem readingItem = ReadingItem.findByUserAndResource(user,resource)
        if (readingItem){
            if (readingItem.isRead) {
                readingItem.isRead = false
                readingItem.save(flush:true,failOnError:true)
                render "${resource} is unread "
            }
            else {
                readingItem.isRead = true
                readingItem.save(flush:true,failOnError:true)
                render "${resource} is read"

            }
        } else {
            render "failure"
        }
    }

    def testAction() {
//    render "test"
    Map map1 = [1:"test1",2:"test2",3:"Mango"]
    respond ([1:"test1",2:"test2",3:"Mango"])
}

    def topicShow(Long id) {
        println id
        Topic topic = Topic.findById(id)
        if (session.getAttribute('user')) {
            if (topic) {
                if (topic.visibility == Visibility.PUBLIC)
                    redirect(controller: 'topic', action: 'showTopic')
                else {
                    User user = User.findById(id)
                    Subscription subscription = Subscription.findByUserAndTopic(user, topic)
                    if (subscription) {
//                    render "success for ${topic} whose visibility is ${topic.visibility}"
                        render view: 'topic/index'
                    } else {
                        flash.error = "User not subscribed to required private topic"
                        redirect(controller: 'login', action: 'index')

                    }

                }

            } else {
                flash.error = "Topic not exist in DB"
                redirect(controller: 'login', action: 'index')
            }
        } else {
            redirect(controller: 'login',action: 'index')
        }
        println topic


    }

    def upload() {
        def f = params.myFile
        render f.inputStream.text
    }
    def download() {
//        byte[] orderPDF = ... // create the bytes from some source
        byte[] bytes = new File("/home/arpit/Desktop/test.json").bytes
        response.setHeader("Content-disposition", "attachment; filename= abc.json")
        response.contentLength = bytes.length
        response.outputStream << bytes
    }


}
