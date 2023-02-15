package com.edgecross.haccp.dto.mqtt;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MqttDto {

    private String status;

    @NotNull(message = "MAC_FORMAT_ERR")
    private String mac;

    private String ts;

    private String name;

    @NotNull(message = "UID_FORMAT_ERR")
    private String uid;

    private String value;

    private String unit;
}
