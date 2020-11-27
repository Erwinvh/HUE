
package com.example.hue.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "1",
    "2",
    "3"
})
public class Lighting {

    @JsonProperty("1")
    private Light light;

    @JsonProperty("2")
    private Light light2;

    @JsonProperty("3")
    private Light light3;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("1")
    public Light getLight() {
        return light;
    }

    @JsonProperty("1")
    public void setLight(Light light) {
        this.light = light;
    }

    @JsonProperty("2")
    public Light getLight2() {
        return light2;
    }

    @JsonProperty("2")
    public void setLight2(Light light) {
        this.light2 = light;
    }

    @JsonProperty("3")
    public Light getLight3() {
        return light3;
    }

    @JsonProperty("3")
    public void setLight3(Light light) {
        this.light3 = light;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
