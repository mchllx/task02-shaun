public class ProductRequest {
    public static final String REQUEST_ID = "request_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String ITEMS = "items";
    public static final String SPENT = "spent";
    public static final String REMAINING = "remaining";
    public static final String CLIENT_END = "client_end";

    private String requestId;
    private String name;
    private String email;
    private String items;
    private Double spent;
    private Double remaining;

    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getItems() {
        return items;
    }
    public void setItems(String items) {
        this.items = items;
    }
    public Double getSpent() {
        return spent;
    }
    public void setSpent(Double spent) {
        this.spent = spent;
    }
    public Double getRemaining() {
        return remaining;
    }
    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }
}
