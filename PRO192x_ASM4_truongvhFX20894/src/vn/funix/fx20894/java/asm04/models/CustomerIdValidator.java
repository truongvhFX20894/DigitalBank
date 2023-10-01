package vn.funix.fx20894.java.asm04.models;

public class CustomerIdValidator {
    private final String customerID;

    public CustomerIdValidator(String customerID) {
        this.customerID = customerID;
    }

    public static boolean validateCustomerID(String customerID) {
        return checkID12(customerID) && isNumberic(customerID) && !checkProvince(customerID).equals("0");
    }

    //Hàm kiểm tra số CCCD có đúng 12 số hay không
    public static boolean checkID12(String customerID) {
        return customerID.length()==12;
    }

    //Kiểm tra các kí tự trong cccd có phải là số?
    public static boolean isNumberic(String customerID) {
        return customerID.matches("-?\\d+(\\.\\d+)?");
    }

    //Kiểm tra mã số tỉnh trong cccd có hợp lệ không?
    public static String checkProvince(String customerID) {
        String province = " ";
        String provinceCode = customerID.substring(0,3);

        switch (provinceCode) {
            case "001":
                province = "Ha Noi";
                break;
            case "002":
                province = "Ha Giang";
                break;
            case "004":
                province = "Cao Bang";
                break;
            case "006":
                province = "Bac Kan";
                break;
            case "008":
                province = "Tuyen Quang";
                break;
            case "010":
                province = "Lao Cai";
                break;
            case "011":
                province = "Dien Bien";
                break;
            case "012":
                province = "Lai Chau";
                break;
            case "014":
                province = "Son La";
                break;
            case "015":
                province = "Yen Bai";
                break;
            case "017":
                province = "Hoa Binh";
                break;
            case "019":
                province = "Thai Nguyen";
                break;
            case "020":
                province = "Lang Son";
                break;
            case "022":
                province = "Quang Ninh";
                break;
            case "024":
                province = "Bac Giang";
                break;
            case "025":
                province = "Phu Tho";
                break;
            case "026":
                province = "Vinh Phuc";
                break;
            case "027":
                province = "Bac Ninh";
                break;
            case "030":
                province = "Hai Duong";
                break;
            case "031":
                province = "Hai Phong";
                break;
            case "033":
                province = "Hung Yen";
                break;
            case "034":
                province = "Thai Binh";
                break;
            case "035":
                province = "Ha Nam";
                break;
            case "036":
                province = "Nam Dinh";
                break;
            case "037":
                province = "Ninh Binh";
                break;
            case "038":
                province = "Thanh Hoa";
                break;
            case "040":
                province = "Nghe An";
                break;
            case "042":
                province = "Ha Tinh";
                break;
            case "044":
                province = "Quang Binh";
                break;
            case "045":
                province = "Quang Tri";
                break;
            case "046":
                province = "Thua Thien Hue";
                break;
            case "048":
                province = "Da Nang";
                break;
            case "049":
                province = "Quang Nam";
                break;
            case "051":
                province = "Quang Ngai";
                break;
            case "052":
                province = "Binh Dinh";
                break;
            case "054":
                province = "Phu Yen";
                break;
            case "056":
                province = "Khanh Hoa";
                break;
            case "058":
                province = "Ninh Thuan";
                break;
            case "060":
                province = "Binh Thuan";
                break;
            case "062":
                province = "Kon Tum";
                break;
            case "064":
                province = "Gia Lai";
                break;
            case "066":
                province = "Dak Lak";
                break;
            case "067":
                province = "Dak Nong";
                break;
            case "068":
                province = "Lam Dong";
                break;
            case "070":
                province = "Binh Phuoc";
                break;
            case "072":
                province = "Tay Ninh";
                break;
            case "074":
                province = "Binh Duong";
                break;
            case "075":
                province = "Dong Nai";
                break;
            case "077":
                province = "Ba Ria - Vung Tau";
                break;
            case "079":
                province = "Ho Chi Minh";
                break;
            case "080":
                province = "Long An";
                break;
            case "082":
                province = "Tien Giang";
                break;
            case "083":
                province = "Ben Tre";
                break;
            case "084":
                province = "Tra Vinh";
                break;
            case "086":
                province = "Vinh Long";
                break;
            case "087":
                province = "Dong Thap";
                break;
            case "089":
                province = "An Giang";
                break;
            case "091":
                province = "Kien Giang";
                break;
            case "092":
                province = "Can Tho";
                break;
            case "093":
                province = "Hau Giang";
                break;
            case "094":
                province = "Soc Trang";
                break;
            case "095":
                province = "Bac Lieu";
                break;
            case "096":
                province = "Can Tho";
                break;
            default:
                province = "0";
                break;
        }
        return province;
    }
}
