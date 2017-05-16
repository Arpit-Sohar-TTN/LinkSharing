<div id="sendInvitation" class="modal fade" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<g:form controller="mailSending" action="sendInvitation" id="form1">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Send Invitation</h4>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-xs-6 center-block">
							<label><h5>Email*</h5></label><br/><br/><br/>
							<label><h5>Topic*</h5></label>
						</div>

						<div class="col-xs-6">
							<input type="email" required placeholder="Email" name="email"
							       style="background-color: transparent !important;color: black !important;height: 25px;width: 65%"/> <br/><br/>
							%{--<select class="form-control" name="topicvalue" id="topicValue" style="width: 65%">
								<option value="">Profile</option>
								<option value="">Post</option>
								<option value="">Logout</option>
							</select>--}%
							<g:select name="topicvalue"
							          from="${linksharing.Topic.list()}"
							          optionKey="id"/>
						</div>
					</div>

				</div>

				<div class="modal-footer">
					<input type="submit" value="Send" class="btn btn-default btn-success"/>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
				</div>
			</g:form>
		</div>
	</div>
</div>