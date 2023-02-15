package com.edgecross.haccp.service.haccp;

import java.util.Map;

public interface HaccpService {

    /* 공정 시작 */
    Map start(Map<String, Object> map) throws Exception;

    /* 인프라 데이터 입력 */
    Map dataInput(Map<String, Object> map) throws Exception;

    /* 공정 종료 */
    Map finish(Map<String, Object> map) throws Exception;
}
