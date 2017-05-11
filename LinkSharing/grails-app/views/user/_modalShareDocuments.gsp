<div id="shareDocuments" class="modal fade" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Share Documents</h4>
			</div>

			<g:uploadForm controller="resource" action="saveDocumentResource" enctype="multipart/form-data">
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-6 center-block">
						<label><h5>Documents*</h5></label><br/><br/><br/><br/>
						<label><h5>Description*</h5></label><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
						<label><h5>Topic*</h5></label>
					</div>
					<div class="col-xs-6">
						<label class="control-label">Select File</label>
						<g:field id="input-1a" type="file" name="attachment" class="file" data-show-preview="false"/><br/>
						<textarea required placeholder="Description" name="description" style="width: 80%"></textarea><br/><br/>
						<g:select class="form-control" name="topicName" from='${subscribedTopic}' style="width: 80%">

						</g:select>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<input type="submit" value="Submit" class="btn btn-default btn-success" />
				<input type="button" value="Cancel" class="btn btn-default btn-primary" />

			</div>
			</g:uploadForm>
		</div>
	</div>
</div>