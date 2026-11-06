import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiDemo {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder(URI.create("https://v1.hitokoto.cn")).build();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

        System.out.println("状态码: " + resp.statusCode());
        String body = resp.body();
        int start = body.indexOf("\"hitokoto\":\"") +"\"hitokoto\":\"".length();
        int end = body.indexOf("\"" , start);
        System.out.println("句子： " + body.substring(start, end));
    }
}