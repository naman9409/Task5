import java.util.ArrayList;
import java.util.List;

public class Task5 {

    public static void main(String[] args) {
        // Mock HTML content
        String html = """
            <html>
                <body>
                    <div class="product">
                        <span class="product-name">Product 1</span>
                        <span class="product-price">$10.99</span>
                        <span class="product-rating">4.5</span>
                    </div>
                    <div class="product">
                        <span class="product-name">Product 2</span>
                        <span class="product-price">$15.99</span>
                        <span class="product-rating">4.0</span>
                    </div>
                </body>
            </html>
        """;

        // Extract product data manually
        List<String[]> productData = new ArrayList<>();
        productData.add(new String[]{"Name", "Price", "Rating"});

        // Simple parsing logic to extract product data
        String[] products = html.split("</div>"); // Splitting on end of each product block

        for (String product : products) {
            if (product.contains("product-name")) {
                String name = extractBetween(product, "<span class=\"product-name\">", "</span>");
                String price = extractBetween(product, "<span class=\"product-price\">", "</span>");
                String rating = extractBetween(product, "<span class=\"product-rating\">", "</span>");
                
                if (!name.isEmpty() && !price.isEmpty() && !rating.isEmpty()) {
                    productData.add(new String[]{name, price, rating});
                }
            }
        }

        // Print CSV data to console
        for (String[] row : productData) {
            System.out.println(String.join(",", row));
        }
    }

    // Helper method to extract text between two strings
    private static String extractBetween(String text, String start, String end) {
        int startIndex = text.indexOf(start);
        int endIndex = text.indexOf(end, startIndex + start.length());
        if (startIndex == -1 || endIndex == -1) {
            return "";
        }
        return text.substring(startIndex + start.length(), endIndex);
    }
}

