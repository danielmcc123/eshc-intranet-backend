package com.eshc.backend.rest;

import com.eshc.backend.EshcBackendApplication;
import com.eshc.backend.models.ActionPoint;
import com.eshc.backend.respositories.ActionPointRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshcBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActionPointEndpointTest {

    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Autowired
    private ActionPointRepository actionPointRepository;

    @Test
    public void whenFindById_returnActionPoint() throws JSONException {
        ActionPoint actionPoint = new ActionPoint("Test ActionPoint");
        actionPointRepository.save(actionPoint);

        HttpEntity<ActionPoint> entity = new HttpEntity<>(actionPoint,headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/actionpoints"),
                HttpMethod.POST, entity, String.class
        );
        JSONObject json = new JSONObject(response.getBody());
        assertEquals(actionPoint.getTitle(),json.get("title"));
    }





    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
