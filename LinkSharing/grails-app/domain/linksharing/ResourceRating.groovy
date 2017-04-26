package linksharing

class ResourceRating {

    Resource resource
    User user
    int score
    Date dateCreated
    Date dateUpdated

    static belongsTo = [resource:Resource]

    static constraints = {
        resource nullable: false
        score nullable: false, size:1..5
        user nullable: false
        resource (unique: ['user'])
    }
}
