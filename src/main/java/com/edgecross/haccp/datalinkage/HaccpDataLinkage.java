package com.edgecross.haccp.datalinkage;

import com.edgecross.haccp.config.MqttConfig;
import com.edgecross.haccp.service.haccp.HaccpService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;


@Component
public class HaccpDataLinkage {

    private MqttConfig mqttConfig;
    private HaccpService haccpService;

    //  공정시작 API
    @GetMapping("start")
    public void start() {

        Map<String, Object> map = new HashMap<>();

        map.put("lcns_no", "77777777777");
        map.put("infra_id", "CD02");
        map.put("ccp_type_cd", "C0030");

        try{
            Map result = haccpService.start(map);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //  인프라 데이터 입력 API
    @GetMapping("/dataInput")
    public void dataInput() {

        Map<String, Object> map = new HashMap<>();

        String lcns_no = "77777777777";
        String infra_id = "CD02";
        String ccp_type_cd = "C0030";

        try{
            Map result = haccpService.start(map);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //  공정종료 API.
    @GetMapping("/finish")
    public void finish() {

        Map<String, Object> map = new HashMap<>();

        String lcns_no = "77777777777";
        String infra_id = "CD02";
        String ccp_type_cd = "C0030";

        try{
            Map result = haccpService.start(map);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


//    @PostMapping("/getparameter")
//    public ResponseEntity<Message> getParameter(@RequestBody Map<String, Object> map) {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> map2 = new HashMap<>();
//
//        map2.put("dashboardCustomize", redisUtil.dashBoardCustomizeRedisGet((String) map.get("mac")));
//
//        try {
//            List<Map> result = modlinkService.getParameter(map);
//
//            result.add(map2);
//
//            return ResponseEntity.ok().body(Message.write("SUCCESS", result));
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(Message.write("null", e));
//        }
//    }
}
