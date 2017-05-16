package linksharing

import  com.ttn.co.ResourceSearchCO
import com.ttn.util.Constants
import com.ttn.vo.RatingInfoVO

class ResourceController {

	def index() {}

	def delete(Long topicId, Long resourceId) {

		Resource resource = Resource.get(resourceId)
		User user = session.getAttribute('user')
//Resource can be delete by either creator of resource or creator of topic or by Admin
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

		/* if (co.q) {
			 co.visibility = Visibility.PUBLIC
		 }*/

		User user = session.getAttribute('user')
		List<Resource> resources = Resource.search(co, user)?.list()
		render "Result -> ${resources}"
	}

	def show(Long resourceId) {
		render Resource.getRatingInfo(resourceId)
	}

	def saveLinkResource(String url, String description, String topic) {
//        bindData(linkResource, linkResourceCO)
		User user = session.getAttribute('user')
		Topic topic1 = Topic.findByTopicName(topic)
		LinkResource linkResource = new LinkResource(url: url, createdBy: user, description: description, topic: topic1)

//        linkResource.createdBy = user
//        linkResource.topic = Topic.get(2)
		if (linkResource.save(flush: true)) {
			flash.message = "Link Resource Saved"
		} else {
			flash.error = "Fail to save resource"
		}
		redirect(controller: 'user', action: 'index')
	}

	def saveDocumentResource(String description, String topicName) {
		User user = session.getAttribute('user')
/*

        String destinationDirectory =  "/home/mayank/Desktop/LinkSharingDocuments/"
        def servletContext = ServletContextHolder.servletContext
        def storagePath = servletContext.getRealPath(destinationDirectory)
            // Create storage path directory if it does not exist
            def storagePathDirectory = new File(storagePath)
            if (!storagePathDirectory.exists()) {

                if (storagePathDirectory.mkdirs()) {

                } else {

                }
            }
*/



		def file = params.attachment
/*        String FILE_PATH = "${Constants.FILE_PATH}/${file}"*/
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

		if (resourceRating.save(flush: true, failOnErro: true)) {
			flash.message = "Resource Rated succcessfully"
			forward action: "showPost", params: [id: "${resource.id}"]

		} else {
			flash.loginError = "Fail to rate resource try again after some time"
			forward action: "showPost", params: [id: "${resource.id}"]

		}

	}

	def download(Long resourceId) {
		Resource resource = Resource.get(resourceId)
		def file = new File(resource.filePath)

		if (file.exists()) {
			response.setContentType("application/octet-stream")
			response.setHeader("Content-disposition", "attachment;filename=\"${file.name}\"")
			response.setContentType("text/plain")
			response.outputStream << file.bytes

		}
		else {
			flash.error = "Error Downloading Post"
			redirect(controller: 'user', action: 'dashboard')
		} // appropriate error handling
		/*String filePath = "/home/arpit/LinkSharingDocuments/"
		byte[] bytes = new File(filePath).getBytes()
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1)
// log.info("filaName : $fileName")
		response.setHeader("Content-disposition", "attachment; filename=$fileName")
		response.contentLength = bytes.length
		response.outputStream << bytes*/
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
