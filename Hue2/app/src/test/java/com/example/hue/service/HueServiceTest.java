package com.example.hue.service;

import com.example.hue.model.Lighting;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class HueServiceTest {

    @Test
    public void getService() throws IOException {
        MockWebServer server = new MockWebServer();

        server.enqueue(new MockResponse().setBody("{\"1\":{\"modelid\":\"LCT001\",\"name\":\"Hue Lamp 1\",\"swversion\":\"65003148\",\"state\":{\"xy\":[0,0],\"ct\":0,\"alert\":\"none\",\"sat\":254,\"effect\":\"none\",\"bri\":254,\"hue\":4444,\"colormode\":\"hs\",\"reachable\":true,\"on\":true},\"type\":\"Extended color light\",\"pointsymbol\":{\"1\":\"none\",\"2\":\"none\",\"3\":\"none\",\"4\":\"none\",\"5\":\"none\",\"6\":\"none\",\"7\":\"none\",\"8\":\"none\"},\"uniqueid\":\"00:17:88:01:00:d4:12:08-0a\"},\"2\":{\"modelid\":\"LCT001\",\"name\":\"Hue Lamp 2\",\"swversion\":\"65003148\",\"state\":{\"xy\":[0.346,0.3568],\"ct\":201,\"alert\":\"none\",\"sat\":144,\"effect\":\"none\",\"bri\":254,\"hue\":23536,\"colormode\":\"hs\",\"reachable\":true,\"on\":true},\"type\":\"Extended color light\",\"pointsymbol\":{\"1\":\"none\",\"2\":\"none\",\"3\":\"none\",\"4\":\"none\",\"5\":\"none\",\"6\":\"none\",\"7\":\"none\",\"8\":\"none\"},\"uniqueid\":\"00:17:88:01:00:d4:12:08-0b\"},\"3\":{\"modelid\":\"LCT001\",\"name\":\"Hue Lamp 3\",\"swversion\":\"65003148\",\"state\":{\"xy\":[0.346,0.3568],\"ct\":201,\"alert\":\"none\",\"sat\":254,\"effect\":\"none\",\"bri\":254,\"hue\":65136,\"colormode\":\"hs\",\"reachable\":true,\"on\":true},\"type\":\"Extended color light\",\"pointsymbol\":{\"1\":\"none\",\"2\":\"none\",\"3\":\"none\",\"4\":\"none\",\"5\":\"none\",\"6\":\"none\",\"7\":\"none\",\"8\":\"none\"},\"uniqueid\":\"00:17:88:01:00:d4:12:08-0c\"}}"));
        server.start(InetAddress.getLocalHost(), 8000);

        HttpUrl url = server.url("/api/newdeveloper/lights/");

        HueService hueService = new HueService();
        hueService.getService(url.toString());

        Assert.assertEquals(Lighting.getINSTANCE().getLight("1").getName(), "Hue Lamp 1");

        server.shutdown();
    }
}