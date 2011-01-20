package com.intelligrape.service

class User {
	String name
	String password
	String confirmPassword
	static constraints = {
		name(nullable : false, blank : false)
		password(nullable: false, blank: false)
		confirmPassword(nullable: false, validator:{ val, obj->
			if(val != obj.password){
				return "user.passwords.mismatch"
			}
		})
		
    }

    def userService

    String formatName(){
        return userService.formatUsername(this.name)
    }
}
