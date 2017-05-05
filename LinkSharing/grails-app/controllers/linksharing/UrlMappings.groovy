package linksharing

class UrlMappings {
//    UrlMappings urlMappings
//    static defaultAction = "noAction"
    static allowedMethods = [index: 'GET', update: ['PUT', 'POST']]
    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/User"(controller: "User",action: "noAction")

        "/errorTest"(controller: "Test",action: "save")
        "/"(view:"/index")
        "/demo"(view:"/demo")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
