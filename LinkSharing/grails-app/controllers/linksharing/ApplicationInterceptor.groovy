package linksharing

import grails.interceptors.Matcher


class ApplicationInterceptor {

   ApplicationInterceptor() {
       matchAll()

   }
    boolean before() {
        log.info(params.toMapString())
//        if (!session.getAttribute('user')) {
//            redirect(controller:'Login',action: 'index')
//        }
        true
    }

    boolean after() {


        true
    }

    void afterView() {
        // no-op
    }
}
