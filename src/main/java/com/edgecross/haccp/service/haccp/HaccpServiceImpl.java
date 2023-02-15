//package com.edgecross.haccp.service.haccp;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.util.Map;
//
//@Service
//public class HaccpServiceImpl implements HaccpService {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//    private final String RESOURCE_URL = "http://220.90.239.209:48265/shai";
//
//    @Override
//    public Map start(Map<String, Object> map) throws Exception {
//        final String TOPIC = "/progix/dashboard/param";
//
//        MultiValueMap<String, String> multiMap = new LinkedMultiValueMap<>();
//        for (String key : map.keySet()) multiMap.add(key, map.get(key).toString());
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        String uri = UriComponentsBuilder.fromHttpUrl(RESOURCE_URL + "/rest/process/start")
//                .queryParams(multiMap)
//                .toUriString();
//
//        try {
//            ResponseEntity<Map> response = restTemplate.exchange(uri, HttpMethod.POST, request, Map.class);
//
//            return response.getBody();
//
//        } catch (Exception e) {
//            logger.error("HACCP_SERVER_CONNECTION_ERR (uri : " + uri + ")");
//            throw new Exception("SERVER_CONNECTION_ERR");
//        }
//    }
//
//    @Override
//    public Map dataInput(Map<String, Object> map) throws Exception {
//        return null;
//    }
//
//    @Override
//    public Map finish(Map<String, Object> map) throws Exception {
//        return null;
//    }
//
//}