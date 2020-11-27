
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
    "certified",
    "control",
    "streaming"
})
public class Capabilities {

    @JsonProperty("certified")
    private Boolean certified;
    @JsonProperty("control")
    private Control control;
    @JsonProperty("streaming")
    private com.example.hue.model.Streaming streaming;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("certified")
    public Boolean getCertified() {
        return certified;
    }

    @JsonProperty("certified")
    public void setCertified(Boolean certified) {
        this.certified = certified;
    }

    @JsonProperty("control")
    public Control getControl() {
        return control;
    }

    @JsonProperty("control")
    public void setControl(Control control) {
        this.control = control;
    }

    @JsonProperty("streaming")
    public com.example.hue.model.Streaming getStreaming() {
        return streaming;
    }

    @JsonProperty("streaming")
    public void setStreaming(com.example.hue.model.Streaming streaming) {
        this.streaming = streaming;
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
