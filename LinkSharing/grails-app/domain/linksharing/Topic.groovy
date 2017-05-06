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
    static mapping = {
        sort "topicName"

    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicName='" + topicName + '\'' +
                '}';
    }


   /* def afterInsert() {

        Topic.withNewSession {

            Subscription subscription = new Subscription(
                    user: this.createdBy,
                    topic: this,
                    seriousness: Seriousness.VERY_SERIOUS,
                    dateCreated: new Date(),
                    dateUpdated: new Date()
            )

            if (subscription.save(flush: true)) {
                log.info "${subscription}-> ${this.createdBy} subscribed for ${this}"

            } else {
                log.error "Subscription does not occured--- ${subscription.errors.allErrors}"
            }
        }
    }*/
}

enum Visibility{
    PUBLIC , PRIVATE
}