package linksharing

class ReadingItemController {

	def index() {}

	def toggleIsRead(Long id) {
		User user = session.user
		Resource resource = Resource.findById(id)
		ReadingItem readingItem = ReadingItem.findByUserAndResource(user, resource)
		if (readingItem.isRead) {
			readingItem.isRead = false
			readingItem.save(flush: true)
			redirect(controller: 'user', action: 'index')
		} else {
			readingItem.isRead = true
			readingItem.save(flush: true)
			redirect(controller: 'user', action: 'index')

		}

	}

}
