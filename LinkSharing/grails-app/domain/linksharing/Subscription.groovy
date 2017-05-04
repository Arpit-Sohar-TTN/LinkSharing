package linksharing

class Subscription {
    Topic topic
    User user
    Seriousness seriousness
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user:User , topic:Topic]

    static constraints = {
        user nullable: false
        topic nullable: false
        seriousness nullable: false
        user(unique: ['topic'])
    }
}
enum Seriousness {
    SERIOUS,
    VERY_SERIOUS,
    CASUAL
}
