package linksharing

class LinkResource extends Resource {
    String url

    static constraints = {
        url( blank: false , url: true)
    }


    @Override
     String toString() {
        return "LinkResource{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}'
    }
}
