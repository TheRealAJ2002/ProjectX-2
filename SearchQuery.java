public class SearchQuery {
    private String question;
    private Response response;

    public SearchQuery(String question, Response response) {
        this.question = question;
        this.response = response;
    }

    public String getQuestion() {
        return question;
    }

    public Response getResponse() {
        return response;
    }
}
