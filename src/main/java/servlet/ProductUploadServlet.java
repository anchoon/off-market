package servlet;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

// Product 관련 클래스 import
import product.ProductDAO; 
import product.Product;

@WebServlet("/ProductUploadServlet")
@MultipartConfig
public class ProductUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 폼에서 입력받은 제품 정보를 받아옵니다.
        String userID = request.getParameter("userID");
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");

        // 사이즈 값을 받아옵니다.
        String productSize = request.getParameter("productSize");

		// 가격 파라미터를 문자열로 받아옵니다.
		String productPriceStr = request.getParameter("productPrice");
		BigDecimal productPrice;

    	try {
        	productPrice = new BigDecimal(productPriceStr);
    	} catch (NumberFormatException e) {
        	productPrice = BigDecimal.ZERO; // 잘못된 가격 값 - 기본값으로 설정하거나 오류 페이지로 리다이렉트
        	e.printStackTrace();
    	}

    	String productCategory = request.getParameter("productCategory");

    	// 이미지 업로드 처리
    	Part filePart = request.getPart("productImage");
    	String productImage = filePart.getSubmittedFileName(); // 파일 이름을 가져옵니다.

   		String uploadPath =
       		getServletContext().getRealPath("") + File.separator + "upload";
   		File uploadDirectory =
       		new File(uploadPath);

   		if (!uploadDirectory.exists()) {
       		uploadDirectory.mkdir();
   		}

   		if (productImage != null && !productImage.isEmpty()) {
       		filePart.write(uploadPath + File.separator + productImage);
   		}

   		Timestamp productDate =
       		new Timestamp(System.currentTimeMillis());

  		int stockQuantity =
   			Integer.parseInt(request.getParameter("stockQuantity"));

  		try { 
  		   ProductDAO dao=new ProductDAO();
  		   Product product=new Product(userID, productName,
  		     	productDescription,
  		     	productPrice,
  		     	productCategory,
  		     	stockQuantity,
  		     	productImage,
  		    	new Timestamp(System.currentTimeMillis()),
  		    	productSize); // Use the constructor that includes the size
 
  		   boolean success=dao.addProduct(product);

  		   if(success){
  		       response.sendRedirect(request.getContextPath()+"/main.jsp");  
  		   }
  		   else{
  		       response.sendRedirect("/error.jsp");
  		   }
  		} catch (Exception e) {
  		  e.printStackTrace();
  		}
}
}