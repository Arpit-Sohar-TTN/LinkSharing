package linksharing

class UtilController {

    def index() {
        println "Debug log enabled?: " + log.debugEnabled
        log.debug "First piggy wrote to Debug"
        log.info "Second piggy wrote to Info"
        log.error "And the third piggy wrote to Error"
        render "Test"
    }
}
