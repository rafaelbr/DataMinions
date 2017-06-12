package br.com.geekfox.apps.DataMinions.webservice;

import android.content.Context;
import br.com.geekfox.apps.DataMinions.helpers.PreferencesHelper;
import br.com.geekfox.apps.DataMinions.json.*;
import br.com.geekfox.apps.DataMinions.services.CustomSSLSocketFactory;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.List;

/**
 * Created by rafaelbrasileiro on 23/04/14.
 */
public class DataService {

    public static final int RESULT_OK = 0;
    public static final int REGISTER_OK = 10;
    public static final int USER_REGISTERED = 11;
    public static final int USER_OK = 20;
    public static final int USER_LOGIN_ERROR = 22;
    public static final int USER_NOTFOUND = 21;
    public static final int DEVICE_REGISTERED = 30;
    public static final int DEVICE_EXIST = 31;
    public static final int DEVICE_NOTREGISTER = 32;
    public static final int LIBRARYENTRY_EXIST = 51;

    //private static final String SERVICE_URL = "https://apps.geekfox.com.br/DataMinionsServer/webdata/general";
    private static final String SERVICE_URL = "http://192.168.0.54:8080/DataMinionsServer/webdata/general";
    //private static final String SERVICE_URL = "http://192.168.43.161:8080/DataMinionsServer/webdata/general";
    private static final String DEVICE_TYPE = "ANDROID";
    private static final String GCM_SENDER_ID = "953072642781";

    private static HttpClient httpClient;

    public static UserData retrieveUserData(UserData data) {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;

        try {
            post = new HttpPost(SERVICE_URL + "/retrieveuser");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            String postData = gson.toJson(data);
            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            if (status == 200) {
                data = gson.fromJson(result, UserData.class);
            }
            else {
                data = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            data = null;
        }

        return data;
    }

    public static UserData login(UserData data) {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;

        try {
            post = new HttpPost(SERVICE_URL + "/login");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            String postData = gson.toJson(data);
            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            if (status == 200) {
                data = gson.fromJson(result, UserData.class);
            }
            else {
                data = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            data = null;
        }

        return data;
    }

    public static UserData loginDevice(DeviceData data) {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;
        UserData userData = null;
        try {
            post = new HttpPost(SERVICE_URL + "/logindevice");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            String postData = gson.toJson(data);
            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            if (status == 200) {
                userData = gson.fromJson(result, UserData.class);
            }
            else {
                userData = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            data = null;
        }

        return userData;
    }

    public static DeviceData registerDevice(DeviceData data) {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;

        try {
            post = new HttpPost(SERVICE_URL + "/registerdevice");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            String postData = gson.toJson(data);
            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            if (status == 200) {
                data = gson.fromJson(result, DeviceData.class);
            }
            else {
                data = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            data = null;
        }

        return data;
    }

    public static UserData saveUserData(UserData data) {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;

        try {
            post = new HttpPost(SERVICE_URL + "/saveuserdata");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            String postData = gson.toJson(data);
            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            if (status == 200) {
                data = gson.fromJson(result, UserData.class);
            }
            else {
                data = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            data = null;
        }

        return data;
    }

    public static MinionHubData captureMinion(MinionHubData data) {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;

        try {
            post = new HttpPost(SERVICE_URL + "/captureminion");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            String postData = gson.toJson(data);
            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            if (status == 200) {
                data = gson.fromJson(result, MinionHubData.class);
            }
            else {
                data = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            data = null;
        }

        return data;
    }

    public static MinionHubData removeFromHub(MinionHubData data) {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;

        try {
            post = new HttpPost(SERVICE_URL + "/removeminionhub");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            String postData = gson.toJson(data);
            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            if (status == 200) {
                data = gson.fromJson(result, MinionHubData.class);
            }
            else {
                data = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            data = null;
        }

        return data;
    }

    public static DataMinionData saveUserMinions(List<DataMinionData> list) {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;
        DataMinionData data;
        try {
            post = new HttpPost(SERVICE_URL + "/saveminions");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            Type type = new TypeToken<List<DataMinionData>>(){}.getType();
            String postData = gson.toJson(list, type);
            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());

            if (status == 200) {
                data = gson.fromJson(result, DataMinionData.class);
            }
            else {
                data = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            data = null;
        }

        return data;
    }

    public static DeviceData registerOnGCM(Context context, DeviceData data) {
        try {
            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
            String hash = gcm.register(GCM_SENDER_ID);
            data.setType(DEVICE_TYPE);
            data.setHash(hash);

            data = registerDevice(data);

            if (data.getResult().getCode() == DEVICE_REGISTERED || data.getResult().getCode() == DEVICE_EXIST) {
                PreferencesHelper.saveGCMHash(hash, context);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            data = null;
        }
        return data;
    }

    public static UserData register(UserData data) {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;

        try {
            post = new HttpPost(SERVICE_URL + "/register");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            String postData = gson.toJson(data);
            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            if (status == 200) {
                data = gson.fromJson(result, UserData.class);
            }
            else {
                data = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            data = null;
        }

        return data;
    }

    public static List<DataMinionData> retrieveAllDataMinions() {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;
        List<DataMinionData> list;
        try {
            post = new HttpPost(SERVICE_URL + "/retrieveallminions");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "text/plain");
            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            Type type = new TypeToken<List<DataMinionData>>(){}.getType();
            if (status == 200) {
                list = gson.fromJson(result, type);
            }
            else {
                list = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            list = null;
        }

        return list;

    }

    public static List<AttackData> retrieveAllAttacks() {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;
        List<AttackData> list;
        try {
            post = new HttpPost(SERVICE_URL + "/retrieveallattacks");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "text/plain");
            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            Type type = new TypeToken<List<AttackData>>(){}.getType();
            if (status == 200) {
                list = gson.fromJson(result, type);
            }
            else {
                list = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            list = null;
        }

        return list;

    }

    public static List<DataMinionData> retrieveUserMinions(UserData data) {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;
        List<DataMinionData> list;
        try {
            post = new HttpPost(SERVICE_URL + "/retrieveuserminions");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            String postData = gson.toJson(data);
            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);
            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            Type type = new TypeToken<List<DataMinionData>>(){}.getType();
            if (status == 200) {
                list = gson.fromJson(result, type);
            }
            else {
                list = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            list = null;
        }

        return list;

    }

    public static List<LibraryEntryData> retrieveLibrary(UserData data) {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;
        List<LibraryEntryData> list;
        try {
            post = new HttpPost(SERVICE_URL + "/retrievelibrary");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            String postData = gson.toJson(data);
            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);
            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            Type type = new TypeToken<List<LibraryEntryData>>(){}.getType();
            if (status == 200) {
                list = gson.fromJson(result, type);
            }
            else {
                list = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            list = null;
        }

        return list;

    }

    public static List<DataHubData> retrieveAllHubs() {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;
        List<DataHubData> list;
        try {
            post = new HttpPost(SERVICE_URL + "/retrievehubs");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "text/plain");
            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            Type type = new TypeToken<List<DataHubData>>(){}.getType();
            if (status == 200) {
                list = gson.fromJson(result, type);
            }
            else {
                list = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            list = null;
        }

        return list;

    }

    public static LibraryEntryData saveLibraryEntry(LibraryEntryData data) {
        configureHTTPClient();

        Gson gson = new Gson();
        HttpPost post = null;

        try {
            post = new HttpPost(SERVICE_URL + "/savelibraryentry");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            String postData = gson.toJson(data);
            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            if (status == 200) {
                data = gson.fromJson(result, LibraryEntryData.class);
            }
            else {
                data = null;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            data = null;
        }

        return data;
    }

    private static void configureHTTPClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            //SETS UP PARAMETERS
            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, "utf-8");
            params.setBooleanParameter("http.protocol.expect-continue", false);

            //REGISTERS SCHEMES FOR BOTH HTTP AND HTTPS
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            CustomSSLSocketFactory sslSocketFactory = new CustomSSLSocketFactory(trustStore);
            sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            registry.register(new Scheme("https",sslSocketFactory, 443));

            ThreadSafeClientConnManager connManager = new ThreadSafeClientConnManager(params, registry);
            httpClient = new DefaultHttpClient(connManager, params);

        }
        catch (KeyStoreException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (CertificateException e) {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (KeyManagementException e) {
            e.printStackTrace();
        }
        catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
    }
}
