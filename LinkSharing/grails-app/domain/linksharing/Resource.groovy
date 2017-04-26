package linksharing

abstract class Resource {
    String description
    User createdBy
    Topic topic
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user:User,topic:Topic]
    static hasMany = [readingItems:ReadingItem,resourceRatings:ResourceRating]

    static constraints = {
    }
    static mapping = {
        description(type:  "text")
    }
}
