public class Ticket {
    
    private Integer ticketId; 
    private Integer userId; 
    private Integer tranId; 
    private Integer carNo; 
    private String siteNo;
    private Integer price;
    private Integer state; // boolean?

    // 相關欄位
    private User user;
    private Tran tran;
}