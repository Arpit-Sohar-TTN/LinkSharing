package linksharing

import com.ttn.co.DocumentResourceCO
import com.ttn.co.LinkResourceCO
import com.ttn.co.ResourceSearchCO
import com.ttn.util.Constants
import com.ttn.vo.RatingInfoVO
import com.ttn.vo.ResourceVO

class ResourceController {

    def index() {}

    def delete(int id) {
        Resource resource = Resource?.load(id)
        User user = session.getAttribute('user')
        //Resource can be delete by either creator of resource or creator of topic or by Admin
        if (resource) {
            if ((resource.createdBy.userName == user.userName) || (user.isAdmin) ||
                    (resource.topic.createdBy.userName == user.userName)) {
                resource.delete(flush: true)
                render "${resource} successfully deleted by ${user.userName}"
            } else {
                render "Sorry you are not authorized for delete this resource"
            }
        } else {
            render "Resource not exist in db"
        }
    }


    def search(ResourceSearchCO co) {

        /* if (co.q) {
             co.visibility = Visibility.PUBLIC
         }*/
        println co
        User user = session.getAttribute('user')
        List<Resource> resources = Resource.search(co, user)?.list()
        render "Result -> ${resources}"
    }

    def show(Long resourceId) {
        render Resource.getRatingInfo(resourceId)
    }

    def saveLinkResource(String url,String description,String topic) {
//        bindData(linkResource, linkResourceCO)
        User user = session.getAttribute('user')
        Topic topic1 = Topic.findByTopicName(topic)
        LinkResource linkResource = new LinkResource(url: url,createdBy: user,description: description,topic: topic1)

//        linkResource.createdBy = user
//        linkResource.topic = Topic.get(2)
        if (linkResource.save(flush: true)) {
            flash.message = "Link Resource Saved"
        } else {
            flash.error = "Fail to save resource"
        }
        redirect(controller:'user',action:'index')
    }
    def saveDocumentResource( String description, String topicName) {
        User user = session.getAttribute('user')
        def file = params.attachment
        String filePath = "${Constants.filePath}/${file}"
        File file2 = new File(filePath)
        file.transferTo(file2)
        Topic topic1 = Topic.findByTopicName(topicName)
        DocumentResource documentResource = new DocumentResource(filePath: filePath,createdBy: user,description: description,topic: topic1)
        if (documentResource.save(flush: true, failOnError: true)) {
            flash.message = "Document Resource Saved"
        } else {
            flash.error = "Fail to save resource"
        }
        redirect(controller:'user',action:'index')
    }

    def showPost() {
        String id = params.id
        Resource resource = Resource.get(id.toInteger())
        RatingInfoVO ratingInfoVO = Resource.getRatingInfo(id.toLong())
        render view:'index',model: [resource:resource,ratingInfoVO:ratingInfoVO]
    }
    def ratePost(int resourceId,int score) {
        User user = session.user
        println score
        Resource resource = Resource.get(resourceId)
     /*   ResourceRating resourceRating1 = ResourceRating.findByUserAndResource(user,resource)
        if (resourceRating1) {
            resourceRating1.score = score
            if (resourceRating1.save(flush:true,failOnError: true)) {
                flash.message = "Resource Rating updated succcessfully"
                redirect (controller: 'resource',action: 'showPost')
            }
            else {
                flash.error = "Fail to rate resource try again after some time"
                redirect (controller: 'resource',action: 'showPost')
            }
        } else {*/

            ResourceRating resourceRating = new ResourceRating(user: user, resource: resource, score: score)

            if (resourceRating.save(flush: true,failOnErro:true)) {
                flash.message = "Resource Rated succcessfully"
                forward action: "showPost", params: [id: "${resource.id}"]

            } else {
                flash.loginError = "Fail to rate resource try again after some time"
                forward action: "showPost", params: [id: "${resource.id}"]

            }

    }
}
