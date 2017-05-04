package linksharing

class  DocumentResource extends Resource {
    String filePath
    static constraints = {
        filePath(nullable: false,blank: false)
    }

    @Override
    public String toString() {
        return "DocumentResource{"
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
