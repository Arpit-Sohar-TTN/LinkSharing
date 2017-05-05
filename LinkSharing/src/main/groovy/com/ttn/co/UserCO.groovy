package com.ttn.co

import grails.validation.Validateable
import groovy.transform.ToString

/**
 * Created by arpit on 5/5/17.
 */
@ToString
class UserCO implements Validateable {
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
 static constraints ={email email: true, nullable: false, unique: true, blank: false}

}
