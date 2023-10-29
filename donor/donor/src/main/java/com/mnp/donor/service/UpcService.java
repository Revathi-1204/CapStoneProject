package com.mnp.donor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mnp.donor.entity.DonorSubscriber;
import com.mnp.donor.repository.DonorSubscriberRepository;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.Date;

@Service
public class UpcService {

    @Autowired DonorSubscriberRepository upcRepository;

    public boolean verification(String number) {
        Optional<DonorSubscriber> existingUserNum = upcRepository.findByNumber(number);
        return existingUserNum.isPresent();
    }

    public DonorSubscriber createUserPort(String number) {
        if (isValidNumber(number) && verification(number)) {
            String upc;
            DonorSubscriber user = upcRepository.findByNumber(number).get();
            if (user.getUpc() == null) {
                do {
                    upc = generateRandomUPC();
                } while (!isUpcUnique(upc));
                user.setUpc(upc);
                Date date = new Date();
                user.setDate(date);
                return upcRepository.save(user);
            }
        }
        return null;
    }

    public String generateRandomUPC() {
        StringBuilder upcBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            upcBuilder.append((int) (Math.random() * 10));
        }
        return upcBuilder.toString();
    }

    public boolean isUpcUnique(String upc) {
        return upcRepository.findByUpc(upc).isEmpty();
    }

    public boolean isValidNumber(String number) {
        return Pattern.matches("^\\d{10}$", number);
    }
}
