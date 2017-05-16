package linksharing


class LoginInterceptor {


	LoginInterceptor() {
		matchAll().excludes(controller: 'login')
	}

	boolean before() {
		/* if(!session.getAttribute('user')) {

			 redirect(controller: "login", action: "index")
		 }*/
		true
	}

	boolean after() { true }
}
