package linksharing

class ResourceController {

    def index() { }

    def delete(int id) {
        Resource resource = Resource?.load(id)
        User user = session.getAttribute('user')
        //Resource can be delete by either creator of resource or creator of topic or by Admin
        if (resource) {
            if ( (resource.createdBy.userName==user.userName) || (user.isAdmin) ||
                    (resource.topic.createdBy.userName==user.userName) ) {
                resource.delete(flush:true)
                render "${resource} successfully deleted by ${user.userName}"
            } else {
                render "Sorry you are not authorized for delete this resource"
            }
        }
        else {
            render "Resource not exist in db"
        }
    }
}
