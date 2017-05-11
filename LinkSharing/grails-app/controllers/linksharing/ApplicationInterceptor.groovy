package linksharing

import grails.interceptors.Matcher


class ApplicationInterceptor {

   ApplicationInterceptor() {
//       matchAll().excludes (controller:'user',action: 'index')

   }
    boolean before() {
        log.info(params.toMapString())
       /* if (!session.getAttribute('user')) {
            redirect(controller:'login',action: 'index')
        }*/


    }

    boolean after() {


        true
    }

    void afterView() {
        // no-op
    }
}
