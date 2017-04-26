package linksharing

class ReadingItem {
    User user
    boolean isRead
    Resource resource
    Date dateCreated
    Date dateUpdated

    static belongsTo = [user:User, resource:Resource]

    static constraints = {
        user nullable: false
        isRead nullable:false
        resource nullable: false
        resource unique: ['user']

    }
}
