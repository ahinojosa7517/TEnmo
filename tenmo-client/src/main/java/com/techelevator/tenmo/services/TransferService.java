package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.TransferDto;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.math.BigDecimal;

public class TransferService extends ApiService {

    public TransferService() {
        super(Url.TRANSFER.getPath());
    }

    public TransferDto[] getAllTransfers(){
       TransferDto[] transfers = null;
        try {
            ResponseEntity<TransferDto[]> response =
                    restTemplate.exchange(API_URL, HttpMethod.GET, makeAuthEntity(), TransferDto[].class);
            transfers = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }

    public boolean sendMoney(int userToId, BigDecimal amount){
        TransferDto transfer = new TransferDto();
        transfer.setAccountToId(userToId);
        transfer.setAmount(amount);
        try {
            HttpEntity<TransferDto> response = restTemplate.exchange(API_URL, HttpMethod.POST, makeTransferDtoEntity(transfer), TransferDto.class);
            transfer = response.getBody();
            return true;
        } catch (RestClientException e) {
            BasicLogger.log(e.getMessage());
            return false;
        }
    }

    private HttpEntity<TransferDto> makeTransferDtoEntity(TransferDto transferDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(transferDto, headers);
    }
}
