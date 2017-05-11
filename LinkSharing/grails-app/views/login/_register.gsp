
                <div class="panel panel-default"><!--topPosts-->
                    <div class="panel-heading">
                        <h3 class="panel-title">Register</h3>
                    </div>
                    <div class="panel-body">
                        <form class="" method="post" action="/login/register">
                            <div class="form-group">
                                <label  class="cols-sm-2 control-label namingLabels">First Name</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="firstName" pattern="^[a-zA-Z]*$"  placeholder="Enter your Name" required="true"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="cols-sm-2 control-label namingLabels">Last Name</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="lastName"  pattern="^[a-zA-Z]*$" placeholder="Enter your Name" required="true"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="cols-sm-2 control-label namingLabels">Your Email</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                        <input type="email" class="form-control" name="email" id="email"  required="true" placeholder="Enter your Email"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="username" class="cols-sm-2 control-label namingLabels">Username</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="userName" id="username" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"  required="true" placeholder="Enter your Username"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="password" class="cols-sm-2 control-label namingLabels">Password</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                        <input type="password" class="form-control" name="password" id="pass" required="true" maxlength="15" minlength="5"
                                               placeholder="Enter your Password"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="confirm" class="cols-sm-2 control-label namingLabels">Confirm Password</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                        <input type="password" class="form-control" name="confirmPassword" id="confPass" required="true"
                                               maxlength="15" minlength="5" placeholder="Confirm your Password" onblur="checkPasswordMatch()"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group ">
                                <input  value="Register" type="submit" id="button1" class="btn btn-primary btn-lg btn-block login-button "/>

                            </div>

                        </form>
                    </div>
                </div>
