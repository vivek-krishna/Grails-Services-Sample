package com.intelligrape.service

import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

class NonTransactionalUserService {

    static transactional = false

    void createAndSaveMultipleUsers(){
        User.withTransaction {
            (1..10).each{Integer i->
                createAndSaveUser("Name ${i}", "password", "password")
            }
        }
    }

    void createAndSaveMultipleUsersWithException(){
        User.withTransaction {
            (1..10).each{Integer i->
                if(i == 5){
                    throw new RuntimeException("Runtime exception occurred")
                }
                else{
                    createAndSaveUser("Name ${i}", "password", "password")
                }
            }
        }
    }

    def createAndSaveUser(String name, String password, String confirmPassword){
        User user = new User(name: name, password: password, confirmPassword: confirmPassword)
        saveUser(user)
    }

    def saveUser(User user){
        user.save(failOnError: true)
    }

    String formatUsername(String name){
        return "Dear ${name}"
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    def createAndSaveMultipleUsersTransaction(){
        (1..10).each{Integer i->
            createAndSaveUser("Name ${i}", "password", "password")
        }

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    def createAndSaveMultipleUsersWithExceptionTransacted(){
        (1..10).each{Integer i->
            if(i == 5){
                throw new RuntimeException("Runtime exception occurred")
            }
            else{
                createAndSaveUser("Name ${i}", "password", "password")
            }
        }

    }
}
