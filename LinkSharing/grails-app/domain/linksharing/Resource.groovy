package linksharing

abstract class Resource {
    String description
    Topic topic
    Date dateCreated
    Date lastUpdated

    static belongsTo = [createdBy:User,topic:Topic]
    static hasMany = [readingItems:ReadingItem,resourceRatings:ResourceRating]

    static constraints = {
    }
    static mapping = {
        description(type:  "text")
    }
}
