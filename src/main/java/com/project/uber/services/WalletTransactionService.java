package com.project.uber.services;

import com.project.uber.dto.WalletTransactionDto;
import com.project.uber.entities.WalletTransaction;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransaction walletTransaction);
}