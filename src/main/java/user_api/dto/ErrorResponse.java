package user_api.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String errorType;
    private String reason;
    private LocalDateTime timestamp;
    private String apiName;
    private String path;

    public ErrorResponse() {
    }

    public ErrorResponse(String errorType, String reason) {
        this.errorType = errorType;
        this.reason = reason;
        this.timestamp = LocalDateTime.now();
    }

    // New constructor including apiName and path
    public ErrorResponse(String errorType, String reason, String apiName, String path) {
        this.errorType = errorType;
        this.reason = reason;
        this.apiName = apiName;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
