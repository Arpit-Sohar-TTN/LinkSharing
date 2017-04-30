package linksharing

class Topic {
    String topicName;
    User createdBy
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static belongsTo = [createdBy:User]
    static hasMany = [subscriptions:Subscription , resources:Resource]

    static constraints = {
        topicName blank: false
        createdBy(unique: ['topicName'])
    }


}

enum Visibility{
    PUBLIC , PRIVATE
}