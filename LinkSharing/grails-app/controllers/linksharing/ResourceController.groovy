package linksharing

import com.ttn.co.ResourceSearchCO
import com.ttn.util.Constants
import com.ttn.vo.RatingInfoVO

class ResourceController {

    def index() {}

    def delete(Long topicId, Long resourceId) {

        Resource resource = Resource.get(resourceId)
        User user = session.getAttribute('user')
        if (resource) {
            if ((resource.createdBy.userName == user.userName) || (user.isAdmin) ||
                    (resource.topic.createdBy.userName == user.userName)) {
                resource.delete(flush: true)
                flash.message = "${resource} successfully deleted by ${user.userName}"
            } else {
                flash.loginError = "Sorry you are not authorized for delete this resource"
            }
        } else {
            flash.loginError = "Resource not exist in db"
        }
        redirect(controller: 'topic', action: 'showTopic', params: [id: "${topicId}"])
    }


    def search(ResourceSearchCO co) {
        User user = session.getAttribute('user')
        List<Resource> resources = Resource.search(co, user)?.list()
        render "Result -> ${resources}"
    }

    def show(Long resourceId) {
        render Resource.getRatingInfo(resourceId)
    }

    def saveLinkResource(String url, String description, String topic) {
        User user = session.getAttribute('user')
        Topic topic1 = Topic.findByTopicName(topic)
        LinkResource linkResource = new LinkResource(url: url, createdBy: user, description: description, topic: topic1)

        if (linkResource.save(flush: true)) {
            flash.message = "Link Resource Saved"
        } else {
            flash.singleError = "Fail to save resource"
        }
        redirect(controller: 'user', action: 'index')
    }

    def saveDocumentResource(String description, String topicName) {
        User user = session.getAttribute('user')
        def file = params.attachment
        String filePath = "${Constants.FILEPATH2}" + params.attachment.getOriginalFilename()
        File file2 = new File(filePath)
        file.transferTo(file2)
        Topic topic1 = Topic.findByTopicName(topicName)
        DocumentResource documentResource = new DocumentResource(filePath: filePath, createdBy: user, description: description, topic: topic1)
        if (documentResource.save(flush: true, failOnError: true)) {
            flash.message = "Document Resource Saved"
        } else {
            flash.error = "Fail to save resource"
        }
        redirect(controller: 'user', action: 'index')
    }

    def showPost() {
        User user = session.user
        if (user) {
            String id = params.id
            Resource resource = Resource.get(id.toInteger())
            RatingInfoVO ratingInfoVO = Resource.getRatingInfo(id.toLong())
            render view: 'index', model: [resource: resource, ratingInfoVO: ratingInfoVO]
        } else {
            flash.message = "Please Signup first"
            redirect(controller: 'login', action: 'index')
        }

    }

    def ratePost(int resourceId, int score) {
        User user = session.user
        Resource resource = Resource.get(resourceId)
        ResourceRating resourceRating = new ResourceRating(user: user, resource: resource, score: score)
       /* if (resourceRating.save(flush: true, failOnError: true)) {
            flash.message = "Resource Rated succcessfully"
            println "Resource Rated succcessfully"
            forward action: "showPost", params: [id: "${resource.id}"]

        } else {
            flash.loginError = "Fail to rate resource try again after some time"
            println "Fail to rate resource try again after some time"
            forward action: "showPost", params: [id: "${resource.id}"]

        }*/
        resourceRating.save(flush:true, failOnError:true)

    }

    def download(Long resourceId) {
        User user = session.user
        Resource resource = Resource.get(resourceId)
        def file = new File(resource.filePath)
        if (user) {
            if (file.exists()) {
                response.setContentType("application/octet-stream")
                response.setHeader("Content-disposition", "attachment;filename=\"${file.name}\"")
                response.setContentType("text/plain")
                response.outputStream << file.bytes

            } else {
                flash.singleError = "Error Downloading Post"
                redirect(controller: 'user', action: 'index')
            }
        } else {
            flash.message = "Please Signup first"
            redirect(controller: 'login', action: 'index')
        }
    }

    def editResourceDescription(Long resourceId, String description) {
        Resource resource = Resource.get(resourceId.toLong())
        resource.description = description
        if (resource.save(flush: true, failOnError: true)) {
            flash.message = "Resource edited successfully"
        } else {
            flash.error = "Failed to edit resource"
        }
        RatingInfoVO ratingInfoVO = Resource.getRatingInfo(resourceId.toLong())
        render view: 'index', model: [resource: resource, ratingInfoVO: ratingInfoVO]
    }
}
