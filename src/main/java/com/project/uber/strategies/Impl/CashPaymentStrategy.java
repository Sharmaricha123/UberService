package com.project.uber.strategies.Impl;

import com.project.uber.entities.Driver;
import com.project.uber.entities.Payment;
import com.project.uber.entities.Wallet;
import com.project.uber.entities.enums.PaymentStatus;
import com.project.uber.entities.enums.TransactionMethod;
import com.project.uber.repositories.PaymentRepository;
import com.project.uber.services.PaymentService;
import com.project.uber.services.WalletService;
import com.project.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private  final WalletService walletService;
    private  final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {

        Driver driver=payment.getRide().getDriver();
        double platformCommission=payment.getAmount()*PLATFORM_COMMISSION;

        Wallet driverWallet=walletService.deductMoneyFromWallet(driver.getUser(), platformCommission,null,payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
