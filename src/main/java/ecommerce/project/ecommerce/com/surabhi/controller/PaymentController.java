package ecommerce.project.ecommerce.com.surabhi.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import ecommerce.project.ecommerce.com.surabhi.Exception.OrderException;
import ecommerce.project.ecommerce.com.surabhi.Exception.UserException;
import ecommerce.project.ecommerce.com.surabhi.model.Order;
import ecommerce.project.ecommerce.com.surabhi.repository.OrderRepository;
import ecommerce.project.ecommerce.com.surabhi.response.ApiResponse;
import ecommerce.project.ecommerce.com.surabhi.response.PaymentLinkResponse;
import ecommerce.project.ecommerce.com.surabhi.service.OrderService;
import ecommerce.project.ecommerce.com.surabhi.service.UserService;
import ecommerce.project.ecommerce.com.surabhi.user.domain.OrderStatus;
import ecommerce.project.ecommerce.com.surabhi.user.domain.PaymentStatus;

@RestController
@RequestMapping("/api")
public class PaymentController {
	
	   @Value("${razorpay.api.key}")
	    private String apiKey;

	    @Value("${razorpay.api.secret}")
	    private String apiSecret;
	
	private OrderService orderService;
	private UserService userService;
	private OrderRepository orderRepository;
	
	public PaymentController(OrderService orderService,UserService userService,OrderRepository orderRepository) {
		this.orderService=orderService;
		this.userService=userService;
		this.orderRepository=orderRepository;
	}
	
	@PostMapping("/payments/{orderId}")
	public ResponseEntity<PaymentLinkResponse>createPaymentLink(@PathVariable Long orderId,
			@RequestHeader("Authorization")String jwt) 
					throws RazorpayException, UserException, OrderException{
		
		Order order=orderService.findOrderById(orderId);
		 try {
		      RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);

		      JSONObject paymentLinkRequest = new JSONObject();
		      paymentLinkRequest.put("amount",order.getTotalPrice()* 100);
		      paymentLinkRequest.put("currency","INR"); 

		      JSONObject customer = new JSONObject();
		      customer.put("name",order.getUser().getFirstName()+" "+order.getUser().getLastName());
		      customer.put("contact",order.getUser().getMobile());
		      customer.put("email",order.getUser().getEmail());
		      paymentLinkRequest.put("customer",customer);

		      JSONObject notify = new JSONObject();
		      notify.put("sms",true);
		      notify.put("email",true);
		      paymentLinkRequest.put("notify",notify);

		      paymentLinkRequest.put("reminder_enable",true);

		      paymentLinkRequest.put("callback_url","http://localhost:4200/payment-success?order_id="+orderId);
		      paymentLinkRequest.put("callback_method","get");

		      PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
		      
		      String paymentLinkId = payment.get("id");
		      String paymentLinkUrl = payment.get("short_url");
		      
		      PaymentLinkResponse res=new PaymentLinkResponse(paymentLinkUrl,paymentLinkId);
		      
		      PaymentLink fetchedPayment = razorpay.paymentLink.fetch(paymentLinkId);
		      
		      order.setOrderId(fetchedPayment.get("order_id"));
		      orderRepository.save(order);
		      
		      System.out.println("Payment link ID: " + paymentLinkId);
		      System.out.println("Payment link URL: " + paymentLinkUrl);
		      System.out.println("Order Id : "+fetchedPayment.get("order_id")+fetchedPayment);
		      
		      return new ResponseEntity<PaymentLinkResponse>(res,HttpStatus.ACCEPTED);
		      
		    } catch (RazorpayException e) {
		    	
		      System.out.println("Error creating payment link: " + e.getMessage());
		      throw new RazorpayException(e.getMessage());
		    }
		
		
	}
	
  @GetMapping("/payments")
  public ResponseEntity<ApiResponse> redirect(@RequestParam(name="payment_id") String paymentId,@RequestParam("order_id")Long orderId) throws RazorpayException, OrderException {
	  RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);
	  Order order =orderService.findOrderById(orderId);
	
	  try {
		
		
		Payment payment = razorpay.payments.fetch(paymentId);
		System.out.println("payment details --- "+payment+payment.get("status"));
		
		if(payment.get("status").equals("captured")) {
			System.out.println("payment details --- "+payment+payment.get("status"));
		  
			order.getPaymentDetails().setPaymentId(paymentId);
			order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
			order.setOrderStatus(OrderStatus.PLACED);
			System.out.println(order.getPaymentDetails().getStatus()+"payment status ");
			orderRepository.save(order);
		}
		ApiResponse res=new ApiResponse("your order get placed", true);
	      return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	      
	} catch (Exception e) {
		System.out.println("errrr payment -------- ");
		new RedirectView("https://shopwithzosh.vercel.app/payment/failed");
		throw new RazorpayException(e.getMessage());
	}

  }

}
