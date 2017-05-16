package com.ttn.co

import grails.validation.Validateable
import groovy.transform.ToString

/**
 * Created by mayank on 5/5/17.
 */
@ToString
class UserCO implements Validateable {
	String userName
	String firstName
	String lastName
	String email
	String password
	boolean isAdmin = false
	boolean isActive = true
	String confirmPassword
// static constraints ={email email: true, unique: true, blank: false}

}
