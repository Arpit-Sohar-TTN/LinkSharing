package linksharing

import com.ttn.co.UserCO

class LoginController {



        def index(){
           render "Login Here"
        }

        def loginHandler(String username,String password) {
            User user = User.findByUserNameAndPassword(username,password)
            if (user) {
                if (user.isActive){
                    session.setAttribute("user",user)
                    redirect(controller: user, action: 'index')
                } else {
                    log.error("User is not active")
                    flash.error = "User is not active"
                    render flash.error
                }

            }else {
                    log.error("User not exist in database")
                    flash.error = "User not exist in database"
                    render flash.error


            }

        }
        def logout() {
            session.invalidate()
            forward(controller:'Login',action:'index')
        }

//            log.info user
//   Hit the url        http://localhost:8080/login/register?firstName=Jay&lastName=Saini&userName=Jay&email=jay.saini@ttn.com&password=jaysaini&confirmPassword=jaysaini
        def register(UserCO user) {
//            def user = new User(params)       1st way of binding data

//            User user  = new User()           2nd way of binding data
//            user.properties = params
            User user2 = new User()
            bindData(user2,user)
            println user

            if (user2.save(flush:true)){
                render "success"
            }
            else {
                if (user2.hasErrors()) {
                    render flash.error = user2.errors.allErrors.collect { message(error: it) }
                }
            }
        }
    }

