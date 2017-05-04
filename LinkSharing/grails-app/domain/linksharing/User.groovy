package linksharing

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
static hasMany = [topics:Topic,subscriptions:Subscription,readingItems:ReadingItem,resources:Resource]

    static transients = ['fullName']
    String getFullName() {
        return "${firstName}  ${lastName}"
    }
    static mapping = {image (sqlType: 'longblob' )}

    static constraints = {
        firstName blank: false, nullable: false
        lastName blank: false, nullable: false
        userName blank: false, nullable: false, unique: true
        email email: true, nullable: false, unique: true, blank: false
        password nullable: false, blank: false, size:5..15
        isAdmin nullable:true
        isActive nullable: true
        image nullable: true
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
