package dto.request;

public class PostRequest {
    private String date;

    private PostRequest() {
    }

    public PostRequest(final String date) {
        this.date = date;
    }

    public String date() {
        return date;
    }

}
