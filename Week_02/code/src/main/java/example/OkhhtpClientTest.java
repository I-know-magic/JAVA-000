package example;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class OkhhtpClientTest {
    final static OkHttpClient okHttpClient = new OkHttpClient();
    final static String URL="http://localhost:8088/api/hello";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static String getRequest(){
        Request request=new Request.Builder().get().url(URL).build();
        Call call=okHttpClient.newCall(request);
        String body="";
        try {
            Response response=call.execute();
            if (response.isSuccessful()) {
                body=  response.body().string();
                System.out.println("body=="+body);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  body;
    }
    public static String postRequest(){
        String json="{username:admin;password:admin}";
//        json
        RequestBody body = RequestBody.create(JSON, json);
//        Form
//        FormBody body = new FormBody.Builder()
//                .add("username", "admin")
//                .add("password", "admin")
//                .build();
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "";
    }
    static ResponseBody upload(String url, String filePath, String fileName) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName,
                        RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath)))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + UUID.randomUUID())
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return response.body();
    }

    public static void main(String[] args) {
       String body= getRequest();
        System.out.println(body);
    }
}
