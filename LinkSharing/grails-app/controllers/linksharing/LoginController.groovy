package linksharing

import com.ttn.co.UserCO
import com.ttn.vo.ResourceVO

class LoginController {
	def userService

	def dummy() {
		render "Hello World"
	}

	def index() {
		println 1
		List<ResourceVO> topPosts = Resource.topPost()
		List<ResourceVO> recentShares = Resource.recentShares()
		if (session.user==null) {
			render view: 'index', model: [topPosts: topPosts, recentShares: recentShares]
		} else {
			redirect(controller: 'user',action: 'index')
		}
	}


	def loginHandler(String username, String password) {
		println 2
		User user = User.findByUserNameAndPassword(username, password)
		if (user) {
			if (user.isActive) {
				session.setAttribute("user", user)
				redirect(controller: 'user', action: 'index')
			} else {
				log.error("User is not active")
				flash.singleError = "Account not active Kindly contact admin"
				redirect(controller: 'login', action: 'index')
			}

		} else {
			log.error("User not exist in database")
			flash.singleError = "Wrong Username or Password"
			redirect(controller: 'login', action: 'index')


		}
	}

	def logout() {
		session.invalidate()
		forward(controller: 'Login', action: 'index')
	}

	def register(UserCO userCO) {
		User user = new User()
		bindData(user, userCO)
		def file = request.getFile('image')
		if (file) {
			user.photo = file.getBytes()
		}
		if (user.save(flush: true)) {
			session.setAttribute('user', user)
			flash.message = 'Registered Successfully'
			redirect(controller: 'login', action: 'index')
		} else {
			if (user.hasErrors()) {

				flash.error = user.errors.allErrors.collect { message(error: it) }
				redirect(controller: 'login', action: 'index')
			}
		}

	}

}