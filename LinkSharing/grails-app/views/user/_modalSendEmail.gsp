<%@ page import="linksharing.Visibility" %>
<div id="sendMail" class="modal fade" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<g:form controller="mailSending" action="sendMail" id="form1">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Create Topic</h4>
				</div>

				<div class="modal-body">
					<div class="row">

						<div class="col-xs-6 center-block">
							<label><h5>username</h5></label><br/><br/><br/>
						</div>

						<div class="col-xs-6">
							<input type="text" name="username" required placeholder="Name"
							       style="background-color: transparent !important;color: black !important;height: 25px;width: 65%"/> <br/><br/>
						</div>

					</div>

				</div>

				<div class="modal-footer">
					<input type="submit" value="Send" class="btn btn-default btn-success"/>
					<button type="button" class="btn btn-primary">Cancel</button>
				</div>
			</g:form>
		</div>
	</div>
</div>