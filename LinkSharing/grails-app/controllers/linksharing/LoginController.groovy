package linksharing

import com.ttn.co.SearchCO
import com.ttn.co.UserCO
import com.ttn.vo.ResourceVO

class LoginController {

        def dummy(){
            render "Hello World"
        }

        def index(){
//            render(view:"/login/index")
//           render "Login Here"+Resource.topPost()
            List<ResourceVO> topPosts = Resource.topPost()
            List<ResourceVO> recentShares = Resource.recentShares()
            println recentShares
            render view: 'index',model: [topPosts:topPosts,recentShares:recentShares]

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
                    flash.singleError = "User is not active"
                    redirect(controller: 'login', action: 'index')
                }

            }else {
                    log.error("User not exist in database")
                    flash.singleError = "Wrong Username or Password"
                redirect(controller: 'login', action: 'index')


            }

        }

        def logout() {
            session.invalidate()
            forward(controller:'Login',action:'index')
            println 'logout called'
        }

      def register(UserCO userCO) {
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

//            def user = new User(params)       1st way of binding data
//            User user  = new User()           2nd way of binding data
//            user.properties = params