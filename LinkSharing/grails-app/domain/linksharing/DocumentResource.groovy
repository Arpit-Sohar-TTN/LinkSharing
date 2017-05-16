package linksharing

class DocumentResource extends Resource {
	String filePath
	static constraints = {
		filePath(nullable: false, blank: false)
	}

	@Override
	 String toString() {
		return "DocumentResource{" +
				"id=" + id +
				", FILE_PATH='" + filePath + '\'' +
				'}'
	}
}
