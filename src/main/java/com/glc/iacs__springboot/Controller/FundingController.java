package com.glc.iacs__springboot.Controller;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glc.iacs__springboot.Model.Funding;
import com.glc.iacs__springboot.Repository.FundingRepository;
import com.glc.iacs__springboot.Service.EmailSenderService;
import com.glc.iacs__springboot.Service.EncrytionService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/funding")
@CrossOrigin(origins = "*")
public class FundingController {

    @Autowired
    private EmailSenderService senderService;

    @Autowired
    private FundingRepository fundingRepository;

    @Autowired
    private EncrytionService encrytionService;

    @PostMapping("/apply")
    @Transactional
    public ResponseEntity<Funding> fundingApply(@RequestBody Funding funding) throws Exception {

        funding.setAppliedDate(new Date());
        funding.setStatus("sent");

        
        Funding newFunding = fundingRepository.save(funding);
        String hashId = encrytionService.encrypt(newFunding.getId().toString());
        
        boolean mailSend = senderService.sendMail(
                hashId,
                newFunding.getSupervisorEmail(),
                newFunding.getSupervisorName(),
                newFunding.getStudentName(),
                newFunding.getStudentEmail(),
                newFunding.getProjectTitle());

        if (mailSend) {
            return ResponseEntity.status(200).body(newFunding);
        } else {
            // If email sending fails, rollback the transaction to reverse the funding
            // request.
            throw new RuntimeException("Failed to send email. Rolling back transaction.");
        }
    }

    @Transactional
    @GetMapping("/update_status")
    public ResponseEntity<Funding> updateFundingStatus(
            @RequestParam("fundingId") Long fundingId,
            @RequestParam("status") String status) {

        Funding updateFunding = fundingRepository.findById(fundingId).get();
        updateFunding.setStatus(status);
        boolean mailSend = senderService.sendStudentNotificationMail(updateFunding.getStudentEmail(),
                updateFunding.getStudentName(),
                updateFunding.getProjectTitle(),
                updateFunding.getStatus());

        if (mailSend) {
            return ResponseEntity.status(200).body(fundingRepository.save(updateFunding));
        } else {
            // If email sending fails, rollback the transaction to reverse the funding
            // request.
            throw new RuntimeException("Failed to send email. Rolling back transaction.");
        }

    }

    @GetMapping
    public ResponseEntity<List<Funding>> getFundingRequest() {
        return ResponseEntity.status(200).body(fundingRepository.findAll());
    }

    @GetMapping("/approved")
    public ResponseEntity<List<Funding>> getFundingRequestByApproved() {
        return ResponseEntity.status(200).body(fundingRepository.findAllByStatus("approved"));
    }

    @GetMapping("/student")
    public ResponseEntity<Funding> getIndividualFundingThroughStudent(@RequestParam("id") Long id) {
        Optional<Funding> fundingOptional = fundingRepository.findByStudentId(id);

        if (fundingOptional.isPresent()) {
            Funding funding = fundingOptional.get();
            return ResponseEntity.ok(funding);
        } else {
            // If the funding is not found for the given student ID, return a 404 Not Found
            // response.
            return ResponseEntity.status(200).build();
        }
    }

    @GetMapping("/specific")
    public ResponseEntity<Funding> getFundingDetail(@RequestParam("fundingId") Long fundingId) {
        Optional<Funding> fundingOptional = fundingRepository.findById(fundingId);

        if (fundingOptional.isPresent()) {
            Funding funding = fundingOptional.get();
            return ResponseEntity.ok(funding);
        } else {
            // If the funding is not found for the given student ID, return a 404 Not Found
            // response.
            return ResponseEntity.status(200).build();
        }
    }


    @GetMapping("/hash")
    public ResponseEntity<Funding> getFundingDetailByHash(@RequestParam("fundingHash") String fundingHash) throws Exception {

        
        String fundingId = encrytionService.decrypt(fundingHash);

        Optional<Funding> fundingOptional = fundingRepository.findById(Long.parseLong(fundingId));

        if (fundingOptional.isPresent()) {
            Funding funding = fundingOptional.get();
            return ResponseEntity.ok(funding);
        } else {
            // If the funding is not found for the given student ID, return a 404 Not Found
            // response.
            return ResponseEntity.status(200).build();
        }
   }

    @GetMapping("/encrypt")
    public String deleteFundingtest(@RequestParam("data") String data) {
        try {
            byte[] key = new byte[32];
            new SecureRandom().nextBytes(key);

            // Convert the key to Base64 encoding
            String encodedKey = Base64.getEncoder().encodeToString(key);
            System.out.println("Base64-encoded secret key: " + encodedKey);

            // return encodedKey;
            return encrytionService.decrypt("==");

        } catch (Exception e) {
            System.out.println(e);
            return "Error";
            // TODO: handle exception
        }

    }

    
    @GetMapping("/delete")
    public void deleteFunding() {
      fundingRepository.deleteAll();
    }

}
