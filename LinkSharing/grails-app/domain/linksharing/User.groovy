package linksharing

import com.ttn.co.SearchCO
import javassist.bytecode.ByteArray
import sun.security.util.Password

class User {

    String userName
    String firstName
    String lastName
    String email
    String password
    Byte[] image
    boolean isAdmin
    boolean isActive
    Date dateCreated
    Date lastUpdated
    String confirmPassword
static hasMany = [topics:Topic,subscriptions:Subscription,readingItems:ReadingItem,resources:Resource]

    static transients = ['fullName','confirmPassword']
    String getFullName() {
        return "${firstName}  ${lastName}"
    }
    static mapping = {image (sqlType: 'longblob' )
                        sort id :'desc'}

    static constraints = {
        firstName blank: false, nullable: false
        lastName blank: false, nullable: false
        userName blank: false, nullable: false, unique: true
        email email: true, nullable: false, unique: true, blank: false
        password nullable: false, blank: false, size:5..15, validator: {val, obj->
            obj.confirmPassword == val
            }
        confirmPassword nullable: true, blank: true, size:5..15
        isAdmin nullable:true
        isActive nullable: true
        image nullable: true
        topics lazy:false


    }



    List<ReadingItem> getUnReadResources(SearchCO searchCO) {
//        List<ReadingItem> unreadItems =[]
        println searchCO
        if (searchCO.q) {
            List<ReadingItem> unreadItems = ReadingItem.createCriteria().list {
                        eq('user',this)
                        println searchCO.q
                        resource {
                            ilike('description',"%${searchCO.q}%")
                        }

                        eq('isRead',false)
                        max searchCO.max.toString()
                        order('lastUpdated',searchCO.order)
            }
            println unreadItems
            return unreadItems
        }

    }
    @Override
    public String toString() {
        return  userName
    }
}
