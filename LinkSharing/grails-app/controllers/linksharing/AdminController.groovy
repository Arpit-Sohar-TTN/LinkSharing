package linksharing

class AdminController {

	def index() {

		int offset, max
		if (params.offset) {
			offset = params.int('offset')
		}
		if (params.max) {
			max = params.int('max')
		}

// List<User> userList = User.findAll()
		if (offset ?: 0)
			offset = 0
		if (!max)
			max = 5
		List<User> userList = User.createCriteria().list(offset: offset, max: 5) { }
		render view: "dashboard", model: ["users": userList]
	}
	def toggleActive(Long id) {
		User user = User.get(id)
		if (user.isActive) {
			user.isActive = false
		}
		else {
			user.isActive = true
		}

		user.save(flush:true)
		flash.message = "${user.userName} activeness change"
		redirect(controller:'admin', action:'index')
	}
}
