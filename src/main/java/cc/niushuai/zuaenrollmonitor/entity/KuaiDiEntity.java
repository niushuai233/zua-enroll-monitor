package cc.niushuai.zuaenrollmonitor.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class KuaiDiEntity {
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("status")
    private String status;
    @JsonProperty("error_code")
    private String errorCode;
    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("info")
        private InfoDTO info;

        @NoArgsConstructor
        @Data
        public static class InfoDTO {
            @JsonProperty("status")
            private String status;
            @JsonProperty("state")
            private String state;
            @JsonProperty("com")
            private String com;
            @JsonProperty("context")
            private List<ContextDTO> context;
            @JsonProperty("send_time")
            private String sendTime;
            @JsonProperty("latest_progress")
            private String latestProgress;
            @JsonProperty("departure_city")
            private String departureCity;
            @JsonProperty("arrival_city")
            private String arrivalCity;
            @JsonProperty("_source_com")
            private String sourceCom;
            @JsonProperty("_support_from")
            private String supportFrom;
            @JsonProperty("isAbstract")
            private String isAbstract;
            @JsonProperty("current")
            private String current;
            @JsonProperty("currentStatus")
            private String currentStatus;
            @JsonProperty("latest_time")
            private String latestTime;

            @NoArgsConstructor
            @Data
            public static class ContextDTO {
                @JsonProperty("time")
                private String time;
                @JsonProperty("desc")
                private String desc;
            }
        }
    }
}
