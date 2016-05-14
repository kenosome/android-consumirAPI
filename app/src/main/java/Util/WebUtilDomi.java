package Util;

/**
 * Created by sala4 on 14/05/2016.
 */


        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;

        import java.io.BufferedOutputStream;
        import java.io.BufferedWriter;
        import java.io.ByteArrayOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.ProtocolException;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.HashMap;
        import java.util.Map;

/**
 * Created by Domiciano on 20/08/2014.
 */
public class WebUtilDomi {

    //HACER EL POST REQUEST
    public static String POSTrequest(String url, Uri.Builder builder) throws IOException {
        URL page = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) page.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        String query = builder.build().getEncodedQuery();

        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        InputStream is = connection.getInputStream();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = is.read(buffer)) != -1) {
            bytes.write(buffer, 0, bytesRead);
        }
        is.close();
        connection.disconnect();

        return new String(bytes.toByteArray(), "UTF-8");
    }

    //HACER EL GETREQUEST
    public static String GETrequest(String url) throws IOException {



        URL page = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) page.openConnection();

        InputStream is = connection.getInputStream();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            bytes.write(buffer, 0, bytesRead);
        }
        is.close();
        connection.disconnect();
        return new String(bytes.toByteArray(), "UTF-8");
    }

    //SIRVE PARA BAJAR IMAGENES DIRECTAMENTE PARA PONER EN UN IMAGEVIEW
    public static Bitmap getImagefromURL(String url) throws IOException {
        URL image_url = new URL(url);
        InputStream in = image_url.openStream();
        Bitmap image = BitmapFactory.decodeStream(in);
        return image;
    }

    //HACER EL GETREQUEST
    public static String GETrequest(String url, HashMap<String, String> params) throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append("?");
        for (String key : params.keySet()) {
            String value = params.get(key);
            if(key.contains(" ") || value.contains(" ")) throw new IOException("Key or value has whitespaces");
            sb.append(key+"="+value+"&");
        }
        String salida = sb.toString();
        salida = salida.substring(0, salida.length()-1);
        url = url+salida;

        URL page = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) page.openConnection();

        InputStream is = connection.getInputStream();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            bytes.write(buffer, 0, bytesRead);
        }
        is.close();
        connection.disconnect();
        return new String(bytes.toByteArray(), "UTF-8");
    }


}