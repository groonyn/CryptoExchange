package json;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonTest {

    public static void main(String[] ignored) throws Exception {


        URL url = new URL("https://cex.io/api/last_price/BTC/USD");
        HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
        httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStreamReader reader = new InputStreamReader(httpcon.getInputStream());
        MyDto dto = new Gson().fromJson(reader, MyDto.class);

        // using the deserialized object
        System.out.println(dto.lprice);
        System.out.println(dto.curr1);
        System.out.println(dto.curr2);
    }

    private class MyDto {
        String lprice;
        String curr1;
        String curr2;
    }

}
