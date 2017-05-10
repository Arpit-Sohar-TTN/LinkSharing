package linksharing

import com.ttn.co.DocumentResourceCO
import com.ttn.co.LinkResourceCO
import com.ttn.co.ResourceSearchCO

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

    def saveLinkResource(LinkResourceCO linkResourceCO) {
        LinkResource linkResource = new LinkResource()
        bindData(linkResource, linkResourceCO)
        if (linkResource.save(flush: true, failOnError: true)) {
            flash.message = "Link Resource Saved"
        } else {
            flash.error = "Fail to save resource"
        }
    }
    def saveDocumentResource(DocumentResourceCO documentResourceCO) {
        DocumentResource documentResource = new DocumentResource()
        bindData(documentResource, documentResourceCO)
        if (documentResource.save(flush: true, failOnError: true)) {
            flash.message = "Document Resource Saved"
        } else {
            flash.error = "Fail to save resource"
        }
    }
}
