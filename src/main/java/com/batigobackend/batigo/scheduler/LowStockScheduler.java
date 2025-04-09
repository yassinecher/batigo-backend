package com.batigobackend.batigo.scheduler;


import com.batigobackend.batigo.Entity.Produit;
import com.batigobackend.batigo.Repository.ProduitRepository;
import com.batigobackend.batigo.Service.SmsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LowStockScheduler {

    private final ProduitRepository produitRepository;
    private final SmsService smsService;

    public LowStockScheduler(ProduitRepository produitRepository, SmsService smsService) {
        this.produitRepository = produitRepository;
        this.smsService = smsService;
    }

  //@Scheduled(cron = "0 0 * * * ?")
  // Runs every hour at the start of the hour
  @Scheduled(cron = "0 * * * * ?")  // Runs every minute
  public void checkLowStockProducts() {
        List<Produit> lowStockProducts = produitRepository.findByQuantityLessThanEqual(3);
      StringBuilder message = new StringBuilder("Low stock alert: ");
        for (Produit produit : lowStockProducts) {
            message.append(" ")
                    .append(produit.getNomP())
                    .append(" has only ")
                    .append(produit.getQuantity())
                    .append(" left in stock!");
        }
        System.out.println(message);
        smsService.sendLowStockAlert(message.toString());
    }
}
