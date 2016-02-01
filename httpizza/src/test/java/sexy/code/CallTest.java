package sexy.code;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CallTest extends MockWebServerTest {

    @Test
    public void get() throws Exception {
        HttpUrl url = fullUrl("/path?query1=1&query2=2");

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("body")
                .addHeader("Content-Type: text/plain");
        server.enqueue(mockResponse);

        Request request = client.newRequest()
                .url(url)
                .addHeader("User-Agent", "UnitTest")
                .get()
                .build();

        Response<String> response = client.newCall(request).execute();
        assertEquals(200, response.getCode());
        assertEquals("text/plain", response.getHeaders().get("Content-Type").get(0));
        assertEquals("body", response.getBody());
        assertTrue(response.isSuccessful());

        RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals(urlPathWithQuery(url), recordedRequest.getPath());
        assertEquals(0, recordedRequest.getBody().size());
        assertEquals("UnitTest", recordedRequest.getHeader("User-Agent"));
        assertNull(recordedRequest.getHeader("Content-Length"));
    }

    @Test
    public void post() throws Exception {
        HttpUrl url = fullUrl("/path?query1=1&query2=2");

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("body")
                .addHeader("Content-Type: text/plain");
        server.enqueue(mockResponse);

        Request request = client.newRequest()
                .url(url)
                .addHeader("User-Agent", "UnitTest")
                .post("requestBody", String.class)
                .build();

        Response<String> response = client.newCall(request).execute();
        assertEquals(200, response.getCode());
        assertEquals("text/plain", response.getHeaders().get("Content-Type").get(0));
        assertEquals("body", response.getBody());
        assertTrue(response.isSuccessful());

        RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals(urlPathWithQuery(url), recordedRequest.getPath());
        assertEquals(11, recordedRequest.getBody().size());
        assertEquals("11", recordedRequest.getHeader("Content-Length"));
        assertEquals("text/plain; charset=utf-8", recordedRequest.getHeader("Content-Type"));
        assertEquals("UnitTest", recordedRequest.getHeader("User-Agent"));
    }

    @Test
    public void postZeroLength() throws Exception {
        HttpUrl url = fullUrl("/path?query1=1&query2=2");

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("body")
                .addHeader("Content-Type: text/plain");
        server.enqueue(mockResponse);

        Request request = client.newRequest()
                .url(url)
                .addHeader("User-Agent", "UnitTest")
                .post(new RequestBody() {
                    @Override
                    public String contentType() {
                        return null;
                    }

                    @Override
                    public void writeTo(BufferedOutputStream os) throws IOException {
                    }
                })
                .build();

        Response<String> response = client.newCall(request).execute();
        assertEquals(200, response.getCode());
        assertEquals("text/plain", response.getHeaders().get("Content-Type").get(0));
        assertEquals("body", response.getBody());
        assertTrue(response.isSuccessful());

        RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals(urlPathWithQuery(url), recordedRequest.getPath());
        assertEquals(0, recordedRequest.getBody().size());
        assertEquals(null, recordedRequest.getHeader("Content-Length"));
        assertEquals("UnitTest", recordedRequest.getHeader("User-Agent"));
    }

    @Test
    public void put() throws Exception {
        HttpUrl url = fullUrl("/path?query1=1&query2=2");

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("body")
                .addHeader("Content-Type: text/plain");
        server.enqueue(mockResponse);

        Request request = client.newRequest()
                .url(url)
                .addHeader("User-Agent", "UnitTest")
                .put("requestBody", String.class)
                .build();

        Response<String> response = client.newCall(request).execute();
        assertEquals(200, response.getCode());
        assertEquals("text/plain", response.getHeaders().get("Content-Type").get(0));
        assertEquals("body", response.getBody());
        assertTrue(response.isSuccessful());

        RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("PUT", recordedRequest.getMethod());
        assertEquals(urlPathWithQuery(url), recordedRequest.getPath());
        assertEquals(11, recordedRequest.getBody().size());
        assertEquals("11", recordedRequest.getHeader("Content-Length"));
        assertEquals("text/plain; charset=utf-8", recordedRequest.getHeader("Content-Type"));
        assertEquals("UnitTest", recordedRequest.getHeader("User-Agent"));
    }

    @Test
    public void delete() throws Exception {
        HttpUrl url = fullUrl("/path?query1=1&query2=2");

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("body")
                .addHeader("Content-Type: text/plain");
        server.enqueue(mockResponse);

        Request request = client.newRequest()
                .url(url)
                .addHeader("User-Agent", "UnitTest")
                .delete()
                .build();

        Response<String> response = client.newCall(request).execute();
        assertEquals(200, response.getCode());
        assertEquals("text/plain", response.getHeaders().get("Content-Type").get(0));
        assertEquals("body", response.getBody());
        assertTrue(response.isSuccessful());

        RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("DELETE", recordedRequest.getMethod());
        assertEquals(urlPathWithQuery(url), recordedRequest.getPath());
        assertEquals(0, recordedRequest.getBody().size());
        assertEquals(null, recordedRequest.getHeader("Content-Length"));
        assertNull(recordedRequest.getHeader("Content-Type"));
        assertEquals("UnitTest", recordedRequest.getHeader("User-Agent"));
    }

    @Test
    public void head() throws Exception {
        HttpUrl url = fullUrl("/path?query1=1&query2=2");

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .addHeader("Content-Type: text/plain");
        server.enqueue(mockResponse);

        Request request = client.newRequest()
                .url(url)
                .addHeader("User-Agent", "UnitTest")
                .method("HEAD", null)
                .build();

        Response<String> response = client.newCall(request).execute();
        assertEquals(200, response.getCode());
        assertEquals("text/plain", response.getHeaders().get("Content-Type").get(0));
        assertTrue(response.isSuccessful());

        RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("HEAD", recordedRequest.getMethod());
        assertEquals(urlPathWithQuery(url), recordedRequest.getPath());
        assertEquals(0, recordedRequest.getBody().size());
        assertEquals(null, recordedRequest.getHeader("Content-Length"));
        assertNull(recordedRequest.getHeader("Content-Type"));
        assertEquals("UnitTest", recordedRequest.getHeader("User-Agent"));
    }
}