package com.rentguruz.app.b2b.model.base;

import com.rentguruz.app.b2b.model.AttachmentsModel;
import com.rentguruz.app.b2b.model.Customer;
import com.rentguruz.app.b2b.model.ReservationCheckin;
import com.rentguruz.app.b2b.model.ReservationCheckout;
import com.rentguruz.app.b2b.model.companyModel;
import com.rentguruz.app.b2b.model.CreditCardModel;
import com.rentguruz.app.b2b.model.InsuranceCompanyDetailsModel;
import com.rentguruz.app.b2b.model.InsuranceModel;
import com.rentguruz.app.b2b.model.display.ThemeColors;
import com.rentguruz.app.b2b.model.reservation.ReservationEquipment;
import com.rentguruz.app.b2b.model.response.CustomerProfile;
import com.rentguruz.app.b2b.model.response.LoginResponse;
import com.rentguruz.app.b2b.model.response.ReservationSummarry;
import com.rentguruz.app.b2b.model.response.UpdateDL;
import com.rentguruz.app.b2b.model.ReservationBusinessSource;

public class UserData {
    public static String UserDetail;
    public static CustomerProfile customerProfile = new CustomerProfile();
    public static CreditCardModel UpdateCreditCard = new CreditCardModel();
    public static LoginResponse loginResponse = new LoginResponse();
    public static InsuranceCompanyDetailsModel insuranceCompanyDetailsModel = new InsuranceCompanyDetailsModel();
    public static InsuranceModel insuranceModel = new InsuranceModel();
    public static companyModel companyModel = new companyModel();
    public static UpdateDL updateDL = new UpdateDL();
    public static Customer customer = new Customer();
    public static ReservationCheckout reservationCheckout = new ReservationCheckout();
    public static ReservationCheckin reservationCheckin = new ReservationCheckin();
    public static ReservationBusinessSource reservationBusinessSource = new ReservationBusinessSource();
    public static String billingdetail;
    public static ReservationEquipment[] equipment;
    public static CreditCardModel activepmt = new CreditCardModel();
    public static ThemeColors UiColor = new ThemeColors();
    public static ReservationSummarry reserversationSummary = new ReservationSummarry();
}
