package linksharing

class ApplicationInterceptor {

	ApplicationInterceptor() {
      matchAll().excludes (controller:'login',action:'index')
			  .excludes (controller:'resource',action:'showPost')
		.excludes (controller:'login',action:'loginHandler')


	}

	boolean before() {
//		log.info(params.toMapString())
		 if (!session.getAttribute('user')) {
			 redirect(controller:'login',action: 'index')
		 }
	true

	}

	boolean after() {


		true
	}

}
