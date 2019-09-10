package controller;

import model.Product;
import service.ProductService;
import service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class ProductServlet extends HttpServlet {
    //private static final long serialVersionUID = 1L;

    public static final String SAVE_DIRECTORY = "images";

    ProductService productService = new ProductServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
            default:
                break;
        }
    }


    private void searchProduct(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter("txtSearch");
        List<Product> productSearch = this.productService.findByName(search);
        request.setAttribute("productSearch", productSearch);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/product/searchResult.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String productFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                productFileName = productFileName.replace("\\", "/");
                int i = productFileName.lastIndexOf('/');

                return productFileName.substring(i + 1);
            }
        }
        return null;

    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String picture = getImageName(request);
            RequestDispatcher requestDispatcher;
            int id = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("productName");
            int price = Integer.parseInt(request.getParameter("productPrice"));
            String description = request.getParameter("productDescription");
            String supplier = request.getParameter("productSupplier");

            Product product = new Product(id, name, price, description, supplier, picture);
            this.productService.save(product);
            requestDispatcher = request.getRequestDispatcher("views/product/create.jsp");
            request.setAttribute("message", "A new product is just created");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("customer/create.jsp");
            dispatcher.forward(request, response);

        }


    }

    private String getImageName(HttpServletRequest request) throws IOException, ServletException {

        String picture = "";
        String aapPath = request.getServletContext().getRealPath("");
        aapPath = aapPath.replace('\\', '/');
        String fullSavePath = null;
        if (aapPath.endsWith("/")) {
            fullSavePath = aapPath + SAVE_DIRECTORY;
        } else {
            fullSavePath = aapPath + "/" + SAVE_DIRECTORY;
        }

        File fileSaveDir = new File(fullSavePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if (fileName != null && fileName.length() > 0) {
                String filePath = fullSavePath + File.separator + fileName;
                System.out.println("write an attachment to file" + filePath);
                part.write(filePath);
                picture = fileName;
            }
        }
        return picture;
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String picture = getImageName(request);
        RequestDispatcher requestDispatcher;
        int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("productName");
            int price = Integer.parseInt(request.getParameter("productPrice"));
            String description = request.getParameter("productDescription");
            String supplier = request.getParameter("productSupplier");

            Product product = this.productService.findById(id);
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setSupplier(supplier);
            if (picture!= "") product.setPicture(picture);
            this.productService.update(id, product);
            request.setAttribute("product", product);
            request.setAttribute("message", "Product information was just updated!");
            requestDispatcher = request.getRequestDispatcher("views/product/update.jsp");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response){
        int id= Integer.parseInt(request.getParameter("id"));
        Product product= this.productService.findById(id);
        RequestDispatcher requestDispatcher;
        if(product==null){
            requestDispatcher= request.getRequestDispatcher("error-404.jsp");
        }else{
            this.productService.delete(id);
            request.setAttribute("message","Delete product successfully!");
            request.setAttribute("product", product);
            requestDispatcher=request.getRequestDispatcher("views/product/delete.jsp");
        }
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "update":
                showEditForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "view":
                viewProduct(request, response);
                break;
            default:
                listProduct(request, response);
                break;
        }
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) {
        int id =  Integer.parseInt(request.getParameter("id"));
        Product product = this.productService.findById(id);
        RequestDispatcher requestDispatcher;
        if(product== null){
            requestDispatcher=request.getRequestDispatcher("error-404.jsp");
        }else{
            request.setAttribute("product",product);
            requestDispatcher= request.getRequestDispatcher("views/product/detail.jsp");
        }
        try{
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void listProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList = this.productService.findAll();
        request.setAttribute("productList", productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/product/list.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/product/create.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));//??not productId
        Product product = this.productService.findById(id);
        RequestDispatcher requestDispatcher;
        if (product == null) {
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("product", product);
            requestDispatcher = request.getRequestDispatcher("views/product/update.jsp");
        }
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productService.findById(id);
        RequestDispatcher requestDispatcher;
        if(product==null){
            requestDispatcher=request.getRequestDispatcher("error-404.jsp");

        }else {
            request.setAttribute("product",product);
            requestDispatcher=request.getRequestDispatcher("views/product/delete.jsp");
        }
        try{
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
