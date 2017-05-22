<%@ page import="linksharing.Visibility" %>
<div id="createTopic" class="modal fade" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="/topic/save" id="form1">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Create Topic</h4>
				</div>

				<div class="modal-body">
					<div class="row">

						<div class="col-xs-6 center-block">
							<label><h5>Name*</h5></label><br/><br/><br/>
							<label><h5>Visibility*</h5></label>
						</div>

						<div class="col-xs-6">
							<input type="text" name="topicName" required placeholder="Name"
							       style="background-color: transparent !important;color: black !important;height: 25px;width: 65%"/> <br/><br/>
							<select form="form1" name="visibility" optionKey="id" optionValue="name">
								<option value="PUBLIC">PUBLIC</option>
								<option value="PRIVATE">PRIVATE</option>

							</select>
						</div>

					</div>

				</div>

				<div class="modal-footer">
					<input type="submit" value="Save" class="btn btn-default btn-success"/>
					<button type="button" class="btn btn-primary"  data-dismiss="modal">Cancel</button>
				</div>
			</form>
		</div>
	</div>
</div>