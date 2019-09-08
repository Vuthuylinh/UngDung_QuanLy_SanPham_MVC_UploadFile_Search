package service;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private  static Map<Integer,Product> products;
    static {
        products=new HashMap<>();
        products.put(1, new Product(1,"Yoga1",20,"English","NewYork Time","Meditate.jpg"));
        products.put(2, new Product(2,"Yoga2",22,"English","NewWorld","Meditate2.jpg"));
        products.put(3, new Product(3,"Meditation 1",25,"Vietnamese","Tuoi tre","Meditate3.jpg"));
        products.put(4, new Product(4,"Meditation 2",50,"Japanese","Thoi Dai","Meditate4.jpg"));
        products.put(5, new Product(5,"Yoga book",30,"Indian","Van Hoa","Meditate6.jpg"));
        products.put(6, new Product(6,"Meditation book",40,"American","The Gioi","officeyoga.jpg"));
        products.put(7, new Product(7,"Sleep training",29,"For officer","Google","yoga2.jpg"));
    }
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> productList = findAll();
        List<Product> productSearchResponse = new ArrayList<>();
        String searchNameToLowerCase = name.toLowerCase().trim();
        for (Product product : productList) {
            String productNameToLowercase = product.getName().toLowerCase().trim();

            if (productNameToLowercase.contains(searchNameToLowerCase)) {
                productSearchResponse.add(product);
            }
        }
        return productSearchResponse;
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(),product);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id,product);
    }

    @Override
    public void delete(int id) {
        products.remove(id);
    }
}

