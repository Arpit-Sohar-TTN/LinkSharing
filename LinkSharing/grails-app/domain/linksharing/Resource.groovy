package linksharing

import com.ttn.co.ResourceSearchCO
import com.ttn.vo.RatingInfoVO
import com.ttn.vo.ResourceVO

abstract class Resource {
    User createdBy
    String description

    Date dateCreated
    Date lastUpdated
    def ratingInfo

    static belongsTo = [createdBy: User, topic: Topic]
    static hasMany = [readingItems: ReadingItem, resourceRatings: ResourceRating]

    static constraints = {
    }
    static mapping = {
        description(type: "text")

    }
    static transients = ['ratingInfo']

      static namedQueries = {
        search{ ResourceSearchCO resourceSearchCO , User user ->
            println 1
            if (resourceSearchCO.visibility.equals( Visibility.PUBLIC)) {
                println 2
                eq('topic.id',resourceSearchCO.topicId)
            }
            else {
                println 3
                def topic  = Topic.findById(resourceSearchCO.topicId)
                println topic
                println Subscription.findByTopicAndUser(topic,user)
                if (Subscription.findByTopicAndUser(topic,user)) {
                    println 4
                    eq('topic.id',resourceSearchCO.topicId  )
                }
                else
                    null
            }
           /* if (resourceSearchCO) {
                if (resourceSearchCO.topicId) {
                    eq('topic.id', resourceSearchCO.topicId)
                }
                if (resourceSearchCO.visibility && resourceSearchCO.visibility == Visibility.PUBLIC) {
                    'topic' {
                        eq('visibility', Visibility.PUBLIC)
                    }
                }

            }*/
        }
    }

   /* static namedQueries = {
        search { ResourceSearchCO co ->
            if (co.topicId) {
                eq('topic', Topic.get(co.topicId))
                topic {
                    eq('visibility', Visibility.stringtoEnum(co.visibility))
                }
            }

        }
    }*/


    static RatingInfoVO getRatingInfo(Long resourceId) {
       List values =  Resource.createCriteria().list {
                projections {
                    resourceRatings {
                        count('score')
                        sum('score')
                        avg('score')
                    }
                    eq("id", resourceId)
                }
            }
            println values
        RatingInfoVO ratingInfoVO = new RatingInfoVO(totalVotes: values.get(0).getAt(0), totalScore: values.get(0).getAt(1), averageScore: values.get(0).getAt(2))
        return ratingInfoVO
    }

    /*static List<Resource> topPost() {
        List values = ResourceRating.createCriteria().list {
            projections {
                property('resource.id')
            }
           groupProperty('resource.id')
            count('resource.id','count')
            order("count", "desc")
            maxResults 5
        }
        List topPosts = Resource.getAll(values)
        return topPosts

    }*/

    /*static List<Resource> topPost() {
        ReadingItem readingItem = ReadingItem.get(1)
        ResourceRating resourceRating = new ResourceRating(user: readingItem.user,
                resource: readingItem.resource,score: 5)
        resourceRating.save(flush:true)
        List resourcesId = ResourceRating.createCriteria().list {
            projections {
                property('resource')
            }
            groupProperty('resource')
            count('resource', 'resourceCount')
            order('resourceCount', 'desc')
            maxResults(5    )
        }
        println( resourcesId*.id)
        List<Resource> topPosts = Resource.getAll(resourcesId*.id)
        return topPosts
    }*/

    static List<ResourceVO> topPost() {

        List<ResourceVO> list = Resource.createCriteria().list {
            projections {
                groupProperty("id")
                property('description')
                property('createdBy')
                property('topic')
                resourceRatings {
                    count('id', "count")
                }

            }
            order("count", "desc")
            maxResults 5 // This is just for pagination
            firstResult 0
        }
        println(list)
        List<ResourceVO> resourceVOList = []
        list.each {element->
            resourceVOList.add(new ResourceVO(id:element.getAt(0),description:element.getAt(1),createdBy:element.getAt(2),
                    topic: element.getAt(3),rateCount:element.getAt(4)))
        }
        return resourceVOList
    }

    static List<ResourceVO> recentShares() {
        List<ResourceVO> list = Resource.createCriteria().list {
            projections {
                groupProperty("id")
                property('description')
                property('createdBy')
                property('topic')


            }
            order("lastUpdated", "desc")
            maxResults 2 // This is just for pagination
            firstResult 0
        }
        println(list)
        List<ResourceVO> resourceVOList = []
        list.each {element->
            resourceVOList.add(new ResourceVO(id:element.getAt(0),description:element.getAt(1),createdBy:element.getAt(2),
                    topic: element.getAt(3)))
        }
        return resourceVOList
    }


}