import java.util.List;

public class ProductResponse {
    public static final String REQUEST_ID = "request_id";
    public static final String ITEM_COUNT = "item_count";
    public static final String BUDGET = "budget";
    public static final String PROD_LIST = "prod_list";
    public static final String PROD_START = "prod_start";
    public static final String PROD_ID = "prod_id";
    public static final String TITLE = "title";
    public static final String PRICE = "price";
    public static final String RATING = "rating";
    public static final String PROD_END = "prod_end";

    private String requestId;
    private Integer itemCount;
    private Double budget;
    private List<Product> products;

    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    public Integer getItemCount() {
        return itemCount;
    }
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }
    public Double getBudget() {
        return budget;
    }
    public void setBudget(Double budget) {
        this.budget = budget;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
