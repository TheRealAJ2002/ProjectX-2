public class AIInterface {
    public Response sendQuestion(String question) {
        // Voer hier de logica uit om de vraag te verwerken en een AI-reactie te genereren
        String responseText = "Dit is een voorbeeldreactie van de AI op de vraag: '" + question + "'";
        return new Response(responseText);
    }
}
