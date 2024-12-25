package com.project.uber.services.Impl;

import com.project.uber.dto.WalletTransactionDto;
import com.project.uber.entities.WalletTransaction;
import com.project.uber.repositories.WalletTransactionRepository;
import com.project.uber.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private  final WalletTransactionRepository walletTransactionRepository;
    private  final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);

    }
}
