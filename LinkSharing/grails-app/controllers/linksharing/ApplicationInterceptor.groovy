package linksharing

class ApplicationInterceptor {

    ApplicationInterceptor() {
        matchAll().excludes(controller: 'login', action: 'index')
                .excludes(controller: 'login', action: 'loginHandler')
                .excludes(controller: 'login', action: 'logout')
                .excludes(controller: 'resource', action: 'showPost')
                .excludes(controller: 'resource', action: 'download')
                .excludes(controller: 'user', action: 'search')
                .excludes(controller: 'login', action: 'register')


    }

    boolean before() {
//		log.info(params.toMapString())
        if (!session.getAttribute('user')) {
            redirect(controller: 'login', action: 'index')
        }
        true


    }

    boolean after() {


        true
    }

}
