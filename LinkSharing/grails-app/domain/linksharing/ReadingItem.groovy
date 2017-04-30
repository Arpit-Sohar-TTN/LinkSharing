package linksharing

class ReadingItem {
    User user
    boolean isRead
    Resource resource
    Date dateCreated
    Date dateUpdated

    static belongsTo = [user:User, resource:Resource]

    static constraints = {
        resource unique: ['user']

    }
}
