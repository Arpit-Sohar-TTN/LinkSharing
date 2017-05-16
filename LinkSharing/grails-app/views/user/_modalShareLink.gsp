<div id="shareLink" class="modal fade" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<g:form controller="resource" action="saveLinkResource">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Share Link</h4>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-xs-6 center-block">
							<label><h5>Link*</h5></label><br/><br/><br/>
							<label><h5>Description*</h5></label><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
							<label><h5>Topic*</h5></label>
						</div>

						<div class="col-xs-6">
							<input type="text" name="url" required placeholder="Link"
							       style="background-color: transparent !important;color: black !important;height: 25px;width: 80%"/> <br/><br/>
							<textarea required placeholder="Description" name="description"
							          style="width: 80%"></textarea><br/><br/>
							<g:select from='${subscribedTopic}' class="form-control" style="width: 80%" name="topic">

							</g:select>
						</div>
					</div>

				</div>

				<div class="modal-footer">
					<input type="submit" class="btn btn-default btn-success"/>
					<button type="button" class="btn btn-primary">Cancel</button>
				</div>
			</g:form>
		</div>
	</div>
</div>