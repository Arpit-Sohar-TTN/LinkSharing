package linksharing

class ReadingItemController {

    def index() { }
    def toggleIsRead(Long id) {
        User user = session.user
        Resource resource = Resource.findById(id)
        ReadingItem readingItem = ReadingItem.findByUser(user)
        println readingItem
        if (readingItem.isRead) {
            readingItem.delete()
            redirect (controller:'user',action:'index')
        }
        else {
            ReadingItem readingItem1 = new ReadingItem(user: user,isRead: true,resource: resource)
            readingItem1.save(flush:true)
            redirect (controller:'user',action:'index')

        }

    }

}
