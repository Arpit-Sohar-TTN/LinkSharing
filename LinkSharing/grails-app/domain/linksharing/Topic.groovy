package linksharing

class Topic {
    String topicName;
    User createdBy
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static belongsTo = [user:User]
    static hasMany = [subscriptions:Subscription , resources:Resource]

    static constraints = {
        topicName nullable: false,blank: false
        visibility nullable: false
        createdBy nullable: false
        createdBy(unique: ['topicName'])
    }


}

enum Visibility{
    PUBLIC , PRIVATE
}