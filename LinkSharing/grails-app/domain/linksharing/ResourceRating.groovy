package linksharing

class ResourceRating {

    Resource resource
    User user
    int score
    Date dateCreated
    Date dateUpdated

    static belongsTo = [resource:Resource]

    static constraints = {
        score   min: 1, max: 5
        resource (unique: ['user'])
    }
}
