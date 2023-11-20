import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        int port = 3000;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        Socket sock = new Socket("127.0.0.1", port);

        ProductResponse productResponse = new ProductResponse();
        Product product = new Product();
        List<Product> products = new LinkedList<>();

        InputStream is = sock.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;
            int count = 0;
            int itemCount = Integer.MAX_VALUE;
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(":");
                if (line.isBlank() || line.isEmpty()) {
                    continue;
                }
                String key = lines[0].trim();
                String value = "";
                if (lines.length > 1) {
                    value = lines[1].trim();
                }
                switch (key) {
                    case ProductResponse.REQUEST_ID:
                        productResponse.setRequestId(value);
                        break;
                    case ProductResponse.ITEM_COUNT:
                        itemCount = Integer.parseInt(value);
                        productResponse.setItemCount(itemCount);
                        break;
                    case ProductResponse.BUDGET:
                        productResponse.setBudget(Double.parseDouble(value));
                        break;
                    case ProductResponse.PROD_START:
                        product = new Product();
                        break;
                    case ProductResponse.PROD_ID:
                        product.setId(value);
                        break;
                    case ProductResponse.TITLE:
                        product.setTitle(value);
                        break;
                    case ProductResponse.PRICE:
                        product.setPrice(Double.parseDouble(value));
                        break;
                    case ProductResponse.RATING:
                        product.setRating(Double.parseDouble(value));
                        break;
                    case ProductResponse.PROD_END:
                        products.add(product);
                        count++;
                        break;
                }
                if (count == itemCount) {
                    break;
                }
            }
            productResponse.setProducts(
                products.stream()
                .sorted((a, b) -> a.getRating().intValue() - b.getRating().intValue())
                .toList()
            );

         List<Product> products2 = productResponse.getProducts();
         double budget = productResponse.getBudget();
         double spent = 0.0d;
         List<String> requestItems = new LinkedList<>();
         for (Product product2 : products2) {
                requestItems.add(product2.getId());
                spent += product2.getPrice();
                if ((budget -= product2.getPrice()) <= 0) {
                    break;
                }
         }

         ProductRequest productRequest = new ProductRequest();
         productRequest.setRequestId(productResponse.getRequestId());
         productRequest.setName("Shaun");
         productRequest.setEmail("shwah@visa.com");
         productRequest.setItems(requestItems
            .stream()
            .collect(Collectors.joining(","))
        );
        productRequest.setSpent(spent);
        productRequest.setRemaining(budget - spent);

        OutputStream os = sock.getOutputStream();
            OutputStreamWriter osr = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osr);

            bw.write("%s: %s\n".formatted(ProductRequest.REQUEST_ID, productRequest.getRequestId()));
            bw.write("%s: %s\n".formatted(ProductRequest.NAME, productRequest.getName()));
            bw.write("%s: %s\n".formatted(ProductRequest.EMAIL, productRequest.getEmail()));
            bw.write("%s: %s\n".formatted(ProductRequest.ITEMS, productRequest.getItems()));
            bw.write("%s: %s\n".formatted(ProductRequest.SPENT, productRequest.getSpent()));
            bw.write("%s: %s\n".formatted(ProductRequest.REMAINING, productRequest.getRemaining()));
            bw.write("%s\n".formatted(ProductRequest.CLIENT_END));
            bw.flush();
        

        InputStream is2 = sock.getInputStream();
            InputStreamReader isr2 = new InputStreamReader(is);
            BufferedReader br2 = new BufferedReader(isr);
            String line2;
            while ((line2 = br2.readLine()) != null) {
                System.out.println(line2);
            }

            br.close();
            isr.close();
            is.close();
            bw.close();
            osr.close();
            os.close();
            br2.close();
            isr2.close();
            is2.close();
            sock.close();
    }
}
