package linksharing

import com.ttn.co.SearchCO
import com.ttn.co.UserCO
import com.ttn.vo.ResourceVO

class LoginController {



        def index(){
//           render "Login Here"+Resource.topPost()
            List<ResourceVO> topPosts = Resource.topPost()
            List<ResourceVO> recentShares = Resource.recentShares()
            println topPosts.class
            render view: 'home',model: [topPosts:topPosts,recentShares:recentShares]
        }


        def loginHandler(String username,String password) {
            println username +" "+ password
            User user = User.findByUserNameAndPassword(username,password)
            if (user) {
                if (user.isActive){
                    session.setAttribute("user",user)
                    redirect(controller: 'user', action: 'index')
                } else {
                    log.error("User is not active")
                    flash.loginError = "User is not active"
                    redirect(controller: 'login', action: 'index')
                }

            }else {
                    log.error("User not exist in database")
                    flash.loginError = "Wrong Username or Password"
                redirect(controller: 'login', action: 'index')


            }

        }

        def logout() {
            session.invalidate()
            forward(controller:'Login',action:'index')
            println 'logout called'
        }

//            log.info user
//   Hit the url        http://localhost:8080/login/register?firstName=Jay&lastName=Saini&userName=Jay&email=jay.saini@ttn.com&password=jaysaini&confirmPassword=jaysaini
        def register(UserCO userCO) {
//            def user = new User(params)       1st way of binding data

//            User user  = new User()           2nd way of binding data
//            user.properties = params
            User user = new User()
            bindData(user,userCO)
            println user

            if (user.save(flush:true)){
                session.setAttribute('user', user)
                flash.message='Registered Successfully'
                redirect(controller: 'login', action: 'index')
            }
            else {
                if (user.hasErrors()) {

                   flash.error = user.errors.allErrors.collect { message(error: it) }
                    println flash.error
                    redirect(controller: 'login' ,action: 'index' )
                }
            }
        }
    }

