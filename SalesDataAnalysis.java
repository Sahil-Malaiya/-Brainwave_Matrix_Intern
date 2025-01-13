/*
Sample Sales Data (CSV Format):
Transaction ID,Date,Product,Quantity,Unit Price,Total Sale,Payment Method
1,2025-01-01,Product A,3,50,150,Credit Card
2,2025-01-01,Product B,1,100,100,Cash
3,2025-01-02,Product A,2,50,100,Credit Card
4,2025-01-02,Product C,5,30,150,Debit Card
5,2025-01-03,Product B,3,100,300,Cash
6,2025-01-03,Product C,2,30,60,Credit Card
7,2025-01-04,Product A,1,50,50,Cash
8,2025-01-04,Product B,4,100,400,Credit Card
9,2025-01-05,Product C,3,30,90,Debit Card
10,2025-01-05,Product A,2,50,100,Cash*/
import java.util.*;
import java.util.stream.Collectors;

public class SalesDataAnalysis {

    static class Sale {
        String transactionId;
        String date;
        String product;
        int quantity;
        double unitPrice;
        double totalSale;
        String paymentMethod;

        public Sale(String transactionId, String date, String product, int quantity, double unitPrice, double totalSale, String paymentMethod) {
            this.transactionId = transactionId;
            this.date = date;
            this.product = product;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.totalSale = totalSale;
            this.paymentMethod = paymentMethod;
        }
    }

    public static void main(String[] args) {
        List<Sale> sales = Arrays.asList(
                new Sale("1", "2025-01-01", "Product A", 3, 50, 150, "Credit Card"),
                new Sale("2", "2025-01-01", "Product B", 1, 100, 100, "Cash"),
                new Sale("3", "2025-01-02", "Product A", 2, 50, 100, "Credit Card"),
                new Sale("4", "2025-01-02", "Product C", 5, 30, 150, "Debit Card"),
                new Sale("5", "2025-01-03", "Product B", 3, 100, 300, "Cash"),
                new Sale("6", "2025-01-03", "Product C", 2, 30, 60, "Credit Card"),
                new Sale("7", "2025-01-04", "Product A", 1, 50, 50, "Cash"),
                new Sale("8", "2025-01-04", "Product B", 4, 100, 400, "Credit Card"),
                new Sale("9", "2025-01-05", "Product C", 3, 30, 90, "Debit Card"),
                new Sale("10", "2025-01-05", "Product A", 2, 50, 100, "Cash")
        );

        // 1. Total Sales
        double totalSales = sales.stream().mapToDouble(sale -> sale.totalSale).sum();
        System.out.println("Total Sales: " + totalSales);

        // 2. Top Selling Products (by quantity)
        Map<String, Integer> productSales = sales.stream()
                .collect(Collectors.groupingBy(sale -> sale.product, Collectors.summingInt(sale -> sale.quantity)));
        productSales.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println("Product: " + entry.getKey() + ", Quantity Sold: " + entry.getValue()));

        // 3. Sales by Payment Method
        Map<String, Double> paymentMethodSales = sales.stream()
                .collect(Collectors.groupingBy(sale -> sale.paymentMethod, Collectors.summingDouble(sale -> sale.totalSale)));
        paymentMethodSales.forEach((method, amount) -> System.out.println("Payment Method: " + method + ", Total Sales: " + amount));

        // 4. Average Sale Value
        double averageSale = sales.stream().mapToDouble(sale -> sale.totalSale).average().orElse(0.0);
        System.out.println("Average Sale Value: " + averageSale);
    }
}
